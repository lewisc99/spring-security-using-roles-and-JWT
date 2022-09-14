package com.springsecurityroles.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;



@Component
public class ConvertToGrantedAuthority {

	



	public static List<GrantedAuthority> getRoles(List<String> roles) {
		


		List<GrantedAuthority> authoritiesRole = new ArrayList<GrantedAuthority>(); 
			
		 
		if(roles != null)
			for (String role : roles)
			{
				 authoritiesRole.add(new SimpleGrantedAuthority(role));	
			}
		
				return authoritiesRole;
			}
	
	
	
}
