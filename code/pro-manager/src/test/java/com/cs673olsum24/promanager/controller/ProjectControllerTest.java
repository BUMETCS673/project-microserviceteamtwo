package com.cs673olsum24.promanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cs673olsum24.promanager.ProManagerApplication;
import com.cs673olsum24.promanager.entity.Projects;
import com.cs673olsum24.promanager.service.ProjectServices;
import com.cs673olsum24.promanager.serviceImpl.ProjectServiceImplementation;
import com.cs673olsum24.promanager.utils.ProjectFieldIndexes;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProManagerApplication.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectControllerTest {

  @Autowired private ProjectServices projectServices;

  @Autowired private ProjectServiceImplementation projectServiceImplementation;

  private String testProjectId = "project005";

  @BeforeEach
  public void setup() throws JsonProcessingException {
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
  
  @AfterEach
  public void cleanup() {
      try {
          // Fetch all projects
          Map<String, Object> response = projectServices.getAllProjects();
          if (response != null && response.containsKey("projects")) {
              List<Projects> projects = (List<Projects>) response.get("projects");
              // Delete each project created during the test
              for (Projects project : projects) {
                  String projectId = project.getProjectid();
                  projectServices.deleteProject(projectId);
              }
          }
      } catch (Exception e) {
          // Ignore if there was an error fetching or deleting projects
      }
  }

  @Test
  @Order(1)
  public void testRetrieveProject() {
    String projectId = testProjectId;

    try {
      Map<String, Object> response = projectServices.getIdWiseProject(projectId);

      // Assert that the project was found and the response is not null
      assertNotNull(response);
      assertNotNull(response.get("project"));
      List<Map<String, Object>> projects = (List<Map<String, Object>>) response.get("project");
      assertFalse(projects.isEmpty());

      // Assuming the project details are in the first object of the array
      Map<String, Object> projectDetails = projects.get(0);
      assertEquals("Initial Test Project", projectDetails.get("projectname"));
      assertEquals("Initial description", projectDetails.get("description"));
      assertEquals("Inactive", projectDetails.get("status"));
      assertEquals("Initial", projectDetails.get("type"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(2)
  public void testGetAllProjects() {
    try {
      // Use the service to retrieve all projects
      Map<String, Object> response = projectServices.getAllProjects();

      // Assert that the response is not null and contains the "projects" key
      assertNotNull(response);
      assertNotNull(response.get("projects"));
      assertTrue(response.get("projects") instanceof List);

      // You can further assert specific details about the projects if needed
      List<Projects> projects = (List<Projects>) response.get("projects");
      assertTrue(!projects.isEmpty(), "Projects list should not be empty");

      // Verify details of the test project
      Projects projectDetails =
          projects.stream()
              .filter(p -> p.getProjectid().equals(testProjectId))
              .findFirst()
              .orElse(null);

      assertNotNull(projectDetails);
      assertEquals("Initial Test Project", projectDetails.getProjectname());
      assertEquals("Initial description", projectDetails.getDescription());
      assertEquals("Inactive", projectDetails.getStatus());
      assertEquals("Initial", projectDetails.getType());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(3)
  public void testRetrieveNonExistentProject() {
    String projectId = "nonexistent";

    try {
      Map<String, Object> response = projectServices.getIdWiseProject(projectId);

      // Assert that the project was not found
      assertTrue(
          response.isEmpty()
              || (response.containsKey("project")
                  && ((List<?>) response.get("project")).isEmpty()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(4)
  public void testDeleteProject() {
    String projectId = testProjectId;

    // Call the service method to delete the project
    Map<String, Object> response = projectServices.deleteProject(projectId);

    // Assert the response
    assertNotNull(response);
    assertEquals("Success", response.get("Response"));

    // Verify that the project is deleted
    try {
      Map<String, Object> deletedProjectResponse = projectServices.getIdWiseProject(projectId);
      assertTrue(
          deletedProjectResponse.isEmpty()
              || (deletedProjectResponse.containsKey("project")
                  && ((List<?>) deletedProjectResponse.get("project")).isEmpty()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(5)
  public void testAddProject() {
    // Prepare payload
    String newProjectId = "proj_006";
    Map<String, Object> payload = new HashMap<>();
    payload.put(
        "projects",
        new Object[] {
          new HashMap<String, Object>() {
            {
              put("project_id", newProjectId);
              put("projectname", "Test Project");
              put("owner_id", 123);
              put("description", "A test project");
              put("created_on", 1622547800L);
              put("updated_on", 1622547800L);
              put("status", "Active");
              put("type", "Test");
              put("active", true);
            }
          }
        });

    // Call the service method
    Map<String, Object> response = projectServices.addProject(null, payload);

    // Assert the response
    assertNotNull(response);
    assertEquals("OK", response.get("Response"));

    // Verify that the project was added
    try {
      Map<String, Object> addedProjectResponse = projectServices.getIdWiseProject(newProjectId);
      assertNotNull(addedProjectResponse);
      assertNotNull(addedProjectResponse.get("project"));
      List<Map<String, Object>> projects =
          (List<Map<String, Object>>) addedProjectResponse.get("project");
      assertFalse(projects.isEmpty());

      // Assuming the project details are in the first object of the array
      Map<String, Object> projectDetails = projects.get(0);
      assertEquals("Test Project", projectDetails.get("projectname"));
      assertEquals("A test project", projectDetails.get("description"));
      assertEquals("Active", projectDetails.get("status"));
      assertEquals("Test", projectDetails.get("type"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(6)
  public void testEditProject() {
    // Prepare payload
    Map<String, Object> payload = new HashMap<>();
    payload.put("project_id", testProjectId);
    payload.put("projectname", "Updated Test Project");
    payload.put("owner_id", 123);
    payload.put("description", "An updated description");
    payload.put("updated_on", 1622547800L);
    payload.put("status", "Active");
    payload.put("type", "Test");

    // Convert payload to JSONObject
    JSONObject jsonPayload = new JSONObject(payload);

    // Call the service method
    Map<String, Object> response = projectServices.editProject(jsonPayload);

    // Assert the response
    assertNotNull(response);
    assertEquals("Success", response.get("Response"));

    // Verify that the project details were updated
    try {
      Map<String, Object> updatedProjectResponse = projectServices.getIdWiseProject(testProjectId);
      assertNotNull(updatedProjectResponse);
      assertNotNull(updatedProjectResponse.get("project"));
      List<Map<String, Object>> projects =
          (List<Map<String, Object>>) updatedProjectResponse.get("project");
      assertFalse(projects.isEmpty());

      // Assuming the project details are in the first object of the array
      Map<String, Object> projectDetails = projects.get(0);
      assertEquals("Updated Test Project", projectDetails.get("projectname"));
      assertEquals("An updated description", projectDetails.get("description"));
      assertEquals("Active", projectDetails.get("status"));
      assertEquals("Test", projectDetails.get("type"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(7)
  public void testAddProjects() {
    // Prepare payload
    Map<String, Object> payload = new HashMap<>();
    payload.put(
        "projects",
        new Object[] {
          new HashMap<String, Object>() {
            {
              put("project_id", "proj_007");
              put("projectname", "Test Project 2");
              put("owner_id", 124);
              put("description", "Another test project");
              put("created_on", 1622547800L);
              put("updated_on", 1622547800L);
              put("status", "Inactive");
              put("type", "Initial");
              put("active", true);
            }
          },
          new HashMap<String, Object>() {
            {
              put("project_id", "proj_008");
              put("projectname", "Test Project 3");
              put("owner_id", 125);
              put("description", "Yet another test project");
              put("created_on", 1622547800L);
              put("updated_on", 1622547800L);
              put("status", "Active");
              put("type", "Initial");
              put("active", true);
            }
          }
        });

    // Call the service method
    Map<String, Object> response = projectServiceImplementation.addProjects(null, payload);

    // Assert the response
    assertNotNull(response);
    assertEquals("OK", response.get("Response"));

    // Verify that the projects were added
    try {
      Map<String, Object> addedProjectResponse1 = projectServices.getIdWiseProject("proj_007");
      assertNotNull(addedProjectResponse1);
      assertNotNull(addedProjectResponse1.get("project"));
      List<Map<String, Object>> projects1 =
          (List<Map<String, Object>>) addedProjectResponse1.get("project");
      assertFalse(projects1.isEmpty());

      Map<String, Object> projectDetails1 = projects1.get(0);
      assertEquals("Test Project 2", projectDetails1.get("projectname"));
      assertEquals("Another test project", projectDetails1.get("description"));
      assertEquals("Inactive", projectDetails1.get("status"));
      assertEquals("Initial", projectDetails1.get("type"));

      Map<String, Object> addedProjectResponse2 = projectServices.getIdWiseProject("proj_008");
      assertNotNull(addedProjectResponse2);
      assertNotNull(addedProjectResponse2.get("project"));
      List<Map<String, Object>> projects2 =
          (List<Map<String, Object>>) addedProjectResponse2.get("project");
      assertFalse(projects2.isEmpty());

      Map<String, Object> projectDetails2 = projects2.get(0);
      assertEquals("Test Project 3", projectDetails2.get("projectname"));
      assertEquals("Yet another test project", projectDetails2.get("description"));
      assertEquals("Active", projectDetails2.get("status"));
      assertEquals("Initial", projectDetails2.get("type"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(8)
  public void testParseProject() {
    // Create a sample JSONObject to parse
    JSONObject projectJson = new JSONObject();
    projectJson.put("project_id", "proj_009");
    projectJson.put("projectname", "Parsed Project");
    projectJson.put("owner_id", 126);
    projectJson.put("description", "Parsed project description");
    projectJson.put("created_on", 1622547800L);
    projectJson.put("updated_on", 1622547800L);
    projectJson.put("status", "Inactive");
    projectJson.put("type", "Initial");
    projectJson.put("active", true);

    // Parse the project
    Projects project = projectServiceImplementation.parseProject(projectJson);

    // Assert the parsed project fields
    assertNotNull(project);
    assertEquals("proj_009", project.getProjectid());
    assertEquals("Parsed Project", project.getProjectname());
    assertEquals(126, project.getOwner_id());
    assertEquals("Parsed project description", project.getDescription());
    assertEquals(Long.valueOf(1622547800L), project.getCreated_on());
    assertEquals(Long.valueOf(1622547800L), project.getUpdated_on());
    assertEquals("Inactive", project.getStatus());
    assertEquals("Initial", project.getType());
    assertTrue(project.getActive());
  }
}
