package com.app.bean;

public class Buyer {
	
	private int userid;
	private String username;
	private  String password;

//	public enum Type {
//	       BUYER, SELLER;
//	     };
	     
	private Type type;
	
	public Buyer()
	{
		
	}

	public Buyer(int userid, String username, String password, Type type) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Buyer [userid=" + userid + ", username=" + username + ", password=" + password + ", type=" + type + "]";
	}
	
	
	
	

}
