package com.forum.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.user.model.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Long id) {
		Optional<User> user;
		
		user = userRepository.findById(id);
		return user.get();
			 
	}
	
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@Transactional
	public User createUser(User user) {
		userRepository.save(user);
		return user;
		
	}

	@Transactional
	public User updateUser(Long id, User user) {
		User userUpdated = getUserById(id);
		userUpdated.setName(user.getName());
		userUpdated.setUserName(user.getUserName());
		userUpdated.setTags(user.getTags());
				
		return userUpdated;
	}
	
	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
