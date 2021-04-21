package com.online.aceBook.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.online.aceBook.model.User;
import com.online.aceBook.repository.UserRepository;

@Controller
public class SearchController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(value = "searchtxt") String searchtxt, Model model, Principal principal) {

		List<User> userlist = userRepository.findAll().stream()
					.filter(user -> user.getFullname().contains(searchtxt))
					.collect(Collectors.toList());
		
		System.out.println(userlist);
		
		model.addAttribute("loggeduser", userRepository.findByUsername(principal.getName()));
		model.addAttribute("searchresults", userlist);
		
		return "searchresults";
	}

}
