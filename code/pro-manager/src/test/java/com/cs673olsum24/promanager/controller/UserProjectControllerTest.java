package com.cs673olsum24.promanager.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.cs673olsum24.promanager.ProManagerApplication;
import com.cs673olsum24.promanager.dao.UserProjectDAO;
import com.cs673olsum24.promanager.serviceImpl.UserProjectServiceImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProManagerApplication.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserProjectControllerTest {

  @Mock(lenient = true)
  private UserProjectDAO userProjectDAO;

  @InjectMocks private UserProjectServiceImplementation userProjectServiceImplementation;

  private String testUserId = "user123";
  private String testProjectId = "project123";

  @BeforeEach
  public void setup() {
    // Setup mock data and behavior
    List<Map<String, Object>> userProjects = new ArrayList<>();
    Map<String, Object> project = new HashMap<>();
    project.put("project_id", testProjectId);
    project.put("project_name", "Test Project");
    userProjects.add(project);

    List<Map<String, Object>> projectMembers = new ArrayList<>();
    Map<String, Object> member = new HashMap<>();
    member.put("user_id", testUserId);
    member.put("role", "COLLABORATOR");
    projectMembers.add(member);

    Mockito.lenient().when(userProjectDAO.findAllUserProjects(testUserId)).thenReturn(userProjects);
    Mockito.lenient()
        .when(userProjectDAO.findAllProjectMembers(testProjectId))
        .thenReturn(projectMembers);
  }

  @Test
  public void testGetAllUserProjects() throws JsonProcessingException {
    Map<String, Object> result = userProjectServiceImplementation.getAllUserProjects(testUserId);

    assertNotNull(result);
    assertTrue(result.containsKey("user_projects"));

    List<Map<String, Object>> projects = (List<Map<String, Object>>) result.get("user_projects");
    assertEquals(1, projects.size());
    assertEquals(testProjectId, projects.get(0).get("project_id"));
  }

  @Test
  public void testGetAllProjectMembers() throws JsonProcessingException {
    Map<String, Object> result =
        userProjectServiceImplementation.getAllProjectMembers(testProjectId);

    assertNotNull(result);
    assertTrue(result.containsKey("project_members"));

    List<Map<String, Object>> members = (List<Map<String, Object>>) result.get("project_members");
    assertEquals(1, members.size());
    assertEquals(testUserId, members.get(0).get("user_id"));
  }

  @Test
  public void testAddProjectMember() {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Map<String, Object> payload = new HashMap<>();
    List<Map<String, Object>> projectMembers = new ArrayList<>();
    Map<String, Object> member = new HashMap<>();
    member.put("PROJECT_USER_ID", 1);
    member.put("PROJECT_ID", testProjectId);
    member.put("USER_ID", 123);
    member.put("ROLE", "COLLABORATOR");
    member.put("created_at", 1622547800L);
    member.put("updated_at", 1622547800L);
    projectMembers.add(member);
    payload.put("project_member", projectMembers);

    Map<String, Object> result =
        userProjectServiceImplementation.addProjectMember(request, payload);

    assertNotNull(result);
    assertEquals("OK", result.get("Response"));
  }
}
