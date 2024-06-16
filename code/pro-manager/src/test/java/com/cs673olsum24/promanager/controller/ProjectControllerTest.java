package com.cs673olsum24.promanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.cs673olsum24.promanager.ProManagerApplication;
import com.cs673olsum24.promanager.service.ProjectServices;
import com.cs673olsum24.promanager.serviceImpl.utils.ProjectFieldIndexes;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProManagerApplication.class)
@ActiveProfiles("test")
public class ProjectControllerTest {

  @Autowired private ProjectServices projectServices;

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

  @Test
  public void testRetrieveProject() {
    String projectId = testProjectId;

    try {
      Map<String, Object> response = projectServices.getIdWiseProject(projectId);

      // Assert that the project was found and the response is not null
      assertNotNull(response);
      assertNotNull(response.get("project"));
      List<?> projects = (List<?>) response.get("project");
      assertFalse(projects.isEmpty());

      // Assuming the project details are in the first object of the array
      Object[] projectDetails = (Object[]) projects.get(0);
      assertEquals("Initial Test Project", projectDetails[ProjectFieldIndexes.PROJECT_NAME]);
      assertEquals("Initial description", projectDetails[ProjectFieldIndexes.DESCRIPTION]);
      assertEquals("Inactive", projectDetails[ProjectFieldIndexes.STATUS]);
      assertEquals("Initial", projectDetails[ProjectFieldIndexes.TYPE]);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
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
      List<?> projects = (List<?>) addedProjectResponse.get("project");
      assertFalse(projects.isEmpty());

      // Assuming the project details are in the first object of the array
      Object[] projectDetails = (Object[]) projects.get(0);
      assertEquals("Test Project", projectDetails[ProjectFieldIndexes.PROJECT_NAME]);
      assertEquals("A test project", projectDetails[ProjectFieldIndexes.DESCRIPTION]);
      assertEquals("Active", projectDetails[ProjectFieldIndexes.STATUS]);
      assertEquals("Test", projectDetails[ProjectFieldIndexes.TYPE]);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
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
      List<?> projects = (List<?>) updatedProjectResponse.get("project");
      assertFalse(projects.isEmpty());

      // Assuming the project details are in the first object of the array
      Object[] projectDetails = (Object[]) projects.get(0);
      assertEquals("Updated Test Project", projectDetails[ProjectFieldIndexes.PROJECT_NAME]);
      assertEquals("An updated description", projectDetails[ProjectFieldIndexes.DESCRIPTION]);
      assertEquals("Active", projectDetails[ProjectFieldIndexes.STATUS]);
      assertEquals("Test", projectDetails[ProjectFieldIndexes.TYPE]);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  public void testGetAllProjects() {
    try {
      // Use the service to retrieve all projects
      Map<String, Object> response = projectServices.getAllProjects();

      // Assert that the response is not null and contains the "projects" key
      assertNotNull(response);
      assertNotNull(response.get("projects"));
      assertTrue(response.get("projects") instanceof List);

      // You can further assert specific details about the projects if needed
      List<?> projects = (List<?>) response.get("projects");
      assertTrue(!projects.isEmpty(), "Projects list should not be empty");

      // Verify details of the test project
      Object[] projectDetails =
          (Object[])
              projects.stream()
                  .filter(p -> ((Object[]) p)[ProjectFieldIndexes.PROJECT_ID].equals(testProjectId))
                  .findFirst()
                  .orElse(null);

      assertNotNull(projectDetails);
      assertEquals("Initial Test Project", projectDetails[ProjectFieldIndexes.PROJECT_NAME]);
      assertEquals("Initial description", projectDetails[ProjectFieldIndexes.DESCRIPTION]);
      assertEquals("Inactive", projectDetails[ProjectFieldIndexes.STATUS]);
      assertEquals("Initial", projectDetails[ProjectFieldIndexes.TYPE]);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }
}
