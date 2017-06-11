package com.srm.services.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srm.services.entity.Users;
import com.srm.services.rest.services.TransactionService;
import com.srm.services.rest.servicesImpl.TransactionServiceImpl;
/*
 *@Auth:prasad
 *@date:11/06/2017
 *@description:main entry restcontrollerservice class
 */
@RestController
public class RestControllerService {

	private final static Logger LOGGER=LoggerFactory.getLogger(RestControllerService.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value="/login/auth",method=RequestMethod.GET)
	public ResponseEntity<String> loginAuth() throws URISyntaxException{
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		LOGGER.info("Username{}"+dbUri.getUserInfo().split(":")[0]);
		LOGGER.info("Password{}"+dbUri.getUserInfo().split(":")[1]);
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		LOGGER.info("DBURL{}"+dbUrl);
		List<Users> users=transactionService.findAllUsers();
		LOGGER.info("userInfo{}"+users);
		return new ResponseEntity<String>("Welcome to Srcommunications",HttpStatus.OK);
	}
	
}
