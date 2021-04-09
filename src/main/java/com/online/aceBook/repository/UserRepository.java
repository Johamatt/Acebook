package com.online.aceBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.online.aceBook.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}