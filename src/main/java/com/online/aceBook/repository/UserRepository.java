package com.online.aceBook.repository;

import org.springframework.data.repository.CrudRepository;
import com.online.aceBook.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}