package com.ql.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.blog.domain.Post;
import com.ql.blog.domain.User;
import com.ql.blog.persistence.ResponseDTO;
import com.ql.blog.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// 포스트 목록
	@GetMapping({"", "/"})
	public String getPostList(Model model,
							  @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		model.addAttribute("postList", postService.getPostList(pageable));
		
		return "index";
	}
	
	// 포스트 등록 화면
	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "/post/insertPost";
	}
	
	// 포스트 등록 처리
	@PostMapping("/post")
	public @ResponseBody ResponseDTO<?> insertPost(@RequestBody Post post, HttpSession session){
		
		// Post 객체를 영속화하기 전 연관된 User 엔티티 설정
		User principal = (User)session.getAttribute("principal");
		
		post.setUser(principal);
		post.setCnt(0);
		
		postService.insertPost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 동록했습니다.");
		
	}
	
	// 포스트 상세
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		
		model.addAttribute("post", postService.getPost(id));
		
		return "post/getPost";
	}
	
	// 포스트 수정 화면
	@GetMapping("/post/updatePost/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		
		model.addAttribute("post", postService.getPost(id));
		
		return "post/updatePost";
	}

}








