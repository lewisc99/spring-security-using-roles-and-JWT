package com.springsecurityroles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurityroles.models.Users;



@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

	
	Optional<Users> findByUsername(String username);
}
