package com.springsecurityroles.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityroles.models.ResponseToken;
import com.springsecurityroles.models.dto.LoginDTO;
import com.springsecurityroles.service.MyUserDetailsService;
import com.springsecurityroles.token.JWTUtil;



@RestController
public class HomeController {

	
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	

	@Autowired
	private JWTUtil jwtUtil;
	
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@GetMapping
	public String Home()
	{
		return ("<h1>Welcome Home</h1>");
	}
	
	@GetMapping("/admin")
	public String Admin()
	{
		return ("<h1>Welcome Admin Page</h1>");
	}
	
	@GetMapping("/user")
	public String User()
	{
		return ("<h1>Welcome User Page</h1>");
	}
	
	
	
	@PostMapping(value="/login")

	public ResponseEntity<ResponseToken> LogIn(@RequestBody LoginDTO login) throws Exception
	{
		
		 
	    	try 
			{
				
		        final Authentication authentication = 	authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								login.getUsername(),login.getPassword()));
		        
		        
		        
				

		       SecurityContextHolder.getContext().setAuthentication(authentication); 
		       
		        
		        
			}
			catch (BadCredentialsException e)
			{
				throw new Exception("Incorrect username or password", e);
			}
	    	  	
	    	
	    	List<String> getRoles = new ArrayList<>();
	    	Collection<? extends GrantedAuthority> roles = userDetailsService.loadUserByUsername(login.getUsername()).getAuthorities();

	    	
	    	for (GrantedAuthority role: roles)
	    	{
	    		String newRole = role.getAuthority();
	    		
	    		getRoles.add(newRole);
	    	}
	    	
	    	
	    	System.out.println(roles);
	    	 
	  
	    	
	    	String token =  jwtUtil.generateToken(login.getUsername(), getRoles);
	    	
	    	ResponseToken responseToken =  new ResponseToken("JWT",token);
	    	
    	  	
            return ResponseEntity.ok().body(responseToken);

	} 
	

	@PostMapping(value="/logout")
	public ResponseEntity<String> logout()
	{
		

	    if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
	    {
	    	//SecurityContextHolder.clearContext();
	    	SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false); 
	    	return ResponseEntity.ok().body("Logout");

	    }
	    else
	    	
	    {
	    	return ResponseEntity.ok().body("not Logout");
	    }
	     
	       
	}


}
