package com.cs673olsum24.promanager.serviceImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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
		
	public Map<String, Object> getAllProjects() throws JsonProcessingException, ParseException {
		Map<String, Object> map = new HashMap<>();
		List<Object []> data = this.projectDAO.findAllProjects();
    	map.put("projects", data);
    	return map;
    }
		
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> addProject(HttpServletRequest request,Map<String, Object> payload) {
		
		System.out.println("In Service Implementation");
		
		JSONObject data = JsonUtils.convertPayload(payload);	
		JSONArray projects=(JSONArray) data.get("projects");	
		Iterator<JSONObject> result = projects.iterator();			
			while(result.hasNext())
			{		
				JSONObject each = (JSONObject) result.next();
				Double created_on = (Double) each.getOrDefault("created_on", 0);
				Double updated_on = (Double) each.getOrDefault("updated_on", 0);
				Double taskid = (Double) each.getOrDefault("taskid", 0);
				
				Projects p = new Projects();							
				
				p.setProjectid((String) each.getOrDefault("projectid", "NA"));
				p.setProjectname((String) each.getOrDefault("projectname", "NA"));
				p.setUserid((String) each.getOrDefault("userid", "NA"));				
				p.setCreated_on((double) created_on.doubleValue());
				p.setUpdated_on((double) updated_on.doubleValue());
				p.setTaskid((double) taskid.doubleValue());				
				p.setDescription(each.getOrDefault("description", "NA").toString());				
				p.setStatus((String) each.getOrDefault("status", "NA"));
				p.setType((String) each.getOrDefault("type", "NA"));
				
				try {
				this.projectDAO.addProjects(p);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		
		Map<String, Object> map = new HashMap<>();
		map.put("Response", "OK");
		
		return map;
    }





}
