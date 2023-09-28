<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form action="/action_page.php">
		<div class="mb-3">
			<label for="uname">Username:</label> 
			<input class="form-control" type="text" id="username" placeholder="Enter username" name="username">
		</div>
		<div class="mb-3">
			<label for="pwd">Password:</label> 
			<input class="form-control" type="password" id="password" placeholder="Enter password" name="password">
		</div>
		<div class="mb-3 mt-3">
			<label for="email">Email:</label>
			<input class="form-control" type="email" id="email" placeholder="Email email" name="email">
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
