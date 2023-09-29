
package com.ql.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.blog.domain.User;
import com.ql.blog.dto.UserDTO;
import com.ql.blog.persistence.ResponseDTO;
import com.ql.blog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 로그인 페이지
	@GetMapping("/auth/login")
	public String login() {
		
		return "system/login";
	}
	
	// 회원가입
	@GetMapping("/auth/insertUser")
	public String inserUser() {
		
//		System.out.println(9/0);
		
		return "user/inserUser";
	}
	
	// 회원가입 처리
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
		
		User user = modelMapper.map(userDTO, User.class);
		User findUser = userService.getUser(user.getUsername()); // 중복회원 체크
		
		if(findUser.getUsername() == null) {
			userService.insertUser(user); // 회원가입
			
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공");
			
		}else {
			
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + " 님은 이미 회원입니다.");
		}
		
	} 
	
}
