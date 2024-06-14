package com.cs673olsum24.promanager.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cs673olsum24.promanager.entity.AppUser;
import com.cs673olsum24.promanager.entity.ProjectTasks;
import com.cs673olsum24.promanager.entity.Projects;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ProjectDAO {
	@Autowired
	private EntityManager entityManager;	 
	public ProjectDAO(NamedParameterJdbcTemplate template) {  
		this.template = template;  
	}


	NamedParameterJdbcTemplate template;

	/**
	 * Retrieves all project records from the database.
	 *
	 * This method executes a native SQL query to fetch all columns of the project records
	 * from the `project_ci` table. The retrieved columns include project ID, project name,
	 * user ID, task ID, description, created date, updated date, status, and type.
	 *
	 * @return A list of Object arrays, where each array represents a row from the `project_ci`
	 *         table. Each Object array contains the following columns:
	 *         - projectid (Integer)
	 *         - projectname (String)
	 *         - userid (Integer)
	 *         - taskid (Integer)
	 *         - description (String)
	 *         - created_on (Timestamp)
	 *         - updated_on (Timestamp)
	 *         - status (String)
	 *         - type (String)
	 *         
	 *         If an exception occurs during query execution, an empty list is returned.
	 *
	 * @throws IllegalArgumentException If the query string is invalid.
	 * @throws PersistenceException If there is a problem executing the query.
	 */
	
	public  List<Projects>findAllProjects() {
		try {
			String sql = "Select p from " + Projects.class.getName() + " p";
			
			
		    Query query = entityManager.createQuery(sql, ProjectTasks.class);
				
		    return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	
	
	public List<Map<String, Object>> findIdWiseProjects(String id) {
		try {
			String sql = "SELECT p.project_id, p.projectname ,  u.NAME , p.active, p.description,  p.created_on, "
					+ "p.updated_on, p.status, p.type FROM "+  Projects.class.getName() + " p LEFT join "+AppUser.class.getName() +" u ON p.owner_id = u.USER_ID where p.project_id = '"+id+"'";

//			List<Object[]> results = query.getResultList();
			

			Query query = entityManager.createQuery(sql);
			

	        

	        List<Object[]> results = query.getResultList();
			

	        
	        List<Map<String, Object>> formattedResults = new ArrayList<>();

	        for (Object[] row : results) {
	        	
	            Map<String, Object> rowMap = new HashMap<>();
	            
	        
	            rowMap.put("project_id", row[0]);
	            

	            rowMap.put("projectname", row[1]);

	            rowMap.put("owner_name", row[2]);
	            rowMap.put("active", row[3]);

	            rowMap.put("description", row[4]);
	            rowMap.put("created_on", row[5]);

	            rowMap.put("updated_on", row[6]);
	            rowMap.put("status", row[7]);

	            rowMap.put("type", row[8]);

	            formattedResults.add(rowMap);

	          
	        }
			
			
			
			return formattedResults;
			
			
			
			
		
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public void addProjects(Projects p) {

		final String sql = "insert into project_ci(project_id,projectname,owner_id,description,created_on,updated_on,status,type,active) "
				+ "values(:project_id,:projectname,:owner_id,:description,:created_on,:updated_on,:status,:type,:active)";	

		Map<String,Object> map=new HashMap<>();  
		map.put("project_id", p.getProjectid());
		map.put("projectname", p.getProjectname());
		map.put("owner_id", p.getOwner_id());
		map.put("description", p.getDescription());
		map.put("created_on", p.getCreated_on());
		map.put("updated_on", p.getUpdated_on());
		map.put("status", p.getStatus());
		map.put("type", p.getType());	
		map.put("active", p.getActive());

		template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate(); 
			}  
		});
	}


	public String deleteProject(String id) {		    	

		final String sql1 ="delete from project_ci WHERE project_id= :id";
		Map<String,Object> map1=new HashMap<>(); 

		map1.put("id",id);

		template.execute(sql1,map1,new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate();  
			}  
		});
		return "success";
	}



	public void safeDeleteProject(Projects p) {

		final String sql1 ="UPDATE project_ci SET status= inactive WHERE projectid= :projectid";

		Map<String,Object> map=new HashMap<>();

		map.put("projectid", p.getProjectid());	 	
		map.put("status", p.getStatus());

		template.execute(sql1,map,new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate();  
			}  
		});
	}



	public void editProject(Projects p) {

		final String sql1 ="UPDATE project_ci SET projectname=:projectname,owner_id=:owner_id,description=:description ,updated_on= :updated_on, status=:status,type=:type WHERE project_id= :project_id";

		Map<String,Object> map=new HashMap<>();  
		map.put("project_id", p.getProjectid());
		map.put("projectname", p.getProjectname());
		map.put("owner_id", p.getOwner_id());		
		map.put("description", p.getDescription());
		map.put("updated_on", p.getUpdated_on());
		map.put("status", p.getStatus());
		map.put("type", p.getType());

		template.execute(sql1,map,new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate();  
			}  
		});
	}

}
