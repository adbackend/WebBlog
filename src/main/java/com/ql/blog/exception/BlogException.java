package com.ql.blog.exception;


public class BlogException extends RuntimeException{
	
	private static final Long serialVersionUID = 1L;
	
	public BlogException(String message) {
		super(message);
	}

}
