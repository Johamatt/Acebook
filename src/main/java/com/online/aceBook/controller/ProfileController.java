package com.online.aceBook.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.online.aceBook.model.Post;
import com.online.aceBook.model.Profile;
import com.online.aceBook.repository.ProfileRepository;
import com.online.aceBook.repository.UserRepository;

@Controller
public class ProfileController {
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public Map<String, Object> home(Principal principal) {
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("file", userRepository.findByUsername(principal.getName()).getAccountProfile().getProfileAvatar());
	    model.put("user", userRepository.findByUsername(principal.getName()));
	    model.put("posts", userRepository.findByUsername(principal.getName()).getAccountProfile().getPost());	      
	    model.put("userlist", userRepository.findAll());	    
	    model.put("post", new Post());
	    return model;
	}
	
	@RequestMapping(value="/file/{id}", method = RequestMethod.POST)
	public String save(@Validated @PathVariable Long id, @RequestParam("file") MultipartFile file, Principal principal) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (file.getSize() == 0) {
            return "redirect:/home"; 
        }
		Profile profile = userRepository.findByUsername(principal.getName()).getAccountProfile();
		profile.setProfileAvatar(file.getBytes());
		profile.setAvatarContentLength(file.getSize());
		profile.setAvatarContentType(file.getContentType());
		profileRepository.save(profile);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/viewfile/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
    	Profile p = userRepository.findById(id).get().getAccountProfile();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(p.getAvatarContentType()));       
        headers.add("Content-Disposition", "attachment; filename=" + "profilepic");
        headers.setContentLength(p.getAvatarContentLength());
        return new ResponseEntity<>(p.getProfileAvatar(), headers, HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/{user}", method = RequestMethod.GET)
    public String getProfile(@PathVariable String user, Model model, Principal principal) {
    	Long userId = Long.parseLong(user.split("\\.")[2]);  	    	
    	if (userId == userRepository.findByUsername(principal.getName()).getId()) {
    		return "redirect:/home";	
    	} 	
    	model.addAttribute("loggeduser", userRepository.findByUsername(principal.getName()));  	
    	model.addAttribute("user", userRepository.getOne(userId));
    	model.addAttribute("file", userRepository.getOne(userId).getAccountProfile().getProfileAvatar());
    	model.addAttribute("posts", userRepository.getOne(userId).getAccountProfile().getPost());
    	model.addAttribute("post", new Post());
    		return "profile";
    }   
}
