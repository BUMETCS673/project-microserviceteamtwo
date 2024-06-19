package com.cs673olsum24.promanager.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Author : Praveen Singh
 * 
 * The Comment class represents a comment entity in the ProManager application.
 * It maps to the "comments" table in the database and contains details about each comment,
 * such as the project it belongs to, the content of the comment, the user who made the comment,
 * and the timestamp when the comment was created.
 */

@Entity  // Indicates that this class is a JPA entity.
@Table(name = "comments")  // Maps this entity to the "comments" table in the database.
public class Comment {
    
    // Fields

    @Id  // Specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indicates that the ID should be generated automatically by the database.
    private int id;  // Unique identifier for the comment.

    @Column(name = "project_id")  // Maps this field to the "project_id" column in the "comments" table.
    private String projectId;  // ID of the project to which the comment belongs.

    @Column(name = "comments", columnDefinition = "TEXT")  // Maps this field to the "comments" column, specifying the data type as TEXT.
    private String comments;  // The content of the comment.

    @Column(name = "user_id")  // Maps this field to the "user_id" column in the "comments" table.
    private int userId;  // ID of the user who made the comment.

    @Column(name = "created_on")  // Maps this field to the "created_on" column in the "comments" table.
    private Date createdOn;  // The timestamp when the comment was created.

    // Constructors

    /**
     * Default no-argument constructor.
     * Required by JPA for entity instantiation.
     */
    public Comment() {
    }

    /**
     * Parameterized constructor to create a Comment with specified details.
     *
     * @param projectId the ID of the project to which the comment belongs.
     * @param comments  the content of the comment.
     * @param userId    the ID of the user who made the comment.
     * @param createdOn the timestamp when the comment was created.
     */
    public Comment(String projectId, String comments, int userId, Date createdOn) {
        this.projectId = projectId;
        this.comments = comments;
        this.userId = userId;
        this.createdOn = createdOn;
    }

    // Getters and Setters

    /**
     * Gets the ID of the comment.
     *
     * @return the ID of the comment.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the comment.
     *
     * @param id the new ID of the comment.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the project to which the comment belongs.
     *
     * @return the project ID.
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the ID of the project to which the comment belongs.
     *
     * @param string the new project ID.
     */
    public void setProjectId(String string) {
        this.projectId = string;
    }

    /**
     * Gets the content of the comment.
     *
     * @return the content of the comment.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the content of the comment.
     *
     * @param comments the new content of the comment.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the ID of the user who made the comment.
     *
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who made the comment.
     *
     * @param userId the new user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the timestamp when the comment was created.
     *
     * @return the creation timestamp.
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the timestamp when the comment was created.
     *
     * @param createdOn the new creation timestamp.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

	
}