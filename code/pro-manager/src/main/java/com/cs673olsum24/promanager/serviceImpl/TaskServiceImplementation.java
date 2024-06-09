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

	@SuppressWarnings("unchecked")
public Map<String, Object> editTask(JSONObject each) {
    System.out.println(each.getOrDefault("updated_on", 0L));
    Map<String, Object> map = new HashMap<>();
    ProjectTasks t = new ProjectTasks();
    
    Object updatedOnObj = each.getOrDefault("updated_on", 0L);
    Long updatedOn;
    if (updatedOnObj instanceof Integer) {
        updatedOn = ((Integer) updatedOnObj).longValue();
    } else {
        updatedOn = (Long) updatedOnObj;
    }

    Object dueDateObj = each.getOrDefault("due_date", 0L);
    Long dueDate;
    if (dueDateObj instanceof Integer) {
        dueDate = ((Integer) dueDateObj).longValue();
    } else {
        dueDate = (Long) dueDateObj;
    }

    int assignedUserId = (int) each.getOrDefault("assigned_user_id", 0);

    t.setTask_id((String) each.getOrDefault("task_id", "NA"));
    t.setProject_id((String) each.getOrDefault("project_id", "NA"));
    t.setTask_name((String) each.getOrDefault("task_name", "NA"));
    t.setDescription(each.getOrDefault("description", "NA").toString());
    t.setStatus((String) each.getOrDefault("status", "To Do"));
    t.setPriority((String) each.getOrDefault("priority", "Medium"));
    t.setAssigned_user_id(assignedUserId);
    t.setDue_date(dueDate);
    t.setUpdated_on(updatedOn);

    try {
        this.taskDAO.editTask(t);
        map.put("Response", "Success");
    } catch (Exception e) {
        map.put("Response", "Failed");
    }

    return map;
}



}
