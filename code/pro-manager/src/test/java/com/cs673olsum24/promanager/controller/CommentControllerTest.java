package com.cs673olsum24.promanager.controller;

import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllComments() {
        Map<String, Object> serviceResponse = new HashMap<>();
        when(commentService.getAllComments(1)).thenReturn(serviceResponse);

        ResponseEntity<Object> response = commentController.getAllComments(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());
        verify(commentService, times(1)).getAllComments(1);
    }

    @Test
    void testAddComment() {
        Comment comment = new Comment(1, "Test comment", 1, new Date());
        Map<String, Object> serviceResponse = new HashMap<>();
        when(commentService.addComment(comment)).thenReturn(serviceResponse);

        ResponseEntity<Object> response = commentController.addComment(comment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());
        verify(commentService, times(1)).addComment(comment);
    }

    @Test
    void testDeleteComment() {
        Map<String, Object> serviceResponse = new HashMap<>();
        when(commentService.deleteComment(1)).thenReturn(serviceResponse);

        ResponseEntity<Object> response = commentController.deleteComment(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());
        verify(commentService, times(1)).deleteComment(1);
    }

    @Test
    void testEditComment() {
        Comment comment = new Comment(1, "Updated comment", 1, new Date());
        Map<String, Object> serviceResponse = new HashMap<>();
        when(commentService.editComment(comment)).thenReturn(serviceResponse);

        ResponseEntity<Object> response = commentController.editComment(comment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());
        verify(commentService, times(1)).editComment(comment);
    }
}