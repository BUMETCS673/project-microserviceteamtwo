package com.cs673olsum24.promanager.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "tasks")
public class ProjectTasks {
	
	@Id
    @GeneratedValue
    @Column(name = "task_id", nullable = false)
    private String task_id;
 
	@Column(name = "project_id")
	private String project_id;
	  
    @Column(name = "task_name")
    private String task_name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private String status;  
    
    @Column(name = "priority")
    private String priority; 
    
    @Column(name = "assigned_user_id")
    private int assigned_user_id;
    
    @Column(name = "due_date")
    private long due_date;
    
    @Column(name = "created_on")
    private long created_on;
    
    @Column(name = "updated_on")
    private long updated_on;
    
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCreated_on() {
		return created_on;
	}

	public void setCreated_on(long created_on) {
		this.created_on = created_on;
	}

	public long getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(long updated_on) {
		this.updated_on = updated_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getAssigned_user_id() {
		return assigned_user_id;
	}

	public void setAssigned_user_id(int assigned_user_id) {
		this.assigned_user_id = assigned_user_id;
	}

	public long getDue_date() {
		return due_date;
	}

	public void setDue_date(long due_date) {
		this.due_date = due_date;
	}
    

}
