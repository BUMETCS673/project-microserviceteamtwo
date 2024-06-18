package com.cs673olsum24.promanager.service;



import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;



public interface TaskServices {
	
	public abstract Map<String, Object> getAllTasks(String id) throws JsonProcessingException;

	public abstract Map<String, Object> editTask(JSONObject body);

	public abstract Map<String, Object> addTasks(HttpServletRequest request,Map<String, Object> payload) ;
	
	public abstract Map<String, Object> deleteTask(String id); //permanant delete
}
