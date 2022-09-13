package com.springsecurityroles.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table(name="user")
@Entity
public class Users implements UserDetails {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private boolean active;
	

	
	@JoinTable(
	        name = "user_role",
	        joinColumns = @JoinColumn(
	                name = "user_id"
	               
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "role_id"
	                
	        )
	)
	@ManyToMany(fetch=FetchType.EAGER,
    cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})

	private List<Role> roles;
	
	
	public Users()
	{
		
	}


	public Users(int id, String username, String password, boolean active) {
	
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
	}
	
	

	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	@Override
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	
		return checkGrantAuthorities();
	}

	private List<GrantedAuthority> checkGrantAuthorities() {
		
		
		List<GrantedAuthority> authoritiesRole = new ArrayList<GrantedAuthority>(); 
		
		 
		if(username!=null && getRoles()!=null && getRoles().isEmpty()==false)
				for(Role roleUser : getRoles()){
				
			
					 authoritiesRole.add(new SimpleGrantedAuthority(roleUser.getName()));	

				
					
				}
		
				return authoritiesRole;
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
	
		return active;
	}

	
}
