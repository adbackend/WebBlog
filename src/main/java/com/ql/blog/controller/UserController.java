
package com.ql.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.blog.domain.User;
import com.ql.blog.persistence.ResponseDTO;
import com.ql.blog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 회원가입
	@GetMapping("/auth/insertUser")
	public String inserUser() {
		
//		System.out.println(9/0);
		
		return "user/inserUser";
	}
	
	// 회원가입 처리
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@RequestBody User user){
		System.out.println(user);
		System.out.println(user.getUsername());
		
		User findUser = userService.getUser(user.getUsername()); // 중복회원 체크
		
		if(findUser.getUsername() == null) {
			userService.insertUser(user); // 회원가입
			
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공");
			
		}else {
			
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + " 님은 이미 회원입니다.");
		}
		
	} 
	
}
