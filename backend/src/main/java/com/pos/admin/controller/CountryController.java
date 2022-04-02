package com.pos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.admin.entity.Country;
import com.pos.admin.service.CountryService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/countries")
	public ResponseEntity<List<Country>> getCountryList() {
		List<Country> countryList = countryService.getCountryList();
		return new ResponseEntity<List<Country>>(countryList,new HttpHeaders(),HttpStatus.OK);
	}

	@PostMapping("/country/save") 
	public ResponseEntity<String> save(@RequestBody Country country) {
		
		return countryService.addCountry(country);
	}
}
