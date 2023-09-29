<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form>
		<div class="mb-3">
			<label for="username">
				<spring:message code="user.login.form.username"/>:
			</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
		</div>
		<div class="mb-3">
			<label for="password">
				<spring:message code="user.login.form.password"/>:
			</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
		</div>
	</form>
	<button id="btn-login" class="btn btn-secondary">
		<spring:message code="user.login.form.username"/>
	</button>
</div>

<script src="/js/login.js"></script>
<%@ include file="../layout/footer.jsp" %>