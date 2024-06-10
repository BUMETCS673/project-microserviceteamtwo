package com.cs673olsum24.promanager.service;



import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;



public interface UserProjectServices {
	
	public abstract Map<String, Object> getAllUserProjects(String user_id) throws JsonProcessingException;
	
}
