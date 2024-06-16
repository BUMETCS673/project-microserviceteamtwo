package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.ProjectDAO;
import com.cs673olsum24.promanager.entity.Projects;
import com.cs673olsum24.promanager.service.ProjectServices;
import com.cs673olsum24.promanager.utils.JsonUtils;
import com.cs673olsum24.promanager.utils.ProjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
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
		Map<String, Object> map = new HashMap<>();
		List<Projects> data = this.projectDAO.findAllProjects();
		map.put("projects", data);
		return map;
	}
	
	public Map<String, Object> getIdWiseProject(String id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> data = this.projectDAO.findIdWiseProjects(id);

		
		map.put("project", data);
		return map;
	}
	
	@Override
	   @SuppressWarnings("unchecked")
	   public Map<String, Object> addProjects(HttpServletRequest request, Map<String, Object> payload) {
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

	
	@SuppressWarnings("unchecked")
	public Map<String, Object> addProject(HttpServletRequest request, Map<String, Object> payload) {

		JSONObject data = JsonUtils.convertPayload(payload);
		JSONArray projects = (JSONArray) data.get("projects");
		

		Iterator<JSONObject> result = projects.iterator();
		while (result.hasNext()) {
			JSONObject each = (JSONObject) result.next();
			
			

			Long createdOn = (Long) each.getOrDefault("created_on", 0);
			
			
			System.out.println("here update");
			
			Long updatedOn = (Long) each.getOrDefault("updated_on", 0);

			
			System.out.println("owner update");
			
			System.out.println(each.getOrDefault("project_id", "NA"));
			
			
			Object ownerIdObj = each.getOrDefault("owner_id", 1);
			int owner_id;

			if (ownerIdObj instanceof Long) {
			    owner_id = ((Long) ownerIdObj).intValue();
			} else if (ownerIdObj instanceof Integer) {
			    owner_id = (Integer) ownerIdObj;
			} else {
			    owner_id = 1; // Default value
			}
			
			System.out.println("owner update complete");
			

			Projects p = new Projects();

			p.setProjectid((String) each.getOrDefault("project_id", "NA"));
			p.setProjectname((String) each.getOrDefault("projectname", "NA"));
			
			p.setDescription(each.getOrDefault("description", "NA").toString());
			p.setStatus((String) each.getOrDefault("status", "OPEN"));
			p.setType((String) each.getOrDefault("type", "NA"));
			p.setActive((boolean)each.getOrDefault("active","TRUE"));

			p.setCreated_on( createdOn);
			p.setUpdated_on(updatedOn);
			p.setOwner_id(owner_id);
			
			

			try {
				this.projectDAO.addProjects(p);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("Response", "OK");

		return map;
	}



	//	@SuppressWarnings("unchecked")
	//	public Map<String, Object> safeDeleteProject(JSONObject each)
	//	{
	//		Map<String, Object> map = new HashMap<>();		
	//		Projects p = new Projects();	
	//		
	//		p.setProjectid((String) each.getOrDefault("projectid", "NA"));
	//		
	//
	//		try {
	//			this.projectDAO.safeDeleteProject(p);
	//			map.put("Response", "Success");
	//		}
	//		catch(Exception e)		
	//		{
	//			map.put("Response", "Failed");
	//		}
	//
	//    	return map;
	//	}



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
