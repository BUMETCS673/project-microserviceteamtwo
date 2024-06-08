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
import com.cs673olsum24.promanager.entity.ProjectTasks;
import com.cs673olsum24.promanager.service.TaskServices;


import com.cs673olsum24.promanager.utils.JsonUtils;

@Service
public class TaskServiceImplementation implements TaskServices {


	@Autowired
	private TaskDAO taskDAO;
	
	
	public Map<String, Object> getAllTasks(String id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		List<Object []> data = this.taskDAO.findAllProjectTasks(id);
		map.put("tasks", data);
		return map;
	}




}
