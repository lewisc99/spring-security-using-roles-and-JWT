package com.springsecurityroles.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.springsecurityroles.config.ConvertToGrantedAuthority;
import com.springsecurityroles.service.MyUserDetailsService;
import com.springsecurityroles.token.JWTUtil;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	
	@Autowired
	private MyUserDetailsService userDetailsService;
	

    
    
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
        
       
    }

   
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JWTVerificationException, IOException {
       
    	
    	String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token != null) {
            // parse the token.
        	
        	 try {
        		 
                 String jwt = token.substring(7);
				Map claims = JWTUtil.validateTokenAndRetrieveSubject(jwt);
				
                 
                 if (claims != null) {
                     // new arraylist means authorities
                	 
     				String getRoles = (String) claims.get("roles");
            

                	 
                	 
                	 
                	return new UsernamePasswordAuthenticationToken(claims.get("email"), null, ConvertToGrantedAuthority.getRoles(getRoles));
                 }
                 
             
				
			} catch (JWTVerificationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

           

            return null;
        }

        return null;
    }
}