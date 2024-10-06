package com.api.controller;

import java.util.List;
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

	@PutMapping("/addinputdataset")
	public Optional<InputModal> createInputDataset(@RequestBody InputModal user) {
		inputModalRepository.save(user);
		return inputModalRepository.findById(user.getId());
	}

}
