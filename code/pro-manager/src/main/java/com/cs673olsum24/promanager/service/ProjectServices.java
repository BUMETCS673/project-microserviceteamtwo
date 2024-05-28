package com.cs673olsum24.promanager.service;



import java.util.Map;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;



public interface ProjectServices {
	
	public abstract Map<String, Object> getAllProjects() throws JsonProcessingException, ParseException;
	
	public abstract Map<String, Object> addProject(HttpServletRequest request,Map<String, Object> payload) ;
	

}
