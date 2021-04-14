package com.online.aceBook.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.online.aceBook.model.Post;
import com.online.aceBook.repository.PostRepository;
import com.online.aceBook.repository.UserRepository;

@Controller
public class PostController {
	
	@Autowired UserRepository userRepository;
	
	@Autowired PostRepository postRepository;
	
	@RequestMapping(value="/savepost/{id}", method = RequestMethod.POST)
	public String save(@PathVariable Long id, @Validated @ModelAttribute Post post) throws IOException {
		
		Date date = new Date();
		post.setPostDate(date);
		postRepository.save(post);
		return "redirect:/" + userRepository.getOne(id);
	}
	
	

}
