package com.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.api.model.*;
import com.api.repository.*;
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
	public List<InputOutputModal> setOutputInputMapping(@RequestBody InputOutputModal data) {
		System.out.println(data);
		inputOutputModalRepository.save(data);
		return inputOutputModalRepository.findAll();
	}

	@PostMapping("/savecode")
	public void saveCodeInputModal(@RequestBody Map<String, Object> data) {
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

	@PutMapping("/addinputdataset")
	public Optional<InputModal> createInputDataset(@RequestBody InputModal inputModal) {
		inputModalRepository.save(inputModal);
		return inputModalRepository.findById(inputModal.getId());
	}

}
