package com.srm.services.rest.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerService {

	@RequestMapping(value="/login/auth",method=RequestMethod.GET)
	public ResponseEntity<String> loginAuth(){
		return new ResponseEntity<String>("Welcome to Srcommunications",HttpStatus.OK);
	}
	
}
