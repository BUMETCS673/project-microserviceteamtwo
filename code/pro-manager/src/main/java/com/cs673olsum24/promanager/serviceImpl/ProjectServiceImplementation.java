package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.*;
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


	@Autowired
	private ProjectDAO projectDAO;

	public Map<String, Object> getAllProjects() throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		List<Object []> data = this.projectDAO.findAllProjects();
		map.put("projects", data);
		return map;
	}



	@SuppressWarnings("unchecked")
	public Map<String, Object> addProject(HttpServletRequest request, Map<String, Object> payload) {

		JSONObject data = JsonUtils.convertPayload(payload);
		JSONArray projects = (JSONArray) data.get("projects");

		Iterator<JSONObject> result = projects.iterator();
		while (result.hasNext()) {
			JSONObject each = (JSONObject) result.next();
			Long created_on = (Long) each.getOrDefault("created_on", 0);
			Long updated_on = (Long) each.getOrDefault("updated_on", 0);
			Long taskid = (Long) each.getOrDefault("taskid", 0);

			Projects p = new Projects();

			p.setProjectid((String) each.getOrDefault("projectid", "NA"));
			p.setProjectname((String) each.getOrDefault("projectname", "NA"));
			p.setUserid((String) each.getOrDefault("userid", "NA"));
			p.setDescription(each.getOrDefault("description", "NA").toString());
			p.setStatus((String) each.getOrDefault("status", "NA"));
			p.setType((String) each.getOrDefault("type", "NA"));

			p.setCreated_on((long) created_on.doubleValue());
			p.setUpdated_on((long) updated_on.doubleValue());
			p.setTaskid((long) taskid.doubleValue());

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

	public Map<String, Object> deleteProject(String id)
	{
		Map<String, Object> map2 = new HashMap<>();

		this.projectDAO.deleteProject(id);

		map2.put("Response","Success");
		return map2;

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

  @SuppressWarnings("unchecked")
  public Map<String, Object> editProject(JSONObject each) {
    Map<String, Object> map = new HashMap<>();
    Projects p = new Projects();
    p.setProjectid(
        ProjectUtils.safelyGetString(each, "projectid", ProjectUtils.DEFAULT_PROJECT_ID));
    p.setProjectname(
        ProjectUtils.safelyGetString(each, "projectname", ProjectUtils.DEFAULT_PROJECT_NAME));
    p.setUserid(ProjectUtils.safelyGetString(each, "userid", ProjectUtils.DEFAULT_USER_ID));
    p.setUpdated_on(
        ProjectUtils.safelyConvertToLong(
            each.getOrDefault("updated_on", ProjectUtils.DEFAULT_DATE)));
    p.setTaskid(
        ProjectUtils.safelyConvertToLong(each.getOrDefault("taskid", ProjectUtils.DEFAULT_DATE)));
    p.setDescription(
        ProjectUtils.safelyGetString(each, "description", ProjectUtils.DEFAULT_DESCRIPTION));
    p.setStatus(ProjectUtils.safelyGetString(each, "status", ProjectUtils.DEFAULT_STATUS));
    p.setType(ProjectUtils.safelyGetString(each, "type", ProjectUtils.DEFAULT_TYPE));

    try {
      this.projectDAO.editProject(p);
      map.put("Response", "Success");
    } catch (Exception e) {
      map.put("Response", "Failed");
    }

    return map;
  }
}
