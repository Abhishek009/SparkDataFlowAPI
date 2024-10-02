package com.api.controller;

import java.util.List;
import java.util.Optional;

import com.api.model.InputOutputModal;
import com.api.model.TableInfo;
import com.api.model.Transformation;
import com.api.repository.InputOutputModalRepository;
import com.api.repository.TableInfoRepository;
import com.api.repository.TransformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.model.InputModal;
import com.api.repository.InputModalRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SDFController {

	@Autowired
	InputModalRepository inputModalRepository;
	@Autowired
	TransformationRepository transRepository;

	@Autowired
	InputOutputModalRepository inputOutputModalRepository;
	@Autowired
	TableInfoRepository tableInfoRepository;
	// Get the list of users
	@GetMapping("/allinputdataset")
	public List<InputModal> getAllInputDataset() {
		return inputModalRepository.findAll();
	}

	@GetMapping("/tableinfo")
	public List<TableInfo> getAllTableInfo() {
		return tableInfoRepository.findAll();
	}

	@GetMapping("/addTransform")
	public List<Transformation> setTransformation(@RequestBody Transformation trans) {
		transRepository.save(trans);
		return transRepository.findAll();
	}

	@PostMapping("/saveinputoutput")
	public List<InputOutputModal> setOutputInputMapping(@RequestBody InputOutputModal data) {
		inputOutputModalRepository.save(data);
		return inputOutputModalRepository.findAll();
	}


	@PutMapping("/addinputdataset")
	public List<InputModal> createInputDataset(@RequestBody InputModal user) {
		inputModalRepository.save(user);
		return inputModalRepository.findAll();
	}

	// Get the user based on the id
	@GetMapping("/getUser/{id}")
	public String getUserByID(@PathVariable long id) {
		return inputModalRepository.findById(id).toString();
	}

	// Delete the user based on the id
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable long id) {
		Optional<InputModal> userData = inputModalRepository.findById(id);
		if (userData.isPresent()) {
			inputModalRepository.deleteById(userData.get().getId());
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// Update the user base on the id
	/*@PutMapping("/upadateUser/{id}")
	public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable long id) {
		Optional<User> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			User userToUpdate = userData.get();
			userToUpdate.setEmail(user.getEmail());
			userToUpdate.setFirstName(user.getFirstName());
			userToUpdate.setLastName(user.getLastName());
			return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}*/
}
