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
import com.cs673olsum24.promanager.entity.Projects;
import com.cs673olsum24.promanager.service.ProjectServices;


import com.cs673olsum24.promanager.utils.JsonUtils;

@Service
public class ProjectServiceImplementation implements ProjectServices {


	@Autowired
	private ProjectDAO projectDAO;

	public Map<String, Object> getAllProjects() throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		List<Object []> data = this.projectDAO.findAllProjects();
		map.put("projects", data);
		return map;
	}



	@SuppressWarnings("unchecked")
	public Map<String, Object> addProject(HttpServletRequest request, Map<String, Object> payload) {

		JSONObject data = JsonUtils.convertPayload(payload);
		JSONArray projects = (JSONArray) data.get("projects");

		Iterator<JSONObject> result = projects.iterator();
		while (result.hasNext()) {
			JSONObject each = (JSONObject) result.next();
			Long created_on = (Long) each.getOrDefault("created_on", 0);
			Long updated_on = (Long) each.getOrDefault("updated_on", 0);
			int owner_id = (int) each.getOrDefault("owner_id", 1);
			
			

			Projects p = new Projects();

			p.setProjectid((String) each.getOrDefault("project_id", "NA"));
			p.setProjectname((String) each.getOrDefault("projectname", "NA"));
			
			p.setDescription(each.getOrDefault("description", "NA").toString());
			p.setStatus((String) each.getOrDefault("status", "OPEN"));
			p.setType((String) each.getOrDefault("type", "NA"));
			p.setActive((boolean)each.getOrDefault("active","TRUE"));

			p.setCreated_on((long) created_on.doubleValue());
			p.setUpdated_on((long) updated_on.doubleValue());
			p.setOwner_id(owner_id);
			
			

			try {
				this.projectDAO.addProjects(p);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("Response", "OK");

		return map;
	}

	public Map<String, Object> deleteProject(String id)
	{
		Map<String, Object> map2 = new HashMap<>();

		this.projectDAO.deleteProject(id);

		map2.put("Response","Success");
		return map2;

	}




	//	@SuppressWarnings("unchecked")
	//	public Map<String, Object> safeDeleteProject(JSONObject each)
	//	{
	//		Map<String, Object> map = new HashMap<>();		
	//		Projects p = new Projects();	
	//		
	//		p.setProjectid((String) each.getOrDefault("projectid", "NA"));
	//		
	//
	//		try {
	//			this.projectDAO.safeDeleteProject(p);
	//			map.put("Response", "Success");
	//		}
	//		catch(Exception e)		
	//		{
	//			map.put("Response", "Failed");
	//		}
	//
	//    	return map;
	//	}

		@SuppressWarnings("unchecked")
		public Map<String, Object> editProject(JSONObject each)
		{
			System.out.println(each.getOrDefault("updated_on", 0L));
			Map<String, Object> map = new HashMap<>();		
			Projects p = new Projects();			
			Object updatedOnObj = each.getOrDefault("updated_on", 0L);
			Long updatedOn;
			if (updatedOnObj instanceof Integer) {
				updatedOn = ((Integer) updatedOnObj).longValue();
			} else {
				updatedOn = (Long) updatedOnObj;
			}

			int owner_id = (int) each.getOrDefault("owner_id", 1);
			
//			Object taskidObj = each.getOrDefault("taskid", 0L);
//			Long taskId;
//			if (taskidObj instanceof Integer) {
//				taskId = ((Integer) taskidObj).longValue();
//			} else {
//				taskId = (Long) taskidObj;
//			}

			p.setProjectid((String) each.getOrDefault("project_id", "NA"));
			p.setProjectname((String) each.getOrDefault("projectname", "NA"));
			
			p.setUpdated_on(updatedOn);
//			p.setTaskid(taskId);		
			p.setDescription(each.getOrDefault("description", "NA").toString());				
			p.setStatus((String) each.getOrDefault("status", "NA"));
			p.setType((String) each.getOrDefault("type", "NA"));
			p.setOwner_id(owner_id);
			
			try {
				this.projectDAO.editProject(p);
				map.put("Response", "Success");
			}
			catch(Exception e)		
			{
				map.put("Response", "Failed");
			}

	   	return map;
		}


}
