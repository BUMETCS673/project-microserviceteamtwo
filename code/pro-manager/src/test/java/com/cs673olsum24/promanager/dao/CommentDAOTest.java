package com.cs673olsum24.promanager.dao;

import com.cs673olsum24.promanager.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Author: Praveen Singh
 */

class CommentDAOTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private NamedParameterJdbcTemplate template;

    @Mock
    private Query query;

    @InjectMocks
    private CommentDAO commentDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("unchecked")
	@Test
    void testAddComment() {
        Comment comment = new Comment(1, "New comment", 1, new Date());
        when(template.execute(anyString(), anyMap(), any(PreparedStatementCallback.class))).thenReturn(1);

        Comment result = commentDAO.addComment(comment);

        assertEquals(comment, result);
        verify(template).execute(eq("INSERT INTO comments (project_id, comments, user_id, created_on) VALUES (:project_id, :comments, :user_id, :created_on)"), anyMap(), any(PreparedStatementCallback.class));
    }

    @SuppressWarnings("unchecked")
	@Test
    void testDeleteComment() {
        when(template.execute(anyString(), anyMap(), any(PreparedStatementCallback.class))).thenReturn(1);

        commentDAO.deleteComment(1);

        verify(template).execute(eq("DELETE FROM comments WHERE id = :comment_id"), anyMap(), any(PreparedStatementCallback.class));
    }

    @SuppressWarnings("unchecked")
	@Test
    void testEditComment() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setComments("Updated comment");
        when(template.execute(anyString(), anyMap(), any(PreparedStatementCallback.class))).thenReturn(1);

        Comment result = commentDAO.editComment(comment);

        assertEquals(comment, result);
        verify(template).execute(eq("UPDATE comments SET comments = :comments WHERE id = :comment_id"), anyMap(), any(PreparedStatementCallback.class));
    }
}