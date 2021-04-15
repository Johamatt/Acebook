package com.online.aceBook.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.online.aceBook.model.User;

import com.online.aceBook.repository.PostRepository;
import com.online.aceBook.repository.UserRepository;

@Controller
public class FriendController {
	
	@Autowired UserRepository userRepository;
	
	@Autowired PostRepository postRepository;
	
	
	@RequestMapping(value="/savefriend/{userid}/{friendid}", method = RequestMethod.POST)
	public String save(@Validated @PathVariable Long userid, @PathVariable Long friendid) throws IOException {
		
		
		User friend = userRepository.getOne(friendid);
		User user = userRepository.getOne(userid);
		
		List<User> userFriendList = user.getFriend();
		List<User> friendFriendlist = friend.getFriend();
		
		if (userFriendList.contains(friend) || friendFriendlist.contains(user)) {			
			return "redirect:/" + userRepository.getOne(userid);
		}
		
		friendFriendlist.add(user);
		friend.setFriend(friendFriendlist);
		userRepository.save(friend);
		
		
		userFriendList.add(friend);		
		user.setFriend(userFriendList);
		userRepository.save(user);
		
		return "redirect:/" + userRepository.getOne(userid);
	}

}
