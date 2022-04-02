package com.pos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.admin.entity.State;
import com.pos.admin.service.StateService;

@RestController
@RequestMapping("/api/state")
@CrossOrigin("http://localhost:4200")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@GetMapping("/{countryId}")
	public ResponseEntity<List<State>> getStatesByCountry(@PathVariable("countryId")Long countryId) {
		List<State> stateList=stateService.getStatesByCountry(countryId);
		return new ResponseEntity<List<State>>(stateList,new HttpHeaders(),HttpStatus.OK);
	}

	@PostMapping("/country/{id}/save")
	public ResponseEntity<String> save(@PathVariable Long id, @RequestBody State state) {
		return stateService.addState(id, state);
	}
}
