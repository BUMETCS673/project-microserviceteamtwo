package com.cs673olsum24.promanager.controller;

import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


import com.cs673olsum24.promanager.service.*;

@RestController
@RequestMapping("/api/comments")

/**
 * Author : Praveen Singh
 */

public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/getallcomments/{projectId}")
    public ResponseEntity<Object> getAllComments(@PathVariable int projectId) {
        Map<String, Object> response = commentService.getAllComments(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/addcomment")
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {
        Map<String, Object> response = commentService.addComment(comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletecomment/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable int id) {
        Map<String, Object> response = commentService.deleteComment(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/editcomment")
    public ResponseEntity<Object> editComment(@RequestBody Comment comment) {
        Map<String, Object> response = commentService.editComment(comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
