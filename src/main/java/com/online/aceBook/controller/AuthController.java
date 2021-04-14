package com.online.aceBook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.online.aceBook.model.Profile;
import com.online.aceBook.model.Post;
import com.online.aceBook.model.User;
import com.online.aceBook.repository.ProfileRepository;
import com.online.aceBook.repository.UserRepository;

@Controller
public class AuthController {
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {		
		model.addAttribute("users", userRepository.findAll());
		return "login";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public String create(@ModelAttribute User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "redirect:/login";
        }
        
        List<Post> posts = new ArrayList<Post>();                  
        Profile profile = new Profile();
        profileRepository.save(profile);
        
        user.setPost(posts);
        user.setAccountProfile(profile);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setRole("USER");
        
        

        
            profile.setUser(user);
            userRepository.save(user);            
            return "redirect:/login";            
    }

}





