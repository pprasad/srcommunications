package com.srm.services.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srm.services.dto.Response;
import com.srm.services.dto.ServiceConstant;
import com.srm.services.entity.Category;
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
		List<Users> users=transactionService.findAllUsers();
		LOGGER.info("userInfo{}"+users);
		return new ResponseEntity<String>("Welcome to Srcommunications",HttpStatus.OK);
	}
	@RequestMapping(value="/saveCategory",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<String>> saveCategory(@RequestBody Category category){
		  Response<String> resposne=new Response<String>();
		  try{
			   Boolean flag=transactionService.saveOrupdateCategory(category);
			   resposne.setResult(flag?ServiceConstant.RESULT_SUCCESS:ServiceConstant.RESULT_FAIURE);
		  }catch(Exception ex){
			  resposne.setResult(ServiceConstant.RESULT_FAIURE);
		  }
		  return new ResponseEntity<Response<String>>(resposne,HttpStatus.OK);
	}

	@RequestMapping(value="/getCategorys",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<Category>>> getCategorys(){
		Response<List<Category>> response=new Response<List<Category>>();
		try{
			List<Category> categories=transactionService.getCategory();
			response.setResult(ServiceConstant.RESULT_SUCCESS);
			response.setResponse(categories);
		}catch(Exception ex){
			response.setResult(ServiceConstant.RESULT_FAIURE);
		}
		return new ResponseEntity<Response<List<Category>>>(response,HttpStatus.OK);
	}
	@RequestMapping(value="/deleteCategorys/{cateId}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<String>> getDeleteCategory(@PathVariable("cateId") Long cateId){
		Response<String> response=new Response<String>();
		try{
			Boolean flag=transactionService.deleteCategory(cateId);
			response.setResult(flag?ServiceConstant.RESULT_SUCCESS:ServiceConstant.RESULT_FAIURE);
		}catch(Exception ex){
			response.setResult(ServiceConstant.RESULT_FAIURE);
		}
		return new ResponseEntity<Response<String>>(response,HttpStatus.OK);
	}
}
