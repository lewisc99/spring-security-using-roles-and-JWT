package com.springsecurityroles.service;


import java.util.ArrayList;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springsecurityroles.models.Role;
import com.springsecurityroles.models.Users;

public class MyUserDetails implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	private Users user;
	private List<GrantedAuthority> authorities;
	
	
	
	public MyUserDetails(Users user)
	{
		this.user = user;
		
		this.authorities = 	checkGrantAuthorities(this.user);
		
			
				
				}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	
		return authorities;
	}

	private List<GrantedAuthority> checkGrantAuthorities(Users user) {
		
		
		List<GrantedAuthority> authoritiesRole = new ArrayList<GrantedAuthority>(); 
		
		 
		if(user!=null && user.getRoles()!=null && user.getRoles().isEmpty()==false)
				for(Role roleUser : user.getRoles()){
				
					
					 authoritiesRole.add(new SimpleGrantedAuthority(roleUser.getName()));	
					
					
				}
		
				return authoritiesRole;
			}
	
	
	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
	
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return user.isActive();
	}

}
