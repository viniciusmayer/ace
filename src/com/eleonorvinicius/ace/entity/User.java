package com.eleonorvinicius.ace.entity;

import java.util.Calendar;
import java.util.Random;

public class User extends Entity {

	private String email;
	private String password;
	private Calendar created;
	private Calendar updated;

	public User(String email, String password) {
		this.setId(new Random().nextLong());
		this.email = email;
		this.password = password;
		this.created = Calendar.getInstance();
		this.updated = Calendar.getInstance();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getUpdated() {
		return updated;
	}

	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}

}
