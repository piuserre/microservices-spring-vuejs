package com.piuserre.userms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piuserre.userms.model.User;
import com.piuserre.userms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user) {
		System.out.println(user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	public String findUsernameById(Long userId) {
		User user = userRepository.findById(userId).orElse(null);

		if (user != null)
			return user.getUsername();
		return null;
	}
}
