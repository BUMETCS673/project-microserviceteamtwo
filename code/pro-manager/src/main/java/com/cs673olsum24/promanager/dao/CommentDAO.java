package com.cs673olsum24.promanager.dao;

import com.cs673olsum24.promanager.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Praveen Singh
 */

@Repository
@Transactional
public class CommentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private NamedParameterJdbcTemplate template;

    public CommentDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    /**
     * Retrieves all comments for a given project ID from the database.
     *
     * @param projectId the ID of the project for which comments are to be retrieved.
     * @return A list of Comment objects representing the comments for the given project ID.
     *         If no comments are found, an empty list is returned.
     */
    @SuppressWarnings("unchecked")
	public List<Comment> getAllComments(int projectId) {
        try {
            String getCommentsql = "SELECT c FROM Comment c WHERE c.projectId = :projectId";
            Query query = entityManager.createQuery(getCommentsql);
            query.setParameter("projectId", projectId);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * Adds a new comment to the database.
     *
     * @param comment the Comment object to be added to the database.
     */
    
    public void addComment(Comment comment) {
        final String addCommentsql = "INSERT INTO comments (project_id, comments, user_id, created_on) VALUES (:projectId, :comments, :userId, :createdOn)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("project_id", comment.getProjectId());
        parameters.put("comments", comment.getComments());
        parameters.put("userId", comment.getUserId());
        parameters.put("createdOn", comment.getCreatedOn());

        template.execute(addCommentsql, parameters, new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate();  
			}  
		});
    }

    /**
     * Deletes a comment from the database based on its ID.
     *
     * @param id the ID of the comment to be deleted.
     */
    
    public void deleteComment(int id) {
        final String deleteCommentsql = "DELETE FROM comments WHERE id = :comment_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("comment_id", id);

        template.execute(deleteCommentsql, parameters, new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate();  
			}  
		});
    }

    /**
     * Updates an existing comment in the database.
     *
     * @param comment the Comment object containing the updated comment data.
     */
    
    public void editComment(Comment comment) {
        final String updateCommentsql = "UPDATE comments SET comments = :comments WHERE id = :comment_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("comments", comment.getComments());
        parameters.put("comment_id", comment.getId());

        template.execute(updateCommentsql, parameters, new PreparedStatementCallback<Object>() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException {  
				return ps.executeUpdate();  
			}  
		});
    }
}