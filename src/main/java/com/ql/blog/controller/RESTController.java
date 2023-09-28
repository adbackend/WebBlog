package com.ql.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ql.blog.domain.User;

@RestController
public class RESTController {
	
	// GET : SELECT
	@GetMapping("/blog")
	public User httpGet() {
		
		User findUser = User.builder()
				.id(1)
				.username("teset")
				.password("1234")
				.email("test@naver.com")
				.build();
		
		return findUser;
	}
	
	// POST : INSERT
	@PostMapping("/blog")
	public String httpPost(@RequestBody User user) {
		
		System.out.println(user);
		
		return "POST요청 처리 " + user;
	}
	
	// PUT : UPDATE
	@PutMapping("/blog")
	public String httpPut(User user) {
		return "PUT요청 처리 " + user;
	}
	
	// DELETE : DELETE
	@DeleteMapping("/blog")
	public String httpDelete(@RequestParam int id) {
		return "DELETE요청 처리 " + id;
	}

}
