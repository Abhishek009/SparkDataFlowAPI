package com.api.controller;

import java.util.*;

import com.api.model.*;
import com.api.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SDFController {

	@Autowired
	InputModalRepository inputModalRepository;
	@Autowired
	TransformationRepository transRepository;
	@Autowired
	FlowMappingRepository flowMappingRepository;
	@Autowired
	InputOutputModalRepository inputOutputModalRepository;
	@Autowired
	TableInfoRepository tableInfoRepository;
	@Autowired
	ExecutionConfigModalRepository executionConfigModalRepository;

	// Get the list of users
//	@GetMapping("/allinputdataset")
//	public List<InputModal> getAllInputDataset() {
//		return inputModalRepository.findAll();
//	}

	@GetMapping("/allinputdataset")
	public List<FlowMapping> getAllInputDataset() {
		System.out.println(flowMappingRepository.getMapping());
		return flowMappingRepository.getMapping();
	}

	@PostMapping("/saveinputoutput")
	public Object setOutputInputMapping(@RequestBody InputOutputModal data) {
		System.out.println(data);
		inputOutputModalRepository.save(data);
		System.out.println("inputOutputModalRepository from saveinputoutput "+inputOutputModalRepository.findById(data.getId()));
		return inputOutputModalRepository.findById(data.getId());
	}

	@PostMapping("/savecode")
	public void saveCodeInputModal(@RequestBody Map<String, Object> data) {
		System.out.println("Code: " + data.get("code"));
		System.out.println("Node ID: " + data.get("nodeid"));

		String code = (String) data.get("code");
		String id = (String) data.get("nodeid");

		System.out.println("Code: " + code);
		System.out.println("Node ID: " + id);

		Optional<InputModal> nodeid = inputModalRepository.findById(Long.parseLong(id));
		if (nodeid.isPresent()) {
			InputModal inputModal = nodeid.get();
			inputModal.setCode(code);
			inputModalRepository.save(inputModal);
			System.out.println("Code updated successfully.");
		} else {
			System.out.println("Node ID not found.");
		}

	}

	@PostMapping("/getcode")
	public String getCodeInputModal(@RequestBody Map<String, Object> data) {
		String id = (String) data.get("nodeid");

		System.out.println("Node ID: " + id);
		String code="";
		Optional<InputModal> nodeid = inputModalRepository.findById(Long.parseLong(id));
		if (nodeid.isPresent()) {
			InputModal inputModal = nodeid.get();
			code = inputModal.getCode();
			System.out.println("Code returned"+code);
		} else {
			System.out.println("Node ID not found.");
		}
		return code;

	}

	@PostMapping("/getInputOutputOfNode")
	public Object inputOutputOfNode(@RequestBody Map<String, Object> data) {
		String id = (String) data.get("nodeid");

		System.out.println("Node ID: " + id);
		System.out.println("getInputOutputOfNode "+inputOutputModalRepository.findById(Long.parseLong(id)));
		return inputOutputModalRepository.findById(Long.parseLong(id));

	}

	@PostMapping("/buildexecuteconfig")
	public String executeNode(@RequestBody Map<String, Object> data) throws JsonProcessingException {

		String id = (String) data.get("nodeid");

		System.out.println("Node ID: " + id);
		String code="";
		Optional<InputModal> nodeid = inputModalRepository.findById(Long.parseLong(id));
		if (nodeid.isPresent()) {
			InputModal inputModal = nodeid.get();
			code = inputModal.getCode();
			System.out.println("Code returned"+code);
		} else {
			System.out.println("Node ID not found.");
		}
		List<ExecutionConfigModal> executionConfigModalList = executionConfigModalRepository.getConfigMapping(id);
		System.out.println("executionConfigModalList "+executionConfigModalList.toString());
		List<Map<String, Object>> job = new ArrayList<>();

		for (ExecutionConfigModal dataset : executionConfigModalList) {

			Map<String, Object> input = new HashMap<>();
			input.put("df-name", dataset.getInputDatasetName());
			input.put("type", dataset.getInputSourceType());
			input.put("identifier", dataset.getInputSourceType());
			input.put("table", dataset.getInputDatasetTableName());
			input.put("schema", dataset.getInputDatasetSchemaName());

			Map<String, Object> inputWrapper = new HashMap<>();
			inputWrapper.put("input", input);

			job.add(inputWrapper);
		}
		System.out.println("Job ===> "+job.toString());

		ExecutionConfigModal firstDataset = executionConfigModalList.get(0);

		Map<String, Object> transform = new HashMap<>();
		transform.put("df-name", firstDataset.getComputeDatasetName());
		transform.put("t_inputs", executionConfigModalList.stream()
				.map(ExecutionConfigModal::getInputDatasetName)
				.reduce((a, b) -> a + "," + b)
				.orElse(""));
		transform.put("query", code);
		transform.put("output", firstDataset.getOutputDataset());

		Map<String, Object> transformWrapper = new HashMap<>();
		transformWrapper.put("transform", transform);
		job.add(transformWrapper);
		System.out.println("Job ===> "+job.toString());

		// Process output
		Map<String, Object> output = new HashMap<>();
		output.put("df-name", firstDataset.getOutputDataset());
		output.put("type", firstDataset.getInputSourceType());
		output.put("identifier", firstDataset.getInputSourceType());
		output.put("table", firstDataset.getOutputTableName());
		output.put("schema", firstDataset.getOutputSchemaName());
		output.put("mode", "overwrite");
		Map<String, Object> outputWrapper = new HashMap<>();
		outputWrapper.put("output", output);
		job.add(outputWrapper);
		System.out.println("Job ===> "+job.toString());
		// Convert job list to YAML
		YAMLMapper yamlMapper = new YAMLMapper();
		String d = yamlMapper.writeValueAsString(job);
		System.out.println(d);
		return code;

	}




	@PutMapping("/addinputdataset")
	public Optional<InputModal> createInputDataset(@RequestBody InputModal inputModal) {
		inputModalRepository.save(inputModal);
		return inputModalRepository.findById(inputModal.getId());
	}

}
