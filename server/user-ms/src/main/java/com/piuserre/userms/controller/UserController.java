package com.piuserre.userms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piuserre.userms.model.Role;
import com.piuserre.userms.model.User;
import com.piuserre.userms.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		if (userService.findByUsername(user.getUsername()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		user.setRole(Role.USER);
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	public ResponseEntity<?> getUser(Principal principal) {
		if (principal == null || principal.getName() == null)
			return new ResponseEntity<>(HttpStatus.OK);

		return ResponseEntity.ok(userService.findByUsername(principal.getName()));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logoutUser(Principal principal) {
		
		System.out.println(principal.getName());
		if (principal == null || principal.getName() == null)
			return new ResponseEntity<>(HttpStatus.OK);

		return ResponseEntity.ok("OK");
	}

	@GetMapping("/name{userid}")
	public ResponseEntity<?> getUserNameById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.findUsernameById(userId));
	}

}
