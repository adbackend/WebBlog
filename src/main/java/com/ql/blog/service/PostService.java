package com.ql.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.blog.domain.Post;
import com.ql.blog.persistence.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	// 포스트 등록
	@Transactional
	public void insertPost(Post post) {
		
		post.setCnt(0);
		postRepository.save(post);
		
	}
	
	// 포스트 목록
	@Transactional(readOnly = true)
	public Page<Post> getPostList(Pageable pageable){
		
		return postRepository.findAll(pageable);
	}
	
	// 포스트 상세
	@Transactional(readOnly = true)
	public Post getPost(int id) {
		return postRepository.findById(id).get();
	}
	
	// 포스트 수정
	@Transactional
	public void updatePost(Post post) {
		
		Post findPost = postRepository.findById(post.getId()).get();
		
		findPost.setTitle(post.getTitle());
		findPost.setContent(post.getContent());
	}
	
	// 포스트 삭제
	@Transactional
	public void deletePost(int id) {
		
		postRepository.deleteById(id);
	}
}
