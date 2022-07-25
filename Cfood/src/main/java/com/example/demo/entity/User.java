package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uId;
	
	@NotEmpty(message = "Username must be filled")
	//@Size(min = 9, message = "Field should have min 9 characters!")
	@Column(name="u_name",nullable = false)
	private String uName;
	
	
	@NotEmpty(message = "Password must be filled")
	//@Size(min = 8, message = "Password must be minimum 8 characters")
	@Column(name="u_password")
	private String uPassword;
	
	
	@NotEmpty(message = "Email must be filled")
	@Email(message="Invalid Email format")
	@Column(name="u_email")
	private String eMail;
	
	
	@NotEmpty(message = "Moble no  must be filled")
	//@Size(min = 10,max = 10, message = "Invalid mobile format")
	@Column(name="mobileno")
	private String mobileNo;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public User(String uName, String uPassword, String eMail, String mobileNo) {
		super();
		this.uName = uName;
		this.uPassword = uPassword;
		this.eMail = eMail;
		this.mobileNo = mobileNo;
	}
	
	public User() {
		
	}
	
	

}
