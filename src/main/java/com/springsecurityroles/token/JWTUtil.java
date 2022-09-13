package com.springsecurityroles.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springsecurityroles.filters.SecurityConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

	

	    public String generateToken(String email, String roles) throws IllegalArgumentException, JWTCreationException {
	        return JWT.create()
	                .withSubject("UserDetails")
	                .withClaim("email", email)
	                .withClaim("roles", roles)
	                .withIssuedAt(new Date())
	                .withIssuer("lewis.com")
	                
	                .sign(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()));
	    }

	    public  static Map validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
	        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()))
	        		.withIssuer("lewis.com")
	        		.withSubject("UserDetails")
	                .build();
	        
	        DecodedJWT jwt = verifier.verify(token);
	        
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("email", jwt.getClaim("email").asString());
	        map.put("roles", jwt.getClaim("roles").asString());
	        return map;
	    }
	
}
