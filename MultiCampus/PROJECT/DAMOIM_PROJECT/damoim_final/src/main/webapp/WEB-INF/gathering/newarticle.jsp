<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote-bs4.min.js"></script>

<style type="text/css">
.container-fluid {
	padding-left: 0px;
	padding-right: 0px;
}
</style>


</head>
<body class="">
	<% MemberVO user = (MemberVO) session.getAttribute("user"); %>
	<% boolean memchk = (boolean)request.getAttribute("memchk"); %>
	<% String state= (String)request.getAttribute("state");%>
	<!-- 메인화면 시작 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"></div>
	<% if (user != null) {
		if(memchk){ %>
			<!-- 왼쪽여백 끝 중앙 컨텐츠 시작 -->
			<div class="col-xl-8">
				<% if(state.equals("0")){ %>
					<form action="/damoim/gathering/writearticle.do" method="post">
				<% } else {%>
					<form action="/damoim/gathering/updatearticle.do" method="post">
					<input type="hidden" name="board_no" value="<%=(String) request.getParameter("board_no")%>">
				<% } %>
					<select class="form-control w-50" name="board_category"
						id="board_category">
						<option value="자유게시판">자유게시판</option>
						<option value="공지사항">공지사항</option>
						<option value="질문과답변">질문과답변</option>
					</select> <input type="hidden" name="board_gno"
						value="<%=request.getParameter("gath_no")%>"> <input
						type="hidden" name="board_mno" value="<%=user.getMem_id()%>">
					<br /> 
						<input class="form-control" name="board_title" placeholder="제목을 입력해주세요."> 
					<br />
					<div class="form-group">
						<textarea id="summernote" name="board_content"></textarea>
					</div>
					<script>
						$('#summernote').summernote({
							placeholder : 'Hello Bootstrap 4',
							disableDragAndDrop: true,
							tabsize : 2,
							height : 500,
							focus : true,
							
							});
					</script>
					<br />
					<div class="row">
						<div class="col-xl-10">
							
						</div>
						<div class="col-xl-2">
							<% if(state.equals("0")){ %>
								<button type="submit" class="btn btn-primary w-100">작성완료</button>
							<% } else {%>
								<button type="submit" class="btn btn-primary w-100">수정완료</button>
							<% } %>
						</div>
					</div>
				</form>
				
			</div>

	<% } else { %>
		<div class="container-fluid">
			<div class="jumbotron" align="center">
				<h1>모임가입이 필요합니다.</h1>
				<a class="btn btn-primary " href="/damoim/gathering/board.do?gath_no=<%= request.getParameter("gath_no") %>&pagenum=0&board_category=all">돌아가기</a>
			</div>
		</div>
		<%} %>
	<%} else { %>
	<div class="container-fluid">
		<div class="jumbotron" align="center">
			<h1>로그인이 필요합니다.</h1>
			<a class="btn btn-primary " href="/damoim/member/login.do">로그인</a>
			<a class="btn btn-primary " href="/damoim/gathering/board.do?gath_no=<%= request.getParameter("gath_no") %>&pagenum=0&board_category=all">돌아가기</a>
		</div>
	</div>
	<% } %>
	<!-- 중앙컨텐츠 끝 오른쪽 여백시작 -->
		<div class="col-xl-2"></div>
	</div>
</div>
	<!-- 메인화면 끝 -->
</body>
</html>