<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 로그인 인증에 성공한 브라우저에만 접근할 수 있는 영역 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="principal" property="principal"/>
</sec:authorize>

<link href="/webjars/bootstrap/5.2.3/css/bootstrap.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
<script src="/webjars/jquery/3.5.1/dist/jquery.min.js"></script>

<!-- include summernote css/js -->
<link href="/webjars/summernote/0.8.10/summernote.css" rel="stylesheet">
<script src="/webjars/summernote/0.8.10/summernote.js"></script>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">Main</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="mynavbar">
			<c:if test="${principal == null}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/insertUser">회원가입</a></li>
				</ul>
			</c:if>
			<c:if test="${principal != null}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/user/updateUser">회원 상세</a></li>
					<li class="nav-item"><a class="nav-link" href="/post/insertPost">포스트 등록</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/logout">로그아웃</a></li>
				</ul>
			</c:if>

				<form class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Search">
					<button class="btn btn-primary" type="button">Search</button>
				</form>
		</div>
	</div>
</nav>