package com.cs673olsum24.promanager.service;

import com.cs673olsum24.promanager.entity.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import org.json.simple.JSONObject;

/**
 * Author : Praveen Singh
 * 
 * The CommentService interface defines the service layer contract for managing comments
 * in the ProManager application. It includes methods for retrieving, adding, deleting,
 * and editing comments. The service layer abstracts the business logic and interactions
 * with the data access layer (DAO) for Comment entities.
 */
public interface CommentService {

    /**
     * Retrieves all comments for a given project ID.
     *
     * @param projectId the ID of the project for which comments are to be retrieved.
     * @return A Map containing the result of the operation. The map typically includes
     *         a success flag, a message, and the list of comments if the retrieval is successful.
     */
	public abstract Map<String, Object> getAllComments(String projectId);
    
   

    /**
     * Adds a new comment to the system.
     *
     * @param comment the Comment object to be added.
     * @return A Map containing the result of the operation. The map typically includes
     *         a success flag, a message, and the newly added comment if the addition is successful.
     */
//    Map<String, Object> addComment(Comment comment);

	public abstract Map<String, Object>  addComment(HttpServletRequest request, Map<String, Object> payload);

    /**
     * Deletes a comment from the system based on its ID.
     *
     * @param id the ID of the comment to be deleted.
     * @return A Map containing the result of the operation. The map typically includes
     *         a success flag and a message indicating whether the deletion was successful.
     */
    Map<String, Object> deleteComment(int id);

    /**
     * Edits an existing comment in the system.
     *
     * @param body the Comment object containing the updated data.
     * @return A Map containing the result of the operation. The map typically includes
     *         a success flag, a message, and the updated comment if the edit is successful.
     */
    Map<String, Object> editComment(JSONObject body);



}