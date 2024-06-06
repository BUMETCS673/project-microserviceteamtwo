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
