package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.ProjectDAO;
import com.cs673olsum24.promanager.entity.Projects;
import com.cs673olsum24.promanager.service.ProjectServices;
import com.cs673olsum24.promanager.utils.JsonUtils;
import com.cs673olsum24.promanager.utils.ProjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImplementation implements ProjectServices {

  @Autowired private ProjectDAO projectDAO;

  @Override
  public Map<String, Object> getAllProjects() throws JsonProcessingException {
    Map<String, Object> response = new HashMap<>();
    List<Object[]> data = projectDAO.findAllProjects();
    response.put("projects", data);
    return response;
  }

  @Override
  public Map<String, Object> getIdWiseProject(String id) throws JsonProcessingException {
    Map<String, Object> response = new HashMap<>();
    List<Object[]> data = projectDAO.findIdWiseProjects(id);
    response.put("project", data);
    return response;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Map<String, Object> addProject(HttpServletRequest request, Map<String, Object> payload) {
    JSONObject data = JsonUtils.convertPayload(payload);
    JSONArray projects = (JSONArray) data.get("projects");

    for (Object obj : projects) {
      JSONObject each = (JSONObject) obj;
      Projects project = parseProject(each);
      try {
        projectDAO.addProjects(project);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    Map<String, Object> response = new HashMap<>();
    response.put("Response", "OK");
    return response;
  }

  @Override
  public Map<String, Object> deleteProject(String id) {
    Map<String, Object> response = new HashMap<>();
    try {
      projectDAO.deleteProject(id);
      response.put("Response", "Success");
    } catch (Exception e) {
      e.printStackTrace();
      response.put("Response", "Failed");
    }
    return response;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Map<String, Object> editProject(JSONObject each) {
    Map<String, Object> response = new HashMap<>();
    Projects project = parseProject(each);

    try {
      projectDAO.editProject(project);
      response.put("Response", "Success");
    } catch (Exception e) {
      e.printStackTrace();
      response.put("Response", "Failed");
    }
    return response;
  }

  private Projects parseProject(JSONObject each) {
    Projects project = new Projects();

    project.setProjectid(
        ProjectUtils.safelyGetString(each, "project_id", ProjectUtils.DEFAULT_PROJECT_ID));
    project.setProjectname(
        ProjectUtils.safelyGetString(each, "projectname", ProjectUtils.DEFAULT_PROJECT_NAME));
    project.setDescription(
        ProjectUtils.safelyGetString(each, "description", ProjectUtils.DEFAULT_DESCRIPTION));
    project.setStatus(ProjectUtils.safelyGetString(each, "status", ProjectUtils.DEFAULT_STATUS));
    project.setType(ProjectUtils.safelyGetString(each, "type", ProjectUtils.DEFAULT_TYPE));
    project.setActive((Boolean) each.getOrDefault("active", true));

    Long createdOn =
        ProjectUtils.safelyConvertToLong(
            each.getOrDefault("created_on", ProjectUtils.DEFAULT_DATE));
    Long updatedOn =
        ProjectUtils.safelyConvertToLong(
            each.getOrDefault("updated_on", ProjectUtils.DEFAULT_DATE));

    project.setCreated_on(createdOn);
    project.setUpdated_on(updatedOn);

    Object ownerIdObj = each.getOrDefault("owner_id", 1);
    int owner_id;
    if (ownerIdObj instanceof Long) {
      owner_id = ((Long) ownerIdObj).intValue();
    } else if (ownerIdObj instanceof Integer) {
      owner_id = (Integer) ownerIdObj;
    } else {
      owner_id = 1; // Default value
    }

    project.setOwner_id(owner_id);

    return project;
  }
}
