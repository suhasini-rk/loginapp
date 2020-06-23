package com.myproject.demo.bean;

import java.io.Serializable;

public class Login implements Serializable {
	String username;
	String password;
	String isloggedin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsloggedin() {
		return isloggedin;
	}
	public void setIsloggedin(String isloggedin) {
		this.isloggedin = isloggedin;
	}
	

}
