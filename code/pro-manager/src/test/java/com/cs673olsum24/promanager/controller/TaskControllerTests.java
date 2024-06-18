package com.cs673olsum24.promanager.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.cs673olsum24.promanager.ProManagerApplication;
import com.cs673olsum24.promanager.service.ProjectServices;
import com.cs673olsum24.promanager.service.TaskServices;
import com.cs673olsum24.promanager.utils.TaskFieldIndexes;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ProManagerApplication.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskControllerTests {
	

	   @Autowired private TaskServices taskServices;
	   
	   @Autowired private ProjectServices projectServices;
	   

	   private String testTaskId = "task_001";
	   
	   private String testProjectId = "proj_002";
	   
	   
	   @BeforeEach
	   public void setupProject() throws JsonProcessingException {
	     // Prepare and insert a test project
	     Map<String, Object> payload = new HashMap<>();
	     payload.put(
	         "projects",
	         new Object[] {
	           new HashMap<String, Object>() {
	             {
	               put("project_id", testProjectId);
	               put("projectname", "Initial Test Project");
	               put("owner_id", 123);
	               put("description", "Initial description");
	               put("created_on", 1622547800L);
	               put("updated_on", 1622547800L);
	               put("status", "Inactive");
	               put("type", "Initial");
	               put("active", true);
	             }
	           }
	         });

	     // Insert the project
	     projectServices.addProject(null, payload);
	   }
	   
	   @BeforeEach
	   public void setup() throws JsonProcessingException {
	     // Prepare and insert a test project
	     
		 Map<String, Object> payload = new HashMap<>();
	     payload.put(
	         "tasks",
	         new Object[] {
	           new HashMap<String, Object>() {
	             {
	               put("task_id", "task_002");
	               put("project_id", "proj_002");
	               put("task_name", "Initial Test Task");	             
	               put("description", "Initial description");
	               put("status", "To Do");
	              
	               put("priority", "Low");	               
	              
	              
	               put("assigned_user_id", 1);
	               put("due_date", 1717941368L);
	               put("created_on", 1717941368L);
	               put("updated_on", 1717941368L);
	             }
	           }
	         });
	     taskServices.addTask(null, payload);
	     
	     
	     
	   }
	   

	   @Test
	   @Order(1)
	   public void testRetrieveProject() {
	     
	     String projectId = "proj_002";

	     try {
	       Map<String, Object> response = taskServices.getAllTasks(projectId);
	       
	       System.out.println("proj_001");
	       System.out.println(response.get("tasks"));
	       
	       // Assert that the project was found and the response is not null
	       assertNotNull(response);
	       assertNotNull(response.get("tasks"));
	       List<?> tasks = (List<?>) response.get("tasks");
	       assertFalse(tasks.isEmpty());
	       System.out.println("In Test here");

	       // Assuming the project details are in the first object of the array
	       Object[] taskDetails = (Object[]) tasks.get(0);
	       assertEquals("Initial Test Task", taskDetails[TaskFieldIndexes.TASK_NAME]);
	       assertEquals("Initial description", taskDetails[TaskFieldIndexes.DESCRIPTION]);
	       assertEquals("To Do", taskDetails[TaskFieldIndexes.STATUS]);
	       assertEquals("Low", taskDetails[TaskFieldIndexes.PRIORITY]);
	     } catch (JsonProcessingException e) {
	       e.printStackTrace();
	       assertNotNull(null, "JsonProcessingException was thrown");
	     }
	   }
	   
	   @Test
	   @Order(2)
	   public void testGetAllProjects() {
		   
		 String projectId = "proj_002";
	     try {
	       // Use the service to retrieve all projects
	       Map<String, Object> response = taskServices.getAllTasks(projectId);
	       
	       System.out.print("proj_001");
	       System.out.print(response.get("tasks"));

	       // Assert that the response is not null and contains the "projects" key
	       assertNotNull(response);
	       assertNotNull(response.get("tasks"));
	       assertTrue(response.get("tasks") instanceof List);

	       // You can further assert specific details about the projects if needed
	       List<?> tasks = (List<?>) response.get("tasks");
	       assertTrue(!tasks.isEmpty(), "Tasks list should not be empty");

	       // Verify details of the test Tasks
	       Object[] taskDetails =
	    		   		(Object[])
	    		   		tasks.stream()
	                   .filter(p -> ((Object[]) p)[TaskFieldIndexes.TASK_ID].equals(testTaskId))
	                   .findFirst()
	                   .orElse(null);

	       assertNotNull(taskDetails);
	       assertEquals("Initial Test Task", taskDetails[TaskFieldIndexes.TASK_NAME]);
	       assertEquals("Initial Test Project Task", taskDetails[TaskFieldIndexes.DESCRIPTION]);
	       assertEquals("To Do", taskDetails[TaskFieldIndexes.STATUS]);
	       assertEquals("Low", taskDetails[TaskFieldIndexes.PRIORITY]);
	     } catch (JsonProcessingException e) {
	       e.printStackTrace();
	       assertNotNull(null, "JsonProcessingException was thrown");
	     }
	   }
	   

}
