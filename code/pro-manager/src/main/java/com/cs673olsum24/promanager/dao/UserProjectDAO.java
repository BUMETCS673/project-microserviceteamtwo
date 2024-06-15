package com.cs673olsum24.promanager.dao;


import jakarta.persistence.EntityManager;
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

import com.cs673olsum24.promanager.entity.ProjectUser;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class UserProjectDAO {
	@Autowired
	private EntityManager entityManager;	 
	public UserProjectDAO(NamedParameterJdbcTemplate template) {  
		this.template = template;  
	}


	NamedParameterJdbcTemplate template;

	/**
	 * Author - Pranjal Ekhande
	 * 
	 * Retrieves a list of projects associated with a specific user, either as an owner or a member.
	 *
	 * @param user_id The ID of the user for whom the projects are to be retrieved.
	 * @return A list of Object arrays, each representing a project with the following details:
	 *         - Project ID 
	 *         - Project name 
	 *         - Project description
	 *         - Project owner's name
	 *         - Project owner's email
	 *         - Project creation date
	 *         - Project last updated date
	 *         - Project status
	 *         - Project type
	 *         - Project active status
	 *         If an exception occurs during the query execution, an empty list is returned.
	 */
	
	public  List<Map<String, Object>>  findAllUserProjects(String user_id) {
	try {
			
			
			String sql = "SELECT p.project_id, p.projectname, p.description AS project_description, u.Name AS project_owner, u.EMAIL AS project_owner_email, p.created_on AS project_created_on,\n"
					+ "p.updated_on AS project_updated_on, p.status AS project_status, p.type AS project_type, p.active AS project_active \n"
					+ "FROM project_ci p LEFT JOIN APP_USER u ON p.owner_id = u.USER_ID LEFT JOIN PROJECT_USER pu ON p.project_id = pu.project_id WHERE p.owner_id = "+user_id+" OR pu.user_id = "+user_id;
			
			Query query = entityManager.createNativeQuery(sql);
			

		    

		    List<Object[]> results = query.getResultList();
			

		    
		    List<Map<String, Object>> formattedResults = new ArrayList<>();

		    for (Object[] row : results) {
		    	
		        Map<String, Object> rowMap = new HashMap<>();
		        
		    
		        rowMap.put("project_id", row[0]);
		        

		        rowMap.put("projectname", row[1]);

		        rowMap.put("description", row[2]);

		        rowMap.put("owner_name", row[3]);

		        rowMap.put("email", row[4]);
		        

		        rowMap.put("created_on", row[5]);

		        rowMap.put("updated_on", row[6]);
		        

		        rowMap.put("status", row[7]);

		        rowMap.put("type", row[8]);
		        rowMap.put("active", row[9]);




		        formattedResults.add(rowMap);

		      
		    }
		    return formattedResults;
			
//			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	public  List<Map<String, Object>>  findAllProjectMembers(String project_id) {
		try {
				
				
				String sql = "SELECT \n"
						+ "    p.project_id,\n"
						+ "    p.projectname,\n"
						+ "    p.description AS project_description,\n"
						+ "    p.owner_id,\n"
						+ "    u1.NAME AS project_owner,\n"
						+ "    pu.USER_ID,\n"
						+ "    u2.NAME AS user_name,\n"
						+ "    pu.ROLE AS user_role\n"
						+ "FROM \n"
						+ "    project_ci p\n"
						+ "LEFT JOIN \n"
						+ "    APP_USER u1 ON p.owner_id = u1.USER_ID\n"
						+ "LEFT JOIN \n"
						+ "    PROJECT_USER pu ON p.project_id = pu.project_id\n"
						+ "LEFT JOIN \n"
						+ "    APP_USER u2 ON pu.USER_ID = u2.USER_ID\n"
						+ "WHERE \n"
						+ "    p.project_id = '"+project_id+"'";
			
				Query query = entityManager.createNativeQuery(sql);
				

			    

			    List<Object[]> results = query.getResultList();
				

			    
			    List<Map<String, Object>> formattedResults = new ArrayList<>();

			    for (Object[] row : results) {
			    	
			        Map<String, Object> rowMap = new HashMap<>();
			        
			    
			        rowMap.put("project_id", row[0]);
			        

			        rowMap.put("projectname", row[1]);
			        rowMap.put("description", row[2]);


			        rowMap.put("project_owner_id", row[3]);

			        rowMap.put("project_owner_name", row[4]);
			        


			        rowMap.put("project_user_id", row[5]);

			        rowMap.put("project_user_name", row[6]);

			        rowMap.put("ROLE", row[7]);

			       



			        formattedResults.add(rowMap);

			      
			    }
			    return formattedResults;
			} catch (Exception e) {
				return Collections.emptyList();
			}
		}
	

    

	
	public void addProjectMember(ProjectUser p) {

		final String sql = "INSERT INTO PROJECT_USER (PROJECT_USER_ID, PROJECT_ID, USER_ID, ROLE, created_at, updated_at) VALUES  "
							+ "(:PROJECT_USER_ID, :PROJECT_ID, :USER_ID, :ROLE, :created_at, :updated_at)";	
		
		Map<String,Object> map=new HashMap<>();  
	
	    
		map.put("PROJECT_USER_ID", p.getPROJECT_USER_ID());
		map.put("PROJECT_ID", p.getPROJECT_ID());
		map.put("USER_ID", p.getUSER_ID());
		
		map.put("ROLE", p.getROLE());
		
		map.put("created_at", p.getCreated_at());
		map.put("updated_at", p.getUpdated_at());
		
		System.out.println(map);

		template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate(); 
			}  
		});
	}

	
	
	
	
}
