package com.cs673olsum24.promanager.service;



import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;



public interface UserProjectServices {
	
	public abstract Map<String, Object> getAllUserProjects(String user_id) throws JsonProcessingException;
	

	public abstract Map<String, Object> getAllProjectMembers(String project_id) throws JsonProcessingException;
	
	public abstract Map<String, Object> addProjectMember(HttpServletRequest request,Map<String, Object> payload) ;
	
}
