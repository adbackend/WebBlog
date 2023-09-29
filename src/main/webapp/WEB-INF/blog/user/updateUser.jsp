<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form action="/action_page.php">
		<input type="hidden" id="id" value="${principal.user.id}"/>
		<div class="mb-3">
			<label for="uname">Username:</label> 
			<input class="form-control" type="text" id="username" name="username" value="${principal.user.name}">
		</div>
		<div class="mb-3">
			<label for="pwd">Password:</label> 
			<input class="form-control" type="password" id="password" placeholder="Enter password" name="password">
		</div>
		<div class="mb-3 mt-3">
			<label for="email">Email:</label>
			<input class="form-control" type="email" id="email" name="email" value="value="${principal.user.password}"">
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">회원 정보 수정</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
