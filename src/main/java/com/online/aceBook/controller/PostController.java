package com.online.aceBook.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.online.aceBook.model.Post;
import com.online.aceBook.model.Profile;
import com.online.aceBook.repository.PostRepository;
import com.online.aceBook.repository.UserRepository;

@Controller
public class PostController {
	
	@Autowired UserRepository userRepository;
	
	@Autowired PostRepository postRepository;
	
	@RequestMapping(value="/savepost/{id}", method = RequestMethod.POST)
	public String save(@PathVariable Long id, @ModelAttribute Post post) throws IOException {
		postRepository.save(post);
		return "redirect:/" + userRepository.getOne(id);
	}
	
	

}
