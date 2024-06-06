package com.cs673olsum24.promanager.controller;

import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")

/**
 * Author : Praveen Singh
 */

public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable int projectId) {
        List<Comment> comments = commentService.getAllComments(projectId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editComment(@RequestBody Comment comment) {
        commentService.editComment(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}