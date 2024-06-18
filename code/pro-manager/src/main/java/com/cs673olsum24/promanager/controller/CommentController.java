package com.cs673olsum24.promanager.controller;

import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author : Praveen Singh
 *
 * The CommentController class provides RESTful API endpoints for managing comments in the ProManager application.
 * It handles incoming HTTP requests and delegates the operations to the CommentService layer.
 */

@RestController  // Indicates that this class is a RESTful controller
@RequestMapping("/apiv1")  // Maps HTTP requests to /api/comments to this controller
public class CommentController {

    @Autowired  // Automatically injects the CommentService bean
    private CommentService commentService;

    /**
     * Retrieves all comments for a specified project ID.
     *
     * @param projectId the ID of the project whose comments are to be retrieved.
     * @return a ResponseEntity containing a map with the list of comments and an HTTP status code.
     * 		   response with the list of comments and HTTP status 200 (OK)
     */
    @GetMapping(value = "/comment/getallcomments/{projectId}")
    public ResponseEntity<Object> getAllComments(@PathVariable("projectId") String id) {
        // Call the service layer to get all comments for the specified project ID
        Map<String, Object> response = commentService.getAllComments(id);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
    
    /**
     * Adds a new comment to the specified project.
     *
     * @param comment the Comment object containing details of the comment to be added.
     * @return a ResponseEntity containing a map with the added comment and an HTTP status code.
     */
    @PostMapping(value = "/comment/addcomment")
    public ResponseEntity<Object> addComment(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
        // Call the service layer to add the new comment
    	return new ResponseEntity<>(commentService.addComment(request,payload),HttpStatus.OK); 
        // Return the response with the added comment and HTTP status 200 (OK)
    }
    
  
    /**
     * Deletes a comment based on its ID.
     *
     * @param id the ID of the comment to be deleted.
     * @return a ResponseEntity containing a map with a success message and an HTTP status code.
     */
    @DeleteMapping(value = "/comment/deletecomment/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("id") int id) {

		return new ResponseEntity<>(commentService.deleteComment(id),HttpStatus.OK);
    }

    /**
     * Edits an existing comment.
     *
     * @param comment the Comment object containing the updated details of the comment.
     * @return a ResponseEntity containing a map with the updated comment and an HTTP status code.
     */
    @PostMapping(value = "/comment/editcomment")
    public ResponseEntity<Object> editComment(@RequestBody JSONObject body) {
	 	return new ResponseEntity<>(commentService.editComment(body),HttpStatus.OK);
    }
}
