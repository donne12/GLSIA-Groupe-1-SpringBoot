package com.pos.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pos.admin.entity.Country;

public interface CountryService {

	public ResponseEntity<String> addCountry(Country country);

	public List<Country> getCountryList();

}
