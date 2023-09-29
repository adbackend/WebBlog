package com.ql.blog.controller;

import javax.servlet.http.HttpSession;

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

//@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	// 로그인 페이지
	@GetMapping("/auth/login")
	public String login() {
		return "system/login";
	}
	
	// 로그인 요청
	@PostMapping("/auth/login")
	public @ResponseBody ResponseDTO<?> login(@RequestBody User user, HttpSession session){
		
		User findUser = userService.getUser(user.getUsername());
		
		// 검색 결과 유무와 사용자가 입력한 비밀번호가 유효한지 검사
		if(findUser.getUsername() == null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 회원 입니다.");
		}else {
			if(user.getPassword().equals(findUser.getPassword())) { // 비밀번호 일치
				session.setAttribute("principal", findUser); 
				return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getUsername() + "님 로그인 되셨습니다.");
			}else {
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호를 잘못 입력하셨습니다.");
			}
		}
		
	}
	
	// 로그아웃 처리
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}

}




