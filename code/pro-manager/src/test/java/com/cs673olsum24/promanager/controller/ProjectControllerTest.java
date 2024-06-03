package com.cs673olsum24.promanager.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cs673olsum24.promanager.service.ProjectServices;

import com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectServices projectServices;

    @Test
    public void testGetAllProjects() throws Exception {
        Map<String, Object> projectsMap = new HashMap<>();
        projectsMap.put("projects", new Object[] { /* Mock project data */ });

        when(projectServices.getAllProjects()).thenReturn(projectsMap);

        mockMvc.perform(get("/apiv1/project/getallprojects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projects").exists());
    }
    
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddProject() throws Exception {
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("Response", "OK");

        
        Map<String, Object> payload = new HashMap<>();
        payload.put("projects", new Object[] {
            new HashMap<String, Object>() {{
                put("projectid", "1");
                put("projectname", "Test Project");
                put("userid", "123");
                put("taskid", 456);
                put("description", "A test project");
                put("created_on", 1622547800L);
                put("updated_on", 1622547800L);
                put("status", "Active");
                put("type", "Test");
            }}
        });

        
        when(projectServices.addProject(null, payload)).thenReturn(mockResponse);

        mockMvc.perform(post("/apiv1/project/addprojects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Response").value("OK"));
    }
}


