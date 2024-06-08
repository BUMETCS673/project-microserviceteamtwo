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

    @GetMapping(value = "/getallcomments/{projectId}")
    public ResponseEntity<Object> getAllComments(@PathVariable int projectId) {
        return new ResponseEntity<>(commentService.getAllComments(projectId), HttpStatus.OK);
    }

    @PostMapping(value = "/addcomment")
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.addComment(comment), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletecomment/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable int id) {
        return new ResponseEntity<>(commentService.deleteComment(id), HttpStatus.OK);
    }

    @PostMapping(value = "/editcomment")
    public ResponseEntity<Object> editComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.editComment(comment), HttpStatus.OK);
    }
}
