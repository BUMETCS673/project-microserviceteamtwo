package com.cs673olsum24.promanager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import org.json.simple.parser.ParseException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.cs673olsum24.promanager.entity.*;

import com.cs673olsum24.promanager.service.*;


@RestController
@RequestMapping(value="/apiv1")
public class ProjectController {
	
	@Autowired
	private ProjectServices projectServices;
	
		/*
		 * Author : Pranjal Ekhande
		 * This is Method defines the api and its path. Method showcases dummy projects which are hardcoded right now.
		 * This will be removed after iteration 1
		 */
		@GetMapping(value = "/project/getprojects")
		public List<Project> getProjects()  {
			return Arrays.asList(new Project(1,"Create Educational Platform","Pranjal"));
	    }
	
	
		/*
		 * Author : Pranjal Ekhande
		 * This is Method defines the api and its path. Method showcases all projects retrival from database.
		 * This skeleton should be used to develope all the serive controller.
		 */
	
		@GetMapping(value = "/project/getallprojects")
		public ResponseEntity<Object> getAllProjects() throws JsonProcessingException, ParseException {
			return new ResponseEntity<>(projectServices.getAllProjects(),HttpStatus.OK);  	
	    }
		
	
//		@PostMapping(value = "/project/addprojects")
//		public ResponseEntity<Object> addProject(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
//			
//			System.out.println("In controller");
//			
//			return new ResponseEntity<>(projectServices.addProject(request,payload),HttpStatus.OK);  	
//	    }
//		
		 
		@GetMapping(value = "project/deleteproject/{id}")
	    public ResponseEntity<Object> deleteProject(@PathVariable("id") String id) {
			
			System.out.println(id);
			return new ResponseEntity<>(projectServices.deleteProject(id),HttpStatus.OK);
		}
		
//		@PostMapping(value = "project/safedeleteproject")
//		 public ResponseEntity<Object> editProject(@RequestBody JSONObject body) {
//		 	return new ResponseEntity<>(projectServices.safeDeleteProject(body),HttpStatus.OK);
//		 }
		
		
//		@PostMapping(value = "project/editproject")
//		 public ResponseEntity<Object> editSolarisCompute(@RequestBody JSONObject body) {
//		 	return new ResponseEntity<>(projectServices.safeDeleteProject(body),HttpStatus.OK);
//		 }
//		
	

		
}
