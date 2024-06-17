package com.cs673olsum24.promanager.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "PROJECT_USER")
public class ProjectUser {
	
	@Id
    @GeneratedValue
    @Column(name = "PROJECT_USER_ID", nullable = false)
    private int PROJECT_USER_ID;
 
	@Column(name = "PROJECT_ID")
    private String PROJECT_ID;
	
    @Column(name = "USER_ID")
    private int USER_ID;
 
    
    @Column(name = "ROLE")
    private String ROLE;
    
    
   
    @Column(name = "created_at")
    private long created_at;
    
    @Column(name = "updated_at")
    private long updated_at;
   



  

	public int getPROJECT_USER_ID() {
		return PROJECT_USER_ID;
	}

	public void setPROJECT_USER_ID(int pROJECT_USER_ID) {
		PROJECT_USER_ID = pROJECT_USER_ID;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}
	
	public int getUSER_ID() {
			return USER_ID;
		}

	public void setUSER_ID(int uSER_ID) {
			USER_ID = uSER_ID;
		}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public long getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(long updated_at) {
		this.updated_at = updated_at;
	}

	

    

}
