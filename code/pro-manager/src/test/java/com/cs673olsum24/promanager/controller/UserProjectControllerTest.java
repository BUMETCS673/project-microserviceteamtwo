package com.cs673olsum24.promanager.controller;
import com.cs673olsum24.promanager.service.UserProjectServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.cs673olsum24.promanager.ProManagerApplication;
import com.cs673olsum24.promanager.service.ProjectServices;

import com.cs673olsum24.promanager.service.TaskServices;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/*
 * Author: Pranjal Ekhande
 * 
 * This method defines the API endpoint and its path. It handles HTTP GET requests for retrieving 
 * all projects associated with a specific user from the database.
 * 
 * @param user_id The ID of the user whose projects are to be retrieved.
 * @return A ResponseEntity containing a map with the user's projects and an HTTP status of OK.
 * @throws JsonProcessingException If an error occurs during JSON processing.
 * @throws ParseException If an error occurs during parsing.
 */


@SpringBootTest(classes = ProManagerApplication.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserProjectControllerTest {

	@Autowired
	 private UserProjectServices userProjectServices;
	 
	 
	   
	   @Autowired private ProjectServices projectServices;
	   
	   
	   private int testPROJECT_USER_ID = 2;
	   
	   private String testProjectId = "proj_002";
	   

	   @BeforeEach
	   public void setup() throws JsonProcessingException {
		   
		   
		   Map<String, Object> payload_projects = new HashMap<>();
		   payload_projects.put(
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
		     projectServices.addProject(null, payload_projects);
		     
		     
		     // Prepare and insert a test project
		     

			 Map<String, Object> payload_users = new HashMap<>();
			 payload_users.put(
			         "projects",
			         new Object[] {
			           new HashMap<String, Object>() {
			             {
			               put("PROJECT_USER_ID", testPROJECT_USER_ID);
			               put("PROJECT_ID", testProjectId);
			               put("USER_ID", 1);
			               put("ROLE", "Initial Viewer");
			               put("created_at", 1718390619L);
			               put("updated_at", 1718390619L);
			              
			             
			             }
			           }
			         });
		     userProjectServices.addProjectMember(null, payload_users);        
	   }
	   
	   
	    @InjectMocks
	    private UserProjectController userProjectController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
    
    @Test
    void testGetAllUserProjects() throws JsonProcessingException, ParseException {
        // Arrange
        String userId = "3";
        Object[] project = {"proj_001", "Project Alpha", "This is a test project.", 123, "User One", 1622548800L, 1622645200L, "ongoing", "development", true};
        Map<String, Object> mockData = new HashMap<>();
        mockData.put("user_projects", List.of(project));
        when(userProjectServices.getAllUserProjects(userId)).thenReturn(mockData);

        // Act
        ResponseEntity<Object> response = userProjectController.getAllUserProjects(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockData, response.getBody());
    }
 
}
