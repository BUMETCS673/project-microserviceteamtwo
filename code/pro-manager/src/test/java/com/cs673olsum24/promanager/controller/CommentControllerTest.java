//package com.cs673olsum24.promanager.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import com.cs673olsum24.promanager.ProManagerApplication;
//import com.cs673olsum24.promanager.service.CommentService;
//import com.cs673olsum24.promanager.service.ProjectServices;
//import com.cs673olsum24.promanager.serviceImpl.ProjectServiceImplementation;
//import com.fasterxml.jackson.core.JsonProcessingException;
//
///**
// * Author: Praveen Singh
// */
//
//@SpringBootTest(classes = ProManagerApplication.class)
//@ActiveProfiles("test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class CommentControllerTest {
//
//    @Autowired
//    private CommentService commentService;
//    
//    @Autowired 
//    private ProjectServices projectServices;
//
//  
//
//    private String testProjectId = "proj_001";
//    
//    @BeforeEach
//    public void setup() throws JsonProcessingException {
//      Map<String, Object> payload_projects = new HashMap<>();
//      payload_projects.put(
//          "projects",
//          new Object[] {
//            new HashMap<String, Object>() {
//              {
//                put("project_id", testProjectId);
//                put("projectname", "Initial Test Project");
//                put("owner_id", 123);
//                put("description", "Initial description");
//                put("created_on", 1622547800L);
//                put("updated_on", 1622547800L);
//                put("status", "Inactive");
//                put("type", "Initial");
//                put("active", true);
//              }
//            }
//          });
//      projectServices.addProject(null, payload_projects);
//
//     
//		Map<String, Object> payload = new HashMap<>();
//        JSONArray comments = new JSONArray();
//        JSONObject comment = new JSONObject();
//        comment.put("project_id", testProjectId);
//        comment.put("comments", "Initial Test Comment");
//        comment.put("user_id", 123);
//        comments.add(comment);
//        payload.put("comments", comments);
//
//        commentService.addComment(null, payload);
//    }
//
////    @SuppressWarnings("unchecked")
////	@BeforeEach
////    public void setup() {
////        // Prepare and insert a test comment
////        Map<String, Object> payload = new HashMap<>();
////        JSONArray comments = new JSONArray();
////        JSONObject comment = new JSONObject();
////        comment.put("project_id", testProjectId);
////        comment.put("comments", "Initial Test Comment");
////        comment.put("user_id", 123);
////        comments.add(comment);
////        payload.put("comments", comments);
////
////        commentService.addComment(null, payload);
////    }
//
//    @SuppressWarnings("unchecked")
//	@Test
//    @Order(1)
//    public void testGetAllComments() {
//        Map<String, Object> response = commentService.getAllComments(testProjectId);
//
//        assertNotNull(response);
//        assertNotNull(response.get("comments"));
//        assertTrue(response.get("comments") instanceof List);
//
//        List<?> comments = (List<?>) response.get("comments");
//        
//        System.out.println(comments);
//        assertFalse(comments.isEmpty(), "Comments list should not be empty");
//
//       
//        Map<?, ?> commentDetails = (Map<?, ?>) comments.get(0);
//        assertEquals(testProjectId, commentDetails.get("project_id"));
//        assertEquals("Initial Test Comment", commentDetails.get("comments"));
//    }
//    
//
////    @Test
////    @Order(1)
////    public void testRetrieveProject() {
////      String projectId = "proj_002";
////
////      try {
////               
////       
////        
////
////      
////        assertEquals("Initial Test Task", taskDetails.get("task_name"));
////        assertEquals("Initial description", taskDetails.get("description"));
////        assertEquals("To Do", taskDetails.get("status"));
////        assertEquals("Low", taskDetails.get("priority"));
////      } catch (JsonProcessingException e) {
////        e.printStackTrace();
////        assertNotNull(null, "JsonProcessingException was thrown");
////      }
////    }
//
////    
////
////    @Test
////    @Order(2)
////    public void testGetCommentsForNonExistentProject() {
////        String nonExistentProjectId = "nonexistent";
////        Map<String, Object> response = commentService.getAllComments(nonExistentProjectId);
////
////        assertNotNull(response);
////        assertTrue(response.containsKey("comments"));
////        assertTrue(((List<?>) response.get("comments")).isEmpty());
////    }
////
////    @SuppressWarnings("unchecked")
////	@Test
////    @Order(3)
////    public void testAddComment() {
////        Map<String, Object> payload = new HashMap<>();
////        JSONArray comments = new JSONArray();
////        JSONObject comment = new JSONObject();
////        comment.put("project_id", testProjectId);
////        comment.put("comments", "Another Test Comment");
////        comment.put("user_id", 456);
////        comments.add(comment);
////        payload.put("comments", comments);
////
////        Map<String, Object> response = commentService.addComment(null, payload);
////
////        assertNotNull(response);
////        assertEquals("OK", response.get("Response"));
////
////        Map<String, Object> allCommentsResponse = commentService.getAllComments(testProjectId);
////        List<?> commentsList = (List<?>) allCommentsResponse.get("comments");
////        assertEquals(2, commentsList.size());
////    }
////
////    @SuppressWarnings("unchecked")
////	@Test
////    @Order(4)
////    public void testDeleteComment() {
////        Map<String, Object> allCommentsResponse = commentService.getAllComments(testProjectId);
////        List<?> comments = (List<?>) allCommentsResponse.get("comments");
////        Map<String, Object> commentToDelete = (Map<String, Object>) comments.get(0);
////        int commentId = (int) commentToDelete.get("comment_id");
////
////        Map<String, Object> response = commentService.deleteComment(commentId);
////
////        assertNotNull(response);
////        assertEquals("Success", response.get("response"));
////
////        allCommentsResponse = commentService.getAllComments(testProjectId);
////        comments = (List<?>) allCommentsResponse.get("comments");
////        assertEquals(1, comments.size());
////    }
////
////    @SuppressWarnings("unchecked")
////	@Test
////    @Order(5)
////    public void testEditComment() {
////        Map<String, Object> allCommentsResponse = commentService.getAllComments(testProjectId);
////        List<?> comments = (List<?>) allCommentsResponse.get("comments");
////        Map<String, Object> commentToEdit = (Map<String, Object>) comments.get(0);
////        int commentId = (int) commentToEdit.get("comment_id");
////
////        JSONObject body = new JSONObject();
////        body.put("id", commentId);
////        body.put("comments", "Updated Test Comment");
////
////        Map<String, Object> response = commentService.editComment(body);
////
////        assertNotNull(response);
////        assertEquals("Success", response.get("Response"));
////
////        allCommentsResponse = commentService.getAllComments(testProjectId);
////        comments = (List<?>) allCommentsResponse.get("comments");
////        Map<String, Object> updatedComment = (Map<String, Object>) comments.get(0);
////        assertEquals("Updated Test Comment", updatedComment.get("comments"));
////    }
//}