package com.cs673olsum24.promanager.serviceImpl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

import com.cs673olsum24.promanager.dao.*;
import com.cs673olsum24.promanager.entity.ProjectUser;
import com.cs673olsum24.promanager.service.UserProjectServices;
import com.cs673olsum24.promanager.utils.JsonUtils;

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
		 List<Map<String, Object>>  data = this.userProjectDAO.findAllUserProjects(user_id);
		map.put("user_projects", data);
		return map;
	}
	
	
	public Map<String, Object> getAllProjectMembers(String project_id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		 List<Map<String, Object>>  data = this.userProjectDAO.findAllProjectMembers(project_id);
		map.put("project_members", data);
		return map;
	}


	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> addProjectMember(HttpServletRequest request, Map<String, Object> payload) {

		JSONObject data = JsonUtils.convertPayload(payload);
		JSONArray user = (JSONArray) data.get("project_member");
		

		Iterator<JSONObject> result = user.iterator();
		while (result.hasNext()) {
			JSONObject each = (JSONObject) result.next();
		
			Long createdtAt = (Long) each.getOrDefault("created_at", 0);
				
			Long updatedAt = (Long) each.getOrDefault("updated_at", 0);
			
				
			Object projUserIdObj = each.getOrDefault("PROJECT_USER_ID", 1);
			int proj_user_id;

			if (projUserIdObj instanceof Long) {
				proj_user_id = ((Long) projUserIdObj).intValue();
			} else if (projUserIdObj instanceof Integer) {
				proj_user_id = (Integer) projUserIdObj;
			} else {
				proj_user_id = 1; // Default value
			}
			
			Object UserIdObj = each.getOrDefault("USER_ID", 1);
			int user_id;

			if (UserIdObj instanceof Long) {
				user_id = ((Long) UserIdObj).intValue();
			} else if (UserIdObj instanceof Integer) {
				user_id = (Integer) UserIdObj;
			} else {
				user_id = 1; // Default value
			}
			

			ProjectUser p = new ProjectUser();
			

			p.setPROJECT_USER_ID(proj_user_id);
			p.setPROJECT_ID((String) each.getOrDefault("PROJECT_ID", "NA"));
			p.setUSER_ID(user_id);
			p.setROLE((String) each.getOrDefault("ROLE", "COLLABORATOR"));
			p.setCreated_at( createdtAt);
			p.setUpdated_at(updatedAt);
			
			

			try {
				this.userProjectDAO.addProjectMember(p);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("Response", "OK");

		return map;
	}

}
