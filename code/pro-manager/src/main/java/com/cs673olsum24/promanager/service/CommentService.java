package com.cs673olsum24.promanager.service;

import com.cs673olsum24.promanager.entity.Comment;

import java.util.List;


/**
 * Author : Praveen Singh
 */

public interface CommentService {
    List<Comment> getAllComments(int projectId);
    void addComment(Comment comment);
    void deleteComment(int id);
    void editComment(Comment comment);

}