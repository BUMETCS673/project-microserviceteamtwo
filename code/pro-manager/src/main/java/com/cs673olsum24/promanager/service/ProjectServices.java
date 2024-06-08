package com.cs673olsum24.promanager.service;



import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;



public interface ProjectServices {
	
	public abstract Map<String, Object> getAllProjects() throws JsonProcessingException;
	
	public abstract Map<String, Object> getIdWiseProject(String id) throws JsonProcessingException;
	
	public abstract Map<String, Object> addProject(HttpServletRequest request,Map<String, Object> payload) ;
	
	public abstract Map<String, Object> deleteProject(String id); //permanant delete
	
//	public abstract Map<String, Object> safeDeleteProject(JSONObject body); // safe delete project

	public abstract Map<String, Object> editProject(JSONObject body);
	
}
