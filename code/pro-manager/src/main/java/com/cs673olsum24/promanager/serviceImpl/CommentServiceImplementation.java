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
    public List<Comment> getAllComments(int projectId) {
        return commentDAO.getAllComments(projectId);
    }

    @Override
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentDAO.deleteComment(id);
    }

    @Override
    public void editComment(Comment comment) {
        commentDAO.editComment(comment);
    }
}