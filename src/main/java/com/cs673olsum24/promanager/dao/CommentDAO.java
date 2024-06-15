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
 * 
 * The CommentDAO class handles database operations for the Comment entity.
 * This includes retrieving, adding, deleting, and updating comments in the database.
 * It uses both JPA's EntityManager and Spring's NamedParameterJdbcTemplate for database access.
 */

@Repository  // Indicates that this class is a repository that will be used for data access.
@Transactional  // Specifies that the methods in this class should be executed within a transactional context.
public class CommentDAO {

    // Injects the JPA EntityManager to manage and query Comment entities.
    @PersistenceContext
    private EntityManager entityManager;

    // Template for executing SQL statements with named parameters.
    private NamedParameterJdbcTemplate template;

    /**
     * Constructor for injecting NamedParameterJdbcTemplate.
     *
     * @param namedParameterJdbcTemplate the NamedParameterJdbcTemplate to be used for SQL operations.
     */
    public CommentDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    /**
     * Retrieves all comments for a given project ID from the database using JPA.
     *
     * @param project_id the ID of the project for which comments are to be retrieved.
     * @return A list of Comment objects representing the comments for the given project ID.
     *         If no comments are found, an empty list is returned.
     */
    @SuppressWarnings("unchecked")  // Suppresses unchecked cast warning for the query result.
    public List<Comment> getAllComments(int project_id) {
        try {
            // JPQL query to select comments by project ID.
            String getCommentsql = "SELECT c FROM Comment c WHERE c.projectId = :project_id";
            Query query = entityManager.createQuery(getCommentsql);
            query.setParameter("project_id", project_id);  // Sets the project_id parameter in the query.
            return query.getResultList();  // Executes the query and returns the list of comments.
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
    public Comment addComment(Comment comment) {
        // SQL statement to insert a new comment into the database.
        final String addCommentsql = "INSERT INTO comments (project_id, comments, user_id, created_on) VALUES (:project_id, :comments, :user_id, :created_on)";
        
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
        return comment;  // Returns the added comment.
    }

    /**
     * Deletes a comment from the database based on its ID using NamedParameterJdbcTemplate.
     *
     * @param id the ID of the comment to be deleted.
     */
    public void deleteComment(int id) {
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
    }

    /**
     * Updates an existing comment in the database using NamedParameterJdbcTemplate.
     *
     * @param comment the Comment object containing the updated comment data.
     * @return The updated Comment object.
     */
    public Comment editComment(Comment comment) {
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
        return comment;  // Returns the updated comment.
    }
}
