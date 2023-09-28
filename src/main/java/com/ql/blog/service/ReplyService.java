package com.ql.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ql.blog.domain.Post;
import com.ql.blog.domain.Reply;
import com.ql.blog.domain.User;
import com.ql.blog.persistence.PostRepository;
import com.ql.blog.persistence.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// 댓글 작성
	@Transactional
	public void insertReply(int postId, Reply reply, User user) {
		
		Post post = postRepository.findById(postId).get();
		
		reply.setUser(user);
		reply.setPost(post);
		
		replyRepository.save(reply);
	}
	
	// 댓글 삭제
	@Transactional
	public void deleteReply(int replyId) {
		
		replyRepository.deleteById(replyId);
		
	}

}
