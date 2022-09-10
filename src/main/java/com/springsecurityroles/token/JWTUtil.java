package com.springsecurityroles.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springsecurityroles.filters.SecurityConstants;

import java.util.Date;


import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

	

	    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
	        return JWT.create()
	                .withSubject("User Details")
	                .withClaim("email", email)
	                .withIssuedAt(new Date())
	                .withIssuer("lewis.com")
	                .sign(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()));
	    }

	    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
	        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()))
	                .withSubject("User Details")
	                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
	                .build();
	        DecodedJWT jwt = verifier.verify(token);
	        return jwt.getClaim("email").asString();
	    }
	
}
