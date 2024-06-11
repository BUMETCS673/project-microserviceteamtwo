package com.cs673olsum24.promanager.controller;

import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
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
@RequestMapping("/api/comments")  // Maps HTTP requests to /api/comments to this controller
public class CommentController {

    @Autowired  // Automatically injects the CommentService bean
    private CommentService commentService;

    /**
     * Retrieves all comments for a specified project ID.
     *
     * @param projectId the ID of the project whose comments are to be retrieved.
     * @return a ResponseEntity containing a map with the list of comments and an HTTP status code.
     */
    @GetMapping(value = "/getallcomments/{projectId}")
    public ResponseEntity<Object> getAllComments(@PathVariable int projectId) {
        // Call the service layer to get all comments for the specified project ID
        Map<String, Object> response = commentService.getAllComments(projectId);
        // Return the response with the list of comments and HTTP status 200 (OK)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Adds a new comment to the specified project.
     *
     * @param comment the Comment object containing details of the comment to be added.
     * @return a ResponseEntity containing a map with the added comment and an HTTP status code.
     */
    @PostMapping(value = "/addcomment")
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {
        // Call the service layer to add the new comment
        Map<String, Object> response = commentService.addComment(comment);
        // Return the response with the added comment and HTTP status 200 (OK)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a comment based on its ID.
     *
     * @param id the ID of the comment to be deleted.
     * @return a ResponseEntity containing a map with a success message and an HTTP status code.
     */
    @DeleteMapping(value = "/deletecomment/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable int id) {
        // Call the service layer to delete the comment by its ID
        Map<String, Object> response = commentService.deleteComment(id);
        // Return the response with a success message and HTTP status 200 (OK)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Edits an existing comment.
     *
     * @param comment the Comment object containing the updated details of the comment.
     * @return a ResponseEntity containing a map with the updated comment and an HTTP status code.
     */
    @PostMapping(value = "/editcomment")
    public ResponseEntity<Object> editComment(@RequestBody Comment comment) {
        // Call the service layer to edit the comment with the updated details
        Map<String, Object> response = commentService.editComment(comment);
        // Return the response with the updated comment and HTTP status 200 (OK)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
