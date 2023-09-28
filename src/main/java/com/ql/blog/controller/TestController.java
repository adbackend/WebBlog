package com.ql.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.blog.domain.RoleType;
import com.ql.blog.domain.User;
import com.ql.blog.exception.BlogException;
import com.ql.blog.persistence.UserRepository;

//@Controller
public class TestController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/html")
	public String html() {
		
		return "redirect:hello.html";
	}
	
	@GetMapping("/image")
	public String image() {
		return "redirect:image/blank.jpg";
	}
	
	
	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
	
		//검색된 회원이 없을경우 예외반환
		User findUser = userRepository.findById(id).orElseThrow(() -> {
			return new BlogException(id + " 번 회원이 존재하지 않습니다.");
		});
	
		return findUser;
	}
	
	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList(){
		return userRepository.findAll();
	}
	
	// Page, Pageable, PageRequest API
	@GetMapping("/user/page/{page}")
	public @ResponseBody Page<User> getUserListPage(@PathVariable int page){
		
		// page에 해당하는 2개의 데이터 조회
		// id와 username 내림차순 정렬
		
		Pageable pageable = PageRequest.of(page, 2, Sort.Direction.DESC, "id", "username");
		
		return userRepository.findAll(pageable);
	}
	
	// @PageableDefault를 이용하면 PageRequest를 사용하는 것보다 쉽게 Pageable 객체를 생성할 수 있다
	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPage2(@PageableDefault(page = 0, size = 2, direction = Direction.DESC, sort = {"id", "username"}) Pageable pageable){
		
		// 첫번째 페이지(0)에 해당하는 2개의 데이터 조회
		// id 내림차순
		// 만약 page와 size를 동적으로 변경하고 싶다면 -> localhost:8080/user/page?page=0&size=5
		
		return userRepository.findAll(pageable);
	}
	
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		
		user.setRole(RoleType.ADMIN);
		userRepository.save(user);
		
		return user.getUsername() + " 회원가입 성공";
	}
	
	@Transactional
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		
		User findUser = userRepository.findById(user.getId()).orElseThrow(()->{
			return new BlogException(user.getId() + " 번 회원이 존재하지 않습니다.");
		});
		
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		
		userRepository.save(findUser);
		
		return "회원수정 성공";
		
	}
	
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		
		userRepository.deleteById(id);
		
		return "회원 삭제 성공";
	}

}
