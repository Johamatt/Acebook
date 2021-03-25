package com.online.aceBook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.online.aceBook.model.*;
import com.online.aceBook.repository.ProfileRepository;
import com.online.aceBook.repository.UserRepository;

@SpringBootApplication
public class AceBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AceBookApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CommandLineRunner demo( UserRepository userRepo, ProfileRepository profileRepo)  {
		return (args) -> {
			
			
						
			Profile p1 = new Profile();
			Profile p2 = new Profile();
			profileRepo.save(p1);
			profileRepo.save(p2);
			
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "Joha", "matt", "user1@gmail.com", "USER", p1);
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin", "ad", "admin@gmail.com", "ADMIN", p2);
			userRepo.save(user1);
			userRepo.save(user2);			
		};
	}
}


