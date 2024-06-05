package com.cs673olsum24.promanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cs673olsum24.promanager.service.ProjectServices;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectServices projectServices;

    @Test
    public void testDeleteProject() throws Exception {
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("Response", "Project deleted successfully");

        String projectId = "1";

        when(projectServices.deleteProject(projectId)).thenReturn(mockResponse);

        mockMvc.perform(delete("/apiv1/project/deleteproject/{id}", projectId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Response").value("Project deleted successfully"));
    }
}
