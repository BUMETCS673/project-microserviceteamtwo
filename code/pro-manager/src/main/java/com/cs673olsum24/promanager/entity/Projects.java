package com.cs673olsum24.promanager.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "project_ci")
public class Projects {
	
	@Id
    @GeneratedValue
    @Column(name = "project_id", nullable = false)
    private String project_id;
 
    @Column(name = "projectname")
    private String projectname;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "owner_id")
    private int owner_id;    
    
    @Column(name = "created_on")
    private long created_on;
    
    @Column(name = "updated_on")
    private long updated_on;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "active")
    private Boolean active;

	public String getProjectid() {
		return project_id;
	}

	public void setProjectid(String project_id) {
		this.project_id = project_id;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

    

}
