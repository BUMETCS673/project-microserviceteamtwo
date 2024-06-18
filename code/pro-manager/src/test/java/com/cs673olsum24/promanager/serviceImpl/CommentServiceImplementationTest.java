package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.CommentDAO;
import com.cs673olsum24.promanager.entity.Comment;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Author: Praveen Singh
 */

@SpringBootTest
@ActiveProfiles("test")
class CommentServiceImplementationTest {

    @Autowired
    private CommentServiceImplementation commentService;

    @Autowired
    private CommentDAO commentDAO;

    private Comment testComment;

    @BeforeEach
    void setUp() {
        testComment = new Comment("project001", "Test Comment", 123, new Date());
        commentDAO.addComment(testComment);
    }

    @SuppressWarnings("unchecked")
	@Test
    void testGetAllComments() {
        Map<String, Object> response = commentService.getAllComments("project001");
        assertNotNull(response);
        assertNotNull(response.get("comments"));
        List<?> comments = (List<?>) response.get("comments");
        assertFalse(comments.isEmpty());
        Map<String, Object> firstComment = (Map<String, Object>) comments.get(0);
        assertEquals("project001", firstComment.get("project_id"));
        assertEquals("Test Comment", firstComment.get("comments"));
        assertEquals(123, firstComment.get("user_id"));
    }

    @Test
    void testAddComment() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("comments", new Object[]{new Comment("project001", "Another Test Comment", 456, new Date())});

        Map<String, Object> response = commentService.addComment(null, payload);
        assertNotNull(response);
        assertEquals("OK", response.get("Response"));

        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        assertEquals(2, comments.size());
    }

    @Test
    void testDeleteComment() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        int commentIdToDelete = (int) comments.get(0).get("comment_id");

        Map<String, Object> response = commentService.deleteComment(commentIdToDelete);
        assertNotNull(response);
        assertEquals("Success", response.get("response"));

        comments = commentDAO.getAllComments("project001");
        assertTrue(comments.isEmpty());
    }

    @SuppressWarnings("unchecked")
	@Test
    void testEditComment() {
        List<Map<String, Object>> comments = commentDAO.getAllComments("project001");
        int commentIdToEdit = (int) comments.get(0).get("comment_id");
        String updatedComment = "Updated Test Comment";
        JSONObject body = new JSONObject();
        body.put("id", commentIdToEdit);
        body.put("comments", updatedComment);

        Map<String, Object> response = commentService.editComment(body);
        assertNotNull(response);
        assertEquals("Success", response.get("Response"));

        comments = commentDAO.getAllComments("project001");
        Map<String, Object> firstComment = comments.get(0);
        assertEquals(updatedComment, firstComment.get("comments"));
    }
}
