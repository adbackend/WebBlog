package com.ql.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ql.blog.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
