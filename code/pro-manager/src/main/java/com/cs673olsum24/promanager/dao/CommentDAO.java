package com.cs673olsum24.promanager.dao;

import com.cs673olsum24.promanager.entity.AppUser;
import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.entity.Projects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Praveen Singh
 * 
 * The CommentDAO class handles database operations for the Comment entity.
 * This includes retrieving, adding, deleting, and updating comments in the database.
 * It uses both JPA's EntityManager and Spring's NamedParameterJdbcTemplate for database access.
 */

@Repository  // Indicates that this class is a repository that will be used for data access.
@Transactional  // Specifies that the methods in this class should be executed within a transactional context.
public class CommentDAO {

    
	@Autowired
    private EntityManager entityManager;


    /**
     * Constructor for injecting NamedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate the NamedParameterJdbcTemplate to be used for SQL operations.
     */
    public CommentDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }
    
    // Template for executing SQL statements with named parameters.
    private NamedParameterJdbcTemplate template;
    

    /**
     * Retrieves all comments for a given project ID from the database using JPA.
     *
     * @param project_id the ID of the project for which comments are to be retrieved.
     * @return A list of Comment objects representing the comments for the given project ID.
     *         If no comments are found, an empty list is returned.
     */
    public List<Map<String, Object>> getAllComments(String project_id) {
        try {
        	
        	String sql = "SELECT *  FROM comments"+" c where c.project_id = '"+project_id+"'";

           
            Query query = entityManager.createNativeQuery(sql);
           
	        List<Object[]> results = query.getResultList();		

	        List<Map<String, Object>> formattedResults = new ArrayList<>();
            
            for (Object[] row : results) {
	        	
		            Map<String, Object> rowMap = new HashMap<>();
		        
		            rowMap.put("comment_id", row[0]);

		            rowMap.put("project_id", row[1]);
		            rowMap.put("comments", row[2]);
	
		            rowMap.put("user_id", row[3]);
		            rowMap.put("created_on", row[4]);
	
		            formattedResults.add(rowMap);	           
	          
	        } 
            return formattedResults;  
        } catch (Exception e) {
            // Returns an empty list if an exception occurs during the query execution.
            return Collections.emptyList();
        }
    }
    

    
    
    
    /**
     * Adds a new comment to the database using NamedParameterJdbcTemplate.
     *
     * @param comment the Comment object to be added to the database.
     * @return The added Comment object.
     */
    public void addComment(Comment comment) {
        // SQL statement to insert a new comment into the database.
        final String addCommentsql = "INSERT INTO comments (project_id, comments, user_id, created_on) VALUES (:project_id, :comments, :user_id, NOW())";
        
        // Maps the named parameters in the SQL statement to the Comment object properties.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("project_id", comment.getProjectId());
        parameters.put("comments", comment.getComments());
        parameters.put("user_id", comment.getUserId());
        parameters.put("created_on", comment.getCreatedOn());

        // Executes the insert operation using the named parameters.
        template.execute(addCommentsql, parameters, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException {
                return ps.executeUpdate();  // Executes the update and returns the result.
            }
        });
  
    }

    /**
     * Deletes a comment from the database based on its ID using NamedParameterJdbcTemplate.
     *
     * @param id the ID of the comment to be deleted.
     */
    public String deleteComment(int id) {
        // SQL statement to delete a comment from the database by ID.
        final String deleteCommentsql = "DELETE FROM comments WHERE comment_id = :comment_id";
        
        // Maps the named parameter in the SQL statement to the comment ID.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("comment_id", id);

        // Executes the delete operation using the named parameter.
        template.execute(deleteCommentsql, parameters, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException {
                return ps.executeUpdate();  // Executes the update and returns the result.
            }
        });
        
        return "success";
    }

    /**
     * Updates an existing comment in the database using NamedParameterJdbcTemplate.
     *
     * @param comment the Comment object containing the updated comment data.
     * @return The updated Comment object.
     */
    public void editComment(Comment comment) {
        // SQL statement to update the comment text of an existing comment by ID.
        final String updateCommentsql = "UPDATE comments SET comments = :comments WHERE comment_id = :comment_id";
        
        // Maps the named parameters in the SQL statement to the updated comment properties.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("comments", comment.getComments());
        parameters.put("comment_id", comment.getId());

        // Executes the update operation using the named parameters.
        template.execute(updateCommentsql, parameters, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException {
                return ps.executeUpdate();  // Executes the update and returns the result.
            }
        });
    }
}
