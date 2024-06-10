package com.cs673olsum24.promanager.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<Object[]> findAllUserProjects(String user_id) {
		try {
			
			
			String sql = "SELECT p.project_id, p.projectname, p.description AS project_description, u.Name AS project_owner, u.EMAIL AS project_owner_email, p.created_on AS project_created_on,\n"
					+ "p.updated_on AS project_updated_on, p.status AS project_status, p.type AS project_type, p.active AS project_active \n"
					+ "FROM project_ci p LEFT JOIN APP_USER u ON p.owner_id = u.USER_ID LEFT JOIN PROJECT_USER pu ON p.project_id = pu.project_id WHERE p.owner_id = "+user_id+" OR pu.user_id = "+user_id;
			
			Query query = entityManager.createNativeQuery(sql);
			
			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
}
