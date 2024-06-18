package com.cs673olsum24.promanager.dao;

import com.cs673olsum24.promanager.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Praveen Singh
 */


@SpringBootTest
@ActiveProfiles("test")
class CommentDAOTest {

    @Autowired
    private CommentDAO commentDAO;

    private Comment testComment;

    @BeforeEach
    void setUp() {
        testComment = new Comment("project001", "Test Comment", 123, new Date());
        commentDAO.addComment(testComment);
    }

    @Test
    void testGetAllComments() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        assertFalse(comments.isEmpty());
        Map<String, Object> firstComment = comments.get(0);
        assertEquals("project001", firstComment.get("project_id"));
        assertEquals("Test Comment", firstComment.get("comments"));
        assertEquals(123, firstComment.get("user_id"));
    }

    @Test
    void testGetAllCommentsForNonExistentProject() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("nonexistent");
        assertTrue(comments.isEmpty());
    }

    @Test
    void testAddComment() {
        Comment newComment = new Comment("project001", "Another Test Comment", 456, new Date());
        commentDAO.addComment(newComment);
        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        assertEquals(2, comments.size());
    }

    @Test
    void testDeleteComment() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        int commentIdToDelete = (int) comments.get(0).get("comment_id");
        String response = commentDAO.deleteComment(commentIdToDelete);
        assertEquals("success", response);
        comments = commentDAO.getAllComments("project001");
        assertTrue(comments.isEmpty());
    }

    @Test
    void testEditComment() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        int commentIdToEdit = (int) comments.get(0).get("comment_id");
        Comment updatedComment = new Comment();
        updatedComment.setId(commentIdToEdit);
        updatedComment.setComments("Updated Test Comment");
        commentDAO.editComment(updatedComment);
        comments = commentDAO.getAllComments("project001");
        Map<String, Object> firstComment = comments.get(0);
        assertEquals("Updated Test Comment", firstComment.get("comments"));
    }
}
