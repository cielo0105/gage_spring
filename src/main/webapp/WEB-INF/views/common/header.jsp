<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<c:set value="${pageContext.servletContext.contextPath }" var="root"></c:set>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<title>구해줘 홈즈</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${root }/css/main.css" type="text/css" />
</head>

<body>
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-body p-5">
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
					<h4 class="fw-bold text-center mb-5">로그인</h4>
					<label for="id" class="form-label">아이디</label> <input type="text"
						class="form-control mb-3" id="userid" placeholder="Enter ID" /> <label
						for="pass" class="form-label">비밀번호</label> <input type="text"
						class="form-control mb-5" id="userpwd" placeholder="Password" />

					<button type="button" class="btn btn-primary w-100"
						data-bs-dismiss="modal" id="btn-login">로그인</button>

					<div class="mt-3 text-center">
						<a href="./findpw1.html"
							class="text-secondary text-decoration-none">비밀번호 찾기</a>
					</div>

					<hr class="mb-5" />
					<button type="button" class="btn btn-light w-100">
						<img width="30" height="30"
							src="https://img.icons8.com/color/48/google-logo.png"
							alt="google-logo" /> Google로 로그인
					</button>
				</div>
			</div>
		</div>
	</div>
	<header class="navbar text-white bg-dark navbar-expand-lg"
		data-bs-theme="dark">
		<div class="container">
			<a class="navbar-brand title" href="/">구해줘 홈즈</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse p-2" id="navbarNav">
				<!-- ㅣ기본정보 -->
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link"
						href="${root }/about.jsp">소개</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root }/board?pgno=1&key=&word=">공지사항</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root }/analysis.jsp">분석</a></li>
				</ul>
				<c:if test="${empty user }">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item">
							<button type="button" class="btn btn-secondary"
								data-bs-toggle="modal" data-bs-target="#exampleModal">로그인</button>
						</li>
						<li class="nav-item">
							<a class="btn ms-1 soft enabled btn-secondary"
								href="/regist">회원가입</a>
						</li>
					</ul>
				</c:if>
				<c:if test="${!empty user }">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item">
							<button class="btn btn-secondary" id="btn-logout">로그아웃</button>
						</li>
						<li class="nav-item">
							<button class="btn ms-1 btn-secondary"
								onclick="location.href='${root }/member/info.jsp'">마이페이지</button>
						</li>
					</ul>
				</c:if>
			</div>
		</div>
		
	<%@ include file="../js/main.jsp" %>
	</header>