package com.springsecurityroles.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springsecurityroles.filters.SecurityConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

	

	    public String generateToken(String email, List<String> roles) throws IllegalArgumentException, JWTCreationException {
	        return JWT.create()
	                .withSubject("UserDetails")
	                .withClaim("email", email)
	                .withClaim("roles", roles)
	                .withIssuedAt(new Date())
	                .withIssuer("lewis.com")
	                
	                .sign(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()));
	    }

	    public  static Map<String, List<String>> validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
	    	
	    	Map<String, List<String>> claims = new HashMap<String, List<String>>();
	        List<String> usernameList = new ArrayList<>();
	        Claim roleClaim;
	      

	    	JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()))
	        		.withIssuer("lewis.com")
	        		.withSubject("UserDetails")
	                .build();
	        
	        DecodedJWT jwt = verifier.verify(token);
	        
	       
	        usernameList.add(jwt.getClaim("email").asString());
	        roleClaim = jwt.getClaim("roles");
	        List<String> rolesList = roleClaim.asList(String.class);
	 
	        
	        claims.put("email", usernameList);
	        claims.put("roles", rolesList);
	        return claims;
	    }
	
}
