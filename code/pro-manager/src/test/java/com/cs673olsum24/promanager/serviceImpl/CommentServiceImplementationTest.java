package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.CommentDAO;
import com.cs673olsum24.promanager.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Author: Praveen Singh
 */

class CommentServiceImplementationTest {

    @Mock
    private CommentDAO commentDAO;

    @InjectMocks
    private CommentServiceImplementation commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllComments() {
        List<Comment> comments = Arrays.asList(new Comment(1, "Comment 1", 1, new Date()));
        when(commentDAO.getAllComments(1)).thenReturn(comments);

        Map<String, Object> result = commentService.getAllComments(1);

        assertEquals(comments, result.get("comments"));
        verify(commentDAO, times(1)).getAllComments(1);
    }

    @Test
    void testAddComment() {
        Comment comment = new Comment(1, "New comment", 1, new Date());
        when(commentDAO.addComment(comment)).thenReturn(comment);

        Map<String, Object> result = commentService.addComment(comment);

        assertEquals(comment, result.get("comment"));
        verify(commentDAO, times(1)).addComment(comment);
    }

    @Test
    void testDeleteComment() {
        doNothing().when(commentDAO).deleteComment(1);

        Map<String, Object> result = commentService.deleteComment(1);

        assertEquals("Success", result.get("response"));
        verify(commentDAO, times(1)).deleteComment(1);
    }

    @Test
    void testEditComment() {
        Comment comment = new Comment(1, "Updated comment", 1, new Date());
        when(commentDAO.editComment(comment)).thenReturn(comment);

        Map<String, Object> result = commentService.editComment(comment);

        assertEquals(comment, result.get("comment"));
        verify(commentDAO, times(1)).editComment(comment);
    }
}