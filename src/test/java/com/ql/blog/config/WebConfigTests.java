package com.ql.blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class WebConfigTests {
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	public void passwordEncode() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String rawPassword = "abc123@@";
		String encodedPassword = encoder.encode(rawPassword);
		
		log.info("암화화 전 비밀번호 : {}", rawPassword);
		log.info("암호화 후 비밀번호 : {}", encodedPassword);
		
		log.info("비밀번호 비교 : {}", encoder.matches(rawPassword, encodedPassword));
		
		
	}
}
