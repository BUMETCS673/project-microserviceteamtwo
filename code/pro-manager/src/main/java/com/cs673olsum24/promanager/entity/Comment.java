package com.cs673olsum24.promanager.entity;

import jakarta.persistence.*;
import java.util.Date;


/**
 * Author : Praveen Singh
 */

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "created_on")
    private Date createdOn;

    // Constructors

    public Comment() {
    }

    public Comment(int projectId, String comments, int userId, Date createdOn) {
        this.projectId = projectId;
        this.comments = comments;
        this.userId = userId;
        this.createdOn = createdOn;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}