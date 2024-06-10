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


public class UserProjectControllerTest {

	 @Mock
	 private UserProjectServices userProjectServices;

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
