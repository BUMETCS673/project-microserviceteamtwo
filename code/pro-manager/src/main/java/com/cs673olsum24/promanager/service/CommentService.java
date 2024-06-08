package com.cs673olsum24.promanager.service;

import com.cs673olsum24.promanager.entity.Comment;
import java.util.Map;


/**
 * Author : Praveen Singh
 */

public interface CommentService {
    Map<String, Object> getAllComments(int projectId);
    Map<String, Object> addComment(Comment comment);
    Map<String, Object> deleteComment(int id);
    Map<String, Object> editComment(Comment comment);
}
