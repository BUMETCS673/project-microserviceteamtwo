package com.cs673olsum24.promanager.controller;

import java.util.Arrays;
import java.util.List;

import com.cs673olsum24.promanager.entity.*;

//import org.json.simple.parser.ParseException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/apiv1")
public class ProjectController {
	
	/*
	 * Author : Pranjal Ekhande
	 * This is Method defines the api and its path. Method showcases dummy projects which are hardcoded right now.
	 */
	@GetMapping(value = "/project/getprojects")
	public List<Project> getAllProjects()  {
		return Arrays.asList(new Project(1,"Create Educational Platform","Pranjal"));
    }
	
	
	
	/*
	 * Author : Pranjal Ekhande
	 * This is Method defines the api and its path. Method showcases all projects retrival from database.
	 * This skeleton should be used to develope all the serive controller.
	 */
	//	@GetMapping(value = "/project/getprojects")
	//	public ResponseEntity<Object> getAllProjects() throws JsonProcessingException, ParseException {
	//		return new ResponseEntity<>(projectService.getProject(),HttpStatus.OK);  	
	//    }
		
		
	//	
	//		@PostMapping(value = "/project/addprojects")
	//		public ResponseEntity<Object> addProjects(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
	//		
	//		return new ResponseEntity<>(projectService.addProject(request,payload),HttpStatus.OK);  	
	//    }

}
