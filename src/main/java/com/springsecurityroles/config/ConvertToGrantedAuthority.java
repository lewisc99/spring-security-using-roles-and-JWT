package com.springsecurityroles.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;



@Component
public class ConvertToGrantedAuthority {

	



	public static List<GrantedAuthority> getRoles(String roles) {
		
		
		String rolesFixed =  roles.substring(1, roles.length() -1);
		
		List<GrantedAuthority> authoritiesRole = new ArrayList<GrantedAuthority>(); 
		
		 
		if(roles != null)
				
			
		 authoritiesRole.add(new SimpleGrantedAuthority(rolesFixed));	


		
				return authoritiesRole;
			}
	
	
	
}
