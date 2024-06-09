package com.cs673olsum24.promanager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

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
public class TaskController {
	
	@Autowired
	private TaskServices taskServices;
	
		/*
		 * Author : Pranjal Ekhande
		 * This is Method defines the api and its path. Method showcases all tasks retrival from database.
		 * This skeleton should be used to develope all the serive controller.
		 */		
		@GetMapping(value = "/task/project/getalltasks/{projectid}")
		public ResponseEntity<Object> getAllTaskProjects( @PathVariable("projectid") String id)  throws JsonProcessingException, ParseException {
			
			return new ResponseEntity<>(taskServices.getAllTasks(id),HttpStatus.OK);  	
	    }		
		/*
		 * Author : Dipayan 
		 * This is Method edits the Tasks for the given Project.
		 */	
		@PostMapping(value = "/task/project/editTask")
		 public ResponseEntity<Object> editTask(@RequestBody JSONObject body) {
		 	return new ResponseEntity<>(taskServices.editTask(body),HttpStatus.OK);
		 }

}
