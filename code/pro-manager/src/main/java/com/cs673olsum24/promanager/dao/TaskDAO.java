package com.cs673olsum24.promanager.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	
	
	
	public List<Object[]> findAllProjectTasks(String id) {
		System.out.println(id);
		try {
			
			Map<String,Object> map=new HashMap<>();
			map.put("projectid", id);	 
				
			String sql = "SELECT \n"
					+ "    p.project_id,\n"
					+ "    p.projectname,\n"
					+ "    t.task_id,\n"
					+ "    t.task_name,\n"
					+ "    t.description,\n"
					+ "    t.status,\n"
					+ "    t.priority,\n"
					+ "    t.due_date,\n"
					+ "    u.NAME AS assigned_user\n"
					+ "FROM \n"
					+ "    project_ci p\n"
					+ "JOIN \n"
					+ "    tasks t ON p.project_id = t.project_id\n"
					+ "LEFT JOIN \n"
					+ "    APP_USER u ON t.assigned_user_id = u.USER_ID\n"
					+ "WHERE \n"
					+ "    p.project_id = '"+id+"'";		
			System.out.println(sql);
			
			
			
			
			Query query = entityManager.createNativeQuery(sql);
			System.out.println(query.getResultList());
			List<Object[]> results = query.getResultList();
			return query.getResultList();
		} catch (Exception e) {
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





	
//	SELECT p.project_id, p.projectname ,  u.name , p.active, p.description,  p.created_on, p.updated_on, p.status, p.type FROM project_ci p LEFT join APP_USER u ON p.owner_id = u.user_id where p.project_id = 'proj_001';
	
	


//	public String deleteProject(String id) {		    	
//
//		final String sql1 ="delete from project_ci WHERE projectid= :id";
//		Map<String,Object> map1=new HashMap<>(); 
//
//		map1.put("id",id);
//
//		template.execute(sql1,map1,new PreparedStatementCallback<Object>() {  
//			@Override  
//			public Object doInPreparedStatement(PreparedStatement ps)  
//					throws SQLException {  
//				return ps.executeUpdate();  
//			}  
//		});
//		return "success";
//	}
//


}
