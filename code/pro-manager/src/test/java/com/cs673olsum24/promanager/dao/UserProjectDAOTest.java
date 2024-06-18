package com.cs673olsum24.promanager.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.cs673olsum24.promanager.entity.ProjectUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserProjectDAOTest {

  @Mock private EntityManager entityManager;

  @Mock private NamedParameterJdbcTemplate template;

  @Mock private Query query;

  @InjectMocks private UserProjectDAO userProjectDAO;

  @BeforeEach
  public void setup() {
    userProjectDAO = new UserProjectDAO(template);
  }

  @Test
  public void testAddProjectMember() {
    ProjectUser projectUser = new ProjectUser();
    projectUser.setPROJECT_USER_ID(1);
    projectUser.setPROJECT_ID("proj1");
    projectUser.setUSER_ID(2);
    projectUser.setROLE("COLLABORATOR");
    projectUser.setCreated_at(1622547800L);
    projectUser.setUpdated_at(1622547800L);

    Map<String, Object> map = new HashMap<>();
    map.put("PROJECT_USER_ID", projectUser.getPROJECT_USER_ID());
    map.put("PROJECT_ID", projectUser.getPROJECT_ID());
    map.put("USER_ID", projectUser.getUSER_ID());
    map.put("ROLE", projectUser.getROLE());
    map.put("created_at", projectUser.getCreated_at());
    map.put("updated_at", projectUser.getUpdated_at());

    userProjectDAO.addProjectMember(projectUser);

    verify(template)
        .execute(
            eq(
                "INSERT INTO PROJECT_USER (PROJECT_USER_ID, PROJECT_ID, USER_ID, ROLE, created_at,"
                    + " updated_at) VALUES  (:PROJECT_USER_ID, :PROJECT_ID, :USER_ID, :ROLE,"
                    + " :created_at, :updated_at)"),
            eq(map),
            any());
  }

  @Test
  public void testFindAllUserProjectsException() {
    when(entityManager.createNativeQuery(anyString()))
        .thenThrow(new RuntimeException("Test Exception"));

    List<Map<String, Object>> result = userProjectDAO.findAllUserProjects("user1");

    assertNotNull(result);
    assertEquals(Collections.emptyList(), result);
  }

  @Test
  public void testFindAllProjectMembersException() {
    when(entityManager.createNativeQuery(anyString()))
        .thenThrow(new RuntimeException("Test Exception"));

    List<Map<String, Object>> result = userProjectDAO.findAllProjectMembers("proj1");

    assertNotNull(result);
    assertEquals(Collections.emptyList(), result);
  }
}
