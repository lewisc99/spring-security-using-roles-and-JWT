package com.springsecurityroles.controller;




import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurityroles.filters.SecurityConstants;
import com.springsecurityroles.models.Users;
import com.springsecurityroles.models.dto.LoginDTO;
import com.springsecurityroles.token.JWTUtil;



@RestController
public class HomeController {

	
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
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
	
	/*
	
	@PostMapping(value="/login")

	public ResponseEntity<String> LogIn(@RequestBody LoginDTO login) throws Exception
	{
		
		try 
		{
			
	      
	        
	        
	        final Authentication authentication = 	authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							login.getUsername(),login.getPassword()));
			

	      // SecurityContextHolder.getContext().setAuthentication(authentication); //to authenticate using the default filter.
	      if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
	      {
	    	  	SecurityContextHolder.getContext().setAuthentication(authentication);
	    	  	
	    	  
	    	  	String token = JWT.create()
	                    .withSubject(login.getUsername())
	                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
	                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
	    	  	
	    	  	
	            return ResponseEntity.ok().body(token);
	      }
	    

	        
		}
		catch (BadCredentialsException e)
		{
			throw new Exception("Incorrect username or password", e);
		}
		

		
		
		return ResponseEntity.ok().build();
		
	} */
	
	@PostMapping(value="/login")

	public ResponseEntity<String> LogIn(@RequestBody LoginDTO login) throws Exception
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
	    	  	
	        final UserDetails userDetails = userDetailsService
	                    .loadUserByUsername(login.getUsername());
	        
	      
	        
	 
	    	String token =  jwtUtil.generateToken(login.getUsername());
    	  	
            return ResponseEntity.ok().body("Controller token: " + token);

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
