package com.ql.blog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.blog.domain.Post;
import com.ql.blog.domain.User;
import com.ql.blog.dto.PostDTO;
import com.ql.blog.persistence.ResponseDTO;
import com.ql.blog.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	public @ResponseBody ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, HttpSession session){
		
		Post post = modelMapper.map(postDTO, Post.class);
		
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
	
	// 포스트 수정 처리
	@PutMapping("/post")
	public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post){
		
		postService.updatePost(post);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), post.getId() + "번 포스트를 수정했습니다.");
		
	}
	
	// 포스트 삭제
	@DeleteMapping("/post/{id}")
	public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id){
		
		postService.deletePost(id);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), id + "번 포스트를 삭제했습니다.");
		
	}

}









