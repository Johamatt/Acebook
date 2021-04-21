package com.online.aceBook;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.online.aceBook.model.*;
import com.online.aceBook.repository.PostRepository;
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
	public CommandLineRunner demo( UserRepository userRepo, ProfileRepository profileRepo, PostRepository postRepo)  {
		return (args) -> {
			
			
			/* < - - - Create profile entities with default avatar - - - > */
			
			Profile p1 = new Profile();
			Profile p2 = new Profile();
			Profile p3 = new Profile();
			
		      File f = (new File("src/main/resources/static/images/default-avatar.png"));
		      byte[] fileContent = Files.readAllBytes(f.toPath());
		      
		        p1.setProfileAvatar(fileContent);
				p1.setAvatarContentType("image/png");
				p1.setAvatarContentLength(10056L);
	
				p2.setProfileAvatar(fileContent);
				p2.setAvatarContentType("image/png");
				p2.setAvatarContentLength(10056L);
				
				p3.setProfileAvatar(fileContent);
				p3.setAvatarContentType("image/png");
				p3.setAvatarContentLength(10056L);
					
			profileRepo.save(p1);
			profileRepo.save(p2);
			profileRepo.save(p3);
			
			/* < - - - Create user entities - - - > */
			
			List<Post> posts = new ArrayList<>();		
			List<User> friendlist = new ArrayList<>();		
			User john = new User("user", 
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", 
					"John", 
					"Doe", 
					"Johndoe@gmail.com", 
					"USER", 
					p1, 
					posts,
					friendlist);
			User user2 = new User("admin", 
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", 
					"Admin",
					"", 
					"acebook21@gmail.com", 
					"ADMIN", 
					p2, 
					posts,
					friendlist);		
			User jane = new User("user1", 
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", 
					"Jane",
					"Doe", 
					"Janedoe@gmail.com", 
					"USER", 
					p3, 
					posts,
					friendlist);
			
			userRepo.save(john);
			userRepo.save(user2);	
			userRepo.save(jane);	
			
			
			/* < - - - Add profile comment - - - > */
			
			Date date = new Date();		
			Post post = new Post(p1, "Hi John, how you doing?", date, jane);
			postRepo.save(post);
			
			
			/* < - - - Add friendship - - - > */
			
			List<User> johnfriendlist = john.getFriend();
			List<User> janefriendlist = jane.getFriend();
			
			johnfriendlist.add(jane);
			userRepo.save(john);
			
			janefriendlist.remove(0);
			janefriendlist.add(john);
			userRepo.save(jane);
			
			
			
			
			

			
			
		};
	}
}


