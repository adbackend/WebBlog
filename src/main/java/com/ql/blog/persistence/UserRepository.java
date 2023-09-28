package com.ql.blog.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ql.blog.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// 중복회원 검사
	// SELECT * FROM user WHERE username = ?1;
	Optional<User> findByUsername(String username);

}
