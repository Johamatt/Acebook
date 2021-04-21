package com.online.aceBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.aceBook.model.Post;
import com.online.aceBook.model.Profile;
import com.online.aceBook.model.User;
import com.online.aceBook.repository.PostRepository;
import com.online.aceBook.repository.ProfileRepository;
import com.online.aceBook.repository.UserRepository;



@CrossOrigin
@RequestMapping("/api/v1/")
@RestController
public class AcebookRestController {

	@Autowired
	private PostRepository postrepository;

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired 
	private UserRepository userRepository;

	

	/* < - - - - USER - - - - - > */

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findById(userId).get();
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return (List<User>) this.userRepository.findAll();
	}

	/* < - - - - POST - - - - - > */

	@GetMapping("/posts")
	public List<Post> getAllPosts() {
		return (List<Post>) this.postrepository.findAll();
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Long postId) {
		Post post = postrepository.findById(postId).get();
		return ResponseEntity.ok().body(post);
	}

	/* < - - - - - PROFILE- - - - > */

	@GetMapping("/profiles/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable(value = "id") Long profileId) {
		Profile profile = profileRepository.findById(profileId).get();
		return ResponseEntity.ok().body(profile);
	}

	@GetMapping("/profiles")
	public List<Profile> getAllProfiles() {
		return (List<Profile>) this.profileRepository.findAll();
	}
}