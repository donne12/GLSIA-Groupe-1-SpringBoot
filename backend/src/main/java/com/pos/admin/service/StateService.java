package com.pos.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pos.admin.entity.State;

public interface StateService {

	public List<State> getStatesByCountry(Long countryId);

	public ResponseEntity<String> addState(Long countryId, State state);
	
}
