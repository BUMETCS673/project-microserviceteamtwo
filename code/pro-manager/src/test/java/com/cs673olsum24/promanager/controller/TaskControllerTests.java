package com.cs673olsum24.promanager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cs673olsum24.promanager.ProManagerApplication;
import com.cs673olsum24.promanager.service.ProjectServices;
import com.cs673olsum24.promanager.service.TaskServices;
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
public class TaskControllerTests {

  @Autowired private TaskServices taskServices;

  @Autowired private ProjectServices projectServices;

  private String testTaskId = "task_002";
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
    projectServices.addProject(null, payload_projects);

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
    taskServices.addTasks(null, payload);
  }

  @AfterEach
  public void cleanup() {
    try {
      Map<String, Object> response = taskServices.getAllTasks(testProjectId);
      if (response != null && response.containsKey("tasks")) {
        List<?> tasks = (List<?>) response.get("tasks");
        for (Object taskObj : tasks) {
          Map<?, ?> task = (Map<?, ?>) taskObj;
          String taskId = (String) task.get("task_id");
          taskServices.deleteTask(taskId);
        }
      }
    } catch (Exception e) {
      // Ignore if already deleted or if there was an error fetching/deleting tasks
    }

    try {
      projectServices.deleteProject(testProjectId);
    } catch (Exception e) {
      // Ignore if already deleted
    }
  }

  @Test
  @Order(1)
  public void testRetrieveProject() {
    String projectId = "proj_002";

    try {
      Map<String, Object> response = taskServices.getAllTasks(projectId);
      assertNotNull(response);
      assertNotNull(response.get("tasks"));
      List<?> tasks = (List<?>) response.get("tasks");
      assertFalse(tasks.isEmpty());

      Map<?, ?> taskDetails = (Map<?, ?>) tasks.get(0);
      assertEquals("Initial Test Task", taskDetails.get("task_name"));
      assertEquals("Initial description", taskDetails.get("description"));
      assertEquals("To Do", taskDetails.get("status"));
      assertEquals("Low", taskDetails.get("priority"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(2)
  public void testGetAllProjectTasks() {
    try {
      Map<String, Object> response = taskServices.getAllTasks(testProjectId);
      assertNotNull(response);
      assertNotNull(response.get("tasks"));
      assertTrue(response.get("tasks") instanceof List);

      List<?> tasks = (List<?>) response.get("tasks");
      assertTrue(!tasks.isEmpty(), "Tasks list should not be empty");

      Map<?, ?> taskDetails =
          tasks.stream()
              .filter(p -> ((Map<?, ?>) p).get("task_id").equals(testTaskId))
              .map(p -> (Map<?, ?>) p)
              .findFirst()
              .orElse(null);

      assertNotNull(taskDetails);
      assertEquals("Initial Test Task", taskDetails.get("task_name"));
      assertEquals("Initial description", taskDetails.get("description"));
      assertEquals("To Do", taskDetails.get("status"));
      assertEquals("Low", taskDetails.get("priority"));
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
      Map<String, Object> response = taskServices.getAllTasks(projectId);
      assertNotNull(response, "Response should not be null");

      boolean noTasksFound =
          response.isEmpty()
              || (response.containsKey("tasks") && ((List<?>) response.get("tasks")).isEmpty());
      assertTrue(noTasksFound, "The response should be empty or contain an empty 'tasks' list");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(4)
  public void testDeleteProject() {
    String projectId = testProjectId;
    String taskId = testTaskId;

    Map<String, Object> response = taskServices.deleteTask(taskId);
    assertNotNull(response);
    assertEquals("Success", response.get("Response"));

    try {
      Map<String, Object> deletedProjectResponse = taskServices.getAllTasks(projectId);
      assertTrue(
          deletedProjectResponse.isEmpty()
              || (deletedProjectResponse.containsKey("tasks")
                  && ((List<?>) deletedProjectResponse.get("tasks")).isEmpty()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(5)
  public void testAddTasks() {
    String newProjectId = "proj_002";
    String newProjectTaskId = "task_003";
    Map<String, Object> payload = new HashMap<>();

    payload.put(
        "tasks",
        new Object[] {
          new HashMap<String, Object>() {
            {
              put("task_id", newProjectTaskId);
              put("project_id", newProjectId);
              put("task_name", "Added Test Task");
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

    Map<String, Object> response = taskServices.addTasks(null, payload);
    assertNotNull(response);
    assertEquals("OK", response.get("Response"));

    try {
      Map<String, Object> addedProjectResponse = projectServices.getIdWiseProject(newProjectId);
      Map<String, Object> addedTaskResponse = taskServices.getAllTasks(newProjectId);
      assertNotNull(addedTaskResponse);
      assertNotNull(addedTaskResponse.get("tasks"));
      List<?> tasks = (List<?>) addedTaskResponse.get("tasks");
      assertFalse(tasks.isEmpty());

      Map<?, ?> taskDetails = (Map<?, ?>) tasks.get(1);
      assertEquals("Added Test Task", taskDetails.get("task_name"));
      assertEquals("Initial description", taskDetails.get("description"));
      assertEquals("To Do", taskDetails.get("status"));
      assertEquals("Low", taskDetails.get("priority"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }

  @Test
  @Order(6)
  public void testEditTask() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("task_id", testTaskId);
    payload.put("project_id", testProjectId);
    payload.put("task_name", "Updated Test Task");
    payload.put("description", "Updated description");
    payload.put("status", "In Progress");
    payload.put("priority", "High");
    payload.put("assigned_user_id", 2);
    payload.put("due_date", 1717941369L);
    payload.put("updated_on", 1717941369L);

    JSONObject jsonPayload = new JSONObject(payload);
    Map<String, Object> response = taskServices.editTask(jsonPayload);

    assertNotNull(response);
    assertEquals("Success", response.get("Response"));

    try {
      Map<String, Object> updatedTaskResponse = taskServices.getAllTasks(testProjectId);
      assertNotNull(updatedTaskResponse);
      assertNotNull(updatedTaskResponse.get("tasks"));
      List<?> tasks = (List<?>) updatedTaskResponse.get("tasks");
      assertFalse(tasks.isEmpty());

      Map<?, ?> taskDetails =
          tasks.stream()
              .filter(p -> ((Map<?, ?>) p).get("task_id").equals(testTaskId))
              .map(p -> (Map<?, ?>) p)
              .findFirst()
              .orElse(null);

      assertNotNull(taskDetails);
      assertEquals("Updated Test Task", taskDetails.get("task_name"));
      assertEquals("Updated description", taskDetails.get("description"));
      assertEquals("In Progress", taskDetails.get("status"));
      assertEquals("High", taskDetails.get("priority"));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      assertNotNull(null, "JsonProcessingException was thrown");
    }
  }
}
