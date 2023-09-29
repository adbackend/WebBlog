package com.ql.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.blog.domain.RoleType;
import com.ql.blog.domain.User;
import com.ql.blog.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입
	@Transactional
	public void insertUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
	}
	
	// 중복회원 검사
	@Transactional(readOnly = true)
	public User getUser(String username) {
		
		
		User findUser = userRepository.findByUsername(username).orElseGet(() -> {
			return new User(); // 검색 결과가 없을 떄 빈 User 객체 반환
		});
		
		log.info("찾은 회원 = {}", findUser);
		
		return findUser;
	}
}
