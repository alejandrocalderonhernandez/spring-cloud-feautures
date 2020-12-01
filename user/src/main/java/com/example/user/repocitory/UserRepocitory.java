package com.example.user.repocitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.user.entity.User;

@RepositoryRestResource(path = "user")
public interface UserRepocitory extends CrudRepository<User, Long>{

	public User findByUsername(String username);
	
}
