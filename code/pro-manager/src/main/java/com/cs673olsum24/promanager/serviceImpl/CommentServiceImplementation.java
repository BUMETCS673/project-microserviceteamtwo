package com.cs673olsum24.promanager.serviceImpl;

import com.cs673olsum24.promanager.dao.CommentDAO;
import com.cs673olsum24.promanager.entity.Comment;
import com.cs673olsum24.promanager.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : Praveen Singh
 */

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Map<String, Object> getAllComments(int projectId) {
        Map<String, Object> response = new HashMap<>();
        List<Comment> comments = commentDAO.getAllComments(projectId);
        response.put("comments", comments);
        return response;
    }

    @Override
    public Map<String, Object> addComment(Comment comment) {
        Map<String, Object> response = new HashMap<>();
        Comment addedComment = commentDAO.addComment(comment);
        response.put("comment", addedComment);
        return response;
    }

    @Override
    public Map<String, Object> deleteComment(int id) {
        Map<String, Object> response = new HashMap<>();
        commentDAO.deleteComment(id);
        response.put("response", "Success");
        return response;
    }

    @Override
    public Map<String, Object> editComment(Comment comment) {
        Map<String, Object> response = new HashMap<>();
        Comment editedComment = commentDAO.editComment(comment);
        response.put("comment", editedComment);
        return response;
    }
}
