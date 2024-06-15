package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.CommentDAO;
import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : Praveen Singh
 * 
 * The CommentServiceImplementation class provides the concrete implementation of the CommentService interface.
 * It interacts with the CommentDAO to perform CRUD operations on comments and encapsulates the business logic
 * for managing comments within the ProManager application.
 */

@Service  // Indicates that this class is a Spring service component.
public class CommentServiceImplementation implements CommentService {

    @Autowired  // Injects the CommentDAO dependency.
    private CommentDAO commentDAO;

    /**
     * Retrieves all comments for a given project ID.
     *
     * @param projectId the ID of the project for which comments are to be retrieved.
     * @return A Map containing a list of Comment objects associated with the given project ID.
     */
    @Override
    public Map<String, Object> getAllComments(int projectId) {
        // Initialize a response map to hold the result
        Map<String, Object> response = new HashMap<>();
        
        // Use the DAO to get the list of comments for the specified project ID
        List<Comment> comments = commentDAO.getAllComments(projectId);
        
        // Add the list of comments to the response map
        response.put("comments", comments);
        
        // Return the response map
        return response;
    }

    /**
     * Adds a new comment to the system.
     *
     * @param comment the Comment object to be added.
     * @return A Map containing the newly added Comment object.
     */
    @Override
    public Map<String, Object> addComment(Comment comment) {
        // Initialize a response map to hold the result
        Map<String, Object> response = new HashMap<>();
        
        // Use the DAO to add the comment to the database
        Comment addedComment = commentDAO.addComment(comment);
        
        // Add the added comment to the response map
        response.put("comment", addedComment);
        
        // Return the response map
        return response;
    }

    /**
     * Deletes a comment from the system based on its ID.
     *
     * @param id the ID of the comment to be deleted.
     * @return A Map containing the success response.
     */
    @Override
    public Map<String, Object> deleteComment(int id) {
        // Initialize a response map to hold the result
        Map<String, Object> response = new HashMap<>();
        
        // Use the DAO to delete the comment by its ID
        commentDAO.deleteComment(id);
        
        // Add a success message to the response map
        response.put("response", "Success");
        
        // Return the response map
        return response;
    }

    /**
     * Edits an existing comment in the system.
     *
     * @param comment the Comment object containing the updated comment data.
     * @return A Map containing the updated Comment object.
     */
    @Override
    public Map<String, Object> editComment(Comment comment) {
        // Initialize a response map to hold the result
        Map<String, Object> response = new HashMap<>();
        
        // Use the DAO to update the comment in the database
        Comment editedComment = commentDAO.editComment(comment);
        
        // Add the edited comment to the response map
        response.put("comment", editedComment);
        
        // Return the response map
        return response;
    }
}