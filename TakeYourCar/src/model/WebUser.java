package model;

import java.io.Serializable;

public class WebUser extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String email;
	private boolean admin;
	
	public WebUser() {
		super();
		username = "";
		password = "";
		email = "";
		admin = false;
	}

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
	
	public void setAdmin(boolean value) {
		admin = value;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return "username : " + username;
	}
	
}
