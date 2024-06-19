package com.cs673olsum24.promanager.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "APP_USER")
public class AppUser {
	
	@Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private int USER_ID;
 
	@Column(name = "NAME")
    private String NAME;
	

    @Column(name = "EMAIL")
    private String ROLE;
    
    @Column(name = "PASSWORD")
    private String PASSWORD;
    
  

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


	public String getNAME() {
		return NAME;
	}


	public void setNAME(String nAME) {
		NAME = nAME;
	}


	public String getPASSWORD() {
		return PASSWORD;
	}


	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}


    

    

    

}
