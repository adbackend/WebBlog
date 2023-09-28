package com.ql.blog.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ql.blog.domain.User;

@SpringBootTest
public class UserDAOTests {
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	void getUserListTest() {
		
		User user = new User();
		user.setUsername("test");
		user.setPassword("1234");
		user.setEmail("test@gmail.com");
		
		int before = userDAO.getUserList().size();
		
		userDAO.insertUser(user);
		
		int after = userDAO.getUserList().size();
		
		assertEquals(before+1, after);
		
	}

}
