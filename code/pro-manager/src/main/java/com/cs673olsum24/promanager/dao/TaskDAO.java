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
public class TaskDAO {
	@Autowired
	private EntityManager entityManager;	 
	public TaskDAO(NamedParameterJdbcTemplate template) {  
		this.template = template;  
	}


	NamedParameterJdbcTemplate template;
	
	public List<Map<String, Object>> findAllProjectTasks(String id) {
		
		try {	
			
			String sql = "Select  p.project_id, p.projectname,t.task_id,t.task_name,t.description,t.status,t.priority,t.due_date, u.NAME from " 
			+ Projects.class.getName() + " p join " + ProjectTasks.class.getName() + 
	             		" t on p.project_id = t.project_id left join " + 
	             			AppUser.class.getName() + " u on t.assigned_user_id = u.USER_ID where p.project_id ='"+id+"'";
			Query query = entityManager.createQuery(sql);
			
			
			
			List<Object[]> results = query.getResultList();
			
			
			
	        List<Map<String, Object>> formattedResults = new ArrayList<>();
	        
	        for (Object[] row : results) {
	        	
				
	            Map<String, Object> rowMap = new HashMap<>();
	            rowMap.put("project_id", row[0]);
	            rowMap.put("projectname", row[1]);
	            rowMap.put("task_id", row[2]);
	            rowMap.put("task_name", row[3]);
	            rowMap.put("description", row[4]);
	            rowMap.put("status", row[5]);
	            rowMap.put("priority", row[6]);
	            rowMap.put("due_date", row[7]);
	            rowMap.put("assigned_user_name", row[8]);
	            
	            formattedResults.add(rowMap);
	            
	        }

			return formattedResults;
			
		} catch (Exception e) {
			return Collections.emptyList();
		}

	}
	
	public List<ProjectTasks> findAllProjectTasks() {
	    try {
	        String sql = "Select e from " + ProjectTasks.class.getName() + " e ";
	       
	
	        Query query = entityManager.createQuery(sql, ProjectTasks.class);
	
	        return query.getResultList();
	    } catch (NoResultException e) {
	    	return Collections.emptyList();
	    }
	}

	public void editTask(ProjectTasks t) {

    final String sql1 = "UPDATE tasks SET task_name=:task_name, project_id=:project_id, description=:description, status=:status, priority=:priority, assigned_user_id=:assigned_user_id, due_date=:due_date, updated_on=:updated_on WHERE task_id=:task_id";

    Map<String,Object> map = new HashMap<>();  
    map.put("task_id", t.getTask_id());
    map.put("task_name", t.getTask_name());
    map.put("project_id", t.getProject_id());
    map.put("description", t.getDescription());
    map.put("status", t.getStatus());
    map.put("priority", t.getPriority());
    map.put("assigned_user_id", t.getAssigned_user_id());
    map.put("due_date", t.getDue_date());
    map.put("updated_on", t.getUpdated_on());

    template.execute(sql1, map, new PreparedStatementCallback<Object>() {  
        @Override  
        public Object doInPreparedStatement(PreparedStatement ps)  
                throws SQLException {  
            return ps.executeUpdate();  
        }  
    });
	}





	
	public void addTaskProjects(ProjectTasks t) {

		final String sql = "INSERT INTO tasks (task_id, project_id, task_name, description, status, priority, assigned_user_id, due_date, created_on, updated_on)  "
							+ "values(:task_id,:project_id,:task_name,:description,:status,:priority,:assigned_user_id,:due_date,:created_on,:updated_on)";	

		Map<String,Object> map=new HashMap<>();  
	
	    
		map.put("task_id", t.getTask_id());
		map.put("project_id", t.getProject_id());
		map.put("task_name", t.getTask_name());
		
		map.put("description", t.getDescription());
		map.put("status", t.getStatus());
		map.put("priority", t.getPriority());
		
		map.put("assigned_user_id", t.getAssigned_user_id());

		map.put("due_date", t.getDue_date());
		map.put("created_on", t.getCreated_on());
		map.put("updated_on", t.getUpdated_on());
		
		

		template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate(); 
			}  
		});
	}
	
	
	public String deleteTask(String id) {		    	

		final String sql1 ="delete from tasks WHERE task_id= :id";
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




}
