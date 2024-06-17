package com.cs673olsum24.promanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.cs673olsum24.promanager.service.*;



@RestController
@RequestMapping(value="/apiv1")
public class UserProjectController {
	
	@Autowired
	private UserProjectServices userProjectServices;
	

		/*
		 * Author: Pranjal Ekhande
		 * 
		 * This method defines the API endpoint and its path. It handles HTTP GET requests for retrieving 
		 * all projects associated with a specific user from the database.
		 * 
		 * @param user_id The ID of the user whose projects are to be retrieved.
		 * @return A ResponseEntity containing a map with the user's projects and an HTTP status of OK.
		 * @throws JsonProcessingException If an error occurs during JSON processing.
		 * @throws ParseException If an error occurs during parsing.
		 */
	
		@GetMapping(value = "/user/getalluserprojects/{user_id}")
		public ResponseEntity<Object> getAllUserProjects(@PathVariable("user_id") String user_id) throws JsonProcessingException, ParseException {
			
			return new ResponseEntity<>(userProjectServices.getAllUserProjects(user_id),HttpStatus.OK);  		    
		}	
		
		
		@GetMapping(value = "/user/getallprojectmembers/{project_id}")
		public ResponseEntity<Object> getAllProjectMembers(@PathVariable("project_id") String project_id) throws JsonProcessingException, ParseException {
			
			return new ResponseEntity<>(userProjectServices.getAllProjectMembers(project_id),HttpStatus.OK);  		    
		}	
		
		
		@PostMapping(value = "/user/projectmember/")
		public ResponseEntity<Object> addProjectMember(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
			return new ResponseEntity<>(userProjectServices.addProjectMember(request,payload),HttpStatus.OK);  	
	    }
		
}






