package com.ql.blog.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ql.blog.domain.User;

public class AuthenticateInterceptor implements HandlerInterceptor{
	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		// 세션에 회원이 존재하는 확인
//		HttpSession session = request.getSession();
//		
//		User principal = (User)session.getAttribute("principal");
//		
//		if(principal == null) {
//			response.sendRedirect("/auth/login");
//		}
//		
//		return true;
//	}

}
