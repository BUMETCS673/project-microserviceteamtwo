package com.cs673olsum24.promanager.controller;

import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private Comment comment;
    private Map<String, Object> commentResponse;

    @BeforeEach
    void setUp() {
        comment = new Comment("proj_001", "This is a sample comment", 2, new Date());

        commentResponse = new HashMap<>();
        commentResponse.put("comment", comment);
    }

    @Test
    void testGetAllComments() throws Exception {
        String projectId = "proj_001";
        Map<String, Object> response = new HashMap<>();
        response.put("comments", comment);

        Mockito.when(commentService.getAllComments(projectId)).thenReturn(response);

        mockMvc.perform(get("/apiv1/comment/getallcomments/{projectId}", projectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments.projectId", is(comment.getProjectId())))
                .andExpect(jsonPath("$.comments.comments", is(comment.getComments())));
    }

    @Test
    void testAddComment() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("projectId", "proj_001");
        payload.put("comments", "This is a sample comment");
        payload.put("userId", 2);

        Mockito.when(commentService.addComment(Mockito.any(), Mockito.any())).thenReturn(commentResponse);

        mockMvc.perform(post("/apiv1/comment/addcomment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment.projectId", is(comment.getProjectId())))
                .andExpect(jsonPath("$.comment.comments", is(comment.getComments())));
    }

    @Test
    void testDeleteComment() throws Exception {
        int commentId = 1;
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment deleted successfully");

        Mockito.when(commentService.deleteComment(commentId)).thenReturn(response);

        mockMvc.perform(delete("/apiv1/comment/deletecomment/{id}", commentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Comment deleted successfully")));
    }

    @SuppressWarnings("unchecked")
	@Test
    void testEditComment() throws Exception {
        JSONObject body = new JSONObject();
        body.put("id", 1);
        body.put("comments", "Updated comment");

        Map<String, Object> response = new HashMap<>();
        response.put("comment", comment);

        Mockito.when(commentService.editComment(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/apiv1/comment/editcomment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body.toJSONString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment.projectId", is(comment.getProjectId())))
                .andExpect(jsonPath("$.comment.comments", is(comment.getComments())));
    }
}
