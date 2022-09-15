package com.springsecurityroles.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Return A Token")
public class ResponseToken {
	
	
	@ApiModelProperty(notes = "Authentication format")
	private String formart;
	
	
	@ApiModelProperty(notes="The Token Generated")
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
