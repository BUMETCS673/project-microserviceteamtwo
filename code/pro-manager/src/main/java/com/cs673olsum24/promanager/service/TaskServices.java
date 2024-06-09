package com.cs673olsum24.promanager.service;



import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;



public interface TaskServices {
	
	public abstract Map<String, Object> getAllTasks(String id) throws JsonProcessingException;
	
}
