package com.springsecurityroles.models;

public class ResponseToken {
	
	private String formart;
	
	private String token;
	
	public ResponseToken() {}
	
	
	

	public ResponseToken(String formart, String token) {
	
		this.formart = formart;
		this.token = token;
	}




	public String getFormart() {
		return formart;
	}

	public void setFormart(String formart) {
		this.formart = formart;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
