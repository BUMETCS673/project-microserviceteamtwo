package com.cs673olsum24.promanager.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.cs673olsum24.promanager.dao.*;
import com.cs673olsum24.promanager.service.UserProjectServices;

@Service
public class UserProjectServiceImplementation implements UserProjectServices {


	@Autowired
	private UserProjectDAO userProjectDAO;

	
	/**
	 * Retrieves all projects associated with a specific user and organizes them into a map.
	 *
	 * @param user_id The ID of the user whose projects are to be retrieved.
	 * @return A map containing a single entry where the key is "user_projects" and the value is a list of Object arrays,
	 *         each representing a project with various details.
	 * @throws JsonProcessingException If an error occurs during JSON processing.
	 */
	public Map<String, Object> getAllUserProjects(String user_id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		List<Object []> data = this.userProjectDAO.findAllUserProjects(user_id);
		map.put("user_projects", data);
		return map;
	}
	


}
