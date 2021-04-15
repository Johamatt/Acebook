package com.online.aceBook.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String create(@ModelAttribute User user) throws IOException {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return "redirect:/login";
		}

		List<Post> posts = new ArrayList<Post>();
		Profile profile = new Profile();
		File f = (new File("src/main/resources/static/images/default-avatar.png"));
		byte[] fileContent = Files.readAllBytes(f.toPath());

		profile.setProfileAvatar(fileContent);
		profile.setAvatarContentType("image/png");
		profile.setAvatarContentLength(10056L);

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
