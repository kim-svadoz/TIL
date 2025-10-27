<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<link rel="stylesheet" href="/damoim/static/common/damoim.css">
</head>

<body>
	<%
		// session(or request)은 컨테이너가 기본으로 만들어주는 변수
		MemberVO user = (MemberVO) session.getAttribute("user");
	%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h5 class="">
					<a href="/damoim/member/guide">다모임가이드</a>
				</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12" align="center">
				<h1 class="display-2">
					<a href="/damoim/main/home.do"><img alt="" src="../static/images/damoimlogo.png">다모임</a>
				</h1>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6"></div>
			<%
				// 처음 접속한 사용자에게 보여줄 컨텐츠
				if (user == null) {
			%>
			<!-- <div class="col-md-2" align="right">
				<img align="right" alt="" src="../static/images/saramicon.jpeg" width=50px; height=30px;>
			</div> -->
			<div class="col-md-4" align="right">
				<img alt="" src="../static/images/saramicon.jpeg" width=50px; height=30px;>
				<a href="/damoim/member/login.do">로그인</a>
			</div>
			<div class="col-md-2">
				<a href="/damoim/member/reg.do">회원가입</a>
			</div>

			<%
				// 로그인 성공한 사용자에게 보여줄 컨텐츠
				} else {
			%>
			<!-- logout과 프로필사진은 로그인이 된 후에만 처리되어야 한다.  -->
			<div class="col-md-2"></div>
			<div class="col-md-2" align="right">
				<a href="/damoim/member/mypage"><img class="rounded-circle" src="/damoim/member/memimg/<%=user.getMem_profile()%>" width="40px" height="40px"></a>
			</div>
			
			<div class="col-md-2">
				<a href="/damoim/member/logout.do">로그아웃</a>
			</div>
			<% } %>
		</div>
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-9">
			<ul class="nav nav-pills">
			<li class="nav-item"><a href="/damoim/main/home.do"
				class="active nav-link">홈</a></li>
		<%-- 	<%  // 처음 접속한 사용자에게 보여줄 컨텐츠
						if(user == null){ %>
			<li class="nav-item"><a class="nav-link" href="#">별점순</a></li>
			<%	// 로그인 성공한 사용자에게 보여줄 컨텐츠
						}else{ %>
			<li class="nav-item"><a class="nav-link"
				href="/damoim/myhome/myhome.do">내모임</a></li>
			<%} %> --%>

			<li></li>
			<li class="nav-item">
			<%if(user!=null){ %>
			<a href="/damoim/myhome/myhome.do" class="active nav-link">내모임</a>
			<%}else{ %>
			<a href="#" class="active nav-link">내모임</a>
			<% } %>
			</li>
			<li></li>
			<%if(user!=null){ %>
			<li class="nav-item"><a href="/damoim/member/mypage" class="active nav-link">마이페이지</a>
			<%}else{ %>
			<a href="#" class="active nav-link">마이페이지</a>
			<% } %>
			</li>
		</ul>
			</div>
			<div class="col-md-2">
			<form class="form-inline" action="/damoim/search/searchchk.do" method="post">
				<div class="input-group">
					<button class="btn btn-primary" type="submit">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</form>
			
			</div>
		</div>
	</div>