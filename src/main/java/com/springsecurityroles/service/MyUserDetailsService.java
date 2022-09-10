package com.springsecurityroles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springsecurityroles.models.Users;
import com.springsecurityroles.repository.UserRepository;

@Service
@Component
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<Users> user = userRepository.findByUsername(username);
		
		
		
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username ));
		
		return user.get(); 
	}

}
