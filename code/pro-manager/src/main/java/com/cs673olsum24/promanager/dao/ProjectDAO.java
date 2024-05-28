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
import com.tcs.capacitymgmt.entity.Vios;

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
		public List<Object[]> findAllProjects() {			
			
			try {
	            String sql = "SELECT p.projectid, p.projectname FROM project_ci p";
	            
	            Query query = entityManager.createNativeQuery(sql);
	            	           		          
	            List<Object[]> results = query.getResultList();	          
	            return query.getResultList();	            	            	            	        	
	        } 
			catch (Exception e) {
				System.out.println("Bye");
		    	return Collections.emptyList();
		    }
		}
		
		

	    public void addProjects(Projects p) {
	    	
	    	System.out.println("In Dao");
	 		final String sql = "insert into project_ci(projectid,projectname,userid,taskid,description,created_on,updated_on,status,type) "
	 						 + "values(:projectid,:projectname,:userid,:taskid,:description,:created_on,:updated_on,:status,:type)";	
	 		
	 		Map<String,Object> map=new HashMap<>();  
	 		map.put("projectid", p.getProjectid());
	 		map.put("projectname", p.getProjectname());
	 		map.put("userid", p.getUserid());
	 		map.put("taskid", p.getTaskid());
	 		map.put("description", p.getDescription());
	 		map.put("created_on", p.getCreated_on());
	 		map.put("updated_on", p.getUpdated_on());
	 		map.put("status", p.getStatus());
	 		map.put("type", p.getType());		
	 		
	 		template.execute(sql,map,new PreparedStatementCallback<Object>() {  
	 		    @Override  
	 		    public Object doInPreparedStatement(PreparedStatement ps)  
	 		            throws SQLException {  
	 		        return ps.executeUpdate(); 
	 		    }  
	 		});
	    }
		
}
