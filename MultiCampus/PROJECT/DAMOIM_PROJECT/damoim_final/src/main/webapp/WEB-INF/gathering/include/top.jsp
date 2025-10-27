<%@page import="java.util.List"%>
<%@page import="image.ImageVO"%>
<%@page import="member.MemberVO"%>
<%@page import="gathering.info.GatheringInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="/damoim/static/common/damoim.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>
<body class="">
	<%
	String gath_no = (String) request.getParameter("gath_no");
	GatheringInfoVO vo = (GatheringInfoVO) request.getAttribute("gathering");
	MemberVO user = (MemberVO) session.getAttribute("user");
	boolean memchk = (boolean) request.getAttribute("memchk");
	List<ImageVO> imgtitle = (List<ImageVO>)request.getAttribute("imgtitle");
	%>
	<!-- 상단 head 시작 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-12">
				<h5 class="">
					<a href="/damoim/main/home"><img id=minilogo
						src="/damoim/images/damoimlogo.png"
						style="width: 80px; height: 50px;">다모임</a>
				</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12">
				<h1 class="display-2" style="text-align: center;">
					<img alt="" style="width: 12%;"
						src="/damoim/gathering/images/<%=gath_no%>_title.png"><%=vo.getGath_name()%>
				</h1>
				<br />
			</div>
		</div>
	</div>
	<ul class="nav nav-pills" style="padding: 10px 50px;">
		<li class="nav-item" style="text-align: center; padding: 0px 15px;"><a
			href="/damoim/gathering/home.do?gath_no=<%=gath_no%>"
			class="nav-link active">홈</a></li>
		<li class="nav-item" style="text-align: center; padding: 0px 15px;"><a
			href="/damoim/gathering/board.do?gath_no=<%=gath_no%>&pagenum=<%=0%>&board_category=all"
			class="nav-link">게시판</a></li>
		<li class="nav-item" style="text-align: center; padding: 0px 15px;"><a
			href="/damoim/gathering/sch_main.do?gath_no=<%=gath_no%>"
			class="nav-link">일정</a></li>
	</ul>
	<%if(!memchk){ %>
	<div class="row">
		<div class="col-xl-12" align="right">
			<%if(user!=null){ %>
			<a class="btn btn-primary btn-block" href="/damoim/gathering/join.do?gath_no=<%=gath_no%>">가입하기</a>
			<%} else{ %>
			<a class="btn btn-primary btn-block" href="/damoim/member/login.do">가입하기</a>
			<% } %>
		</div>
	</div>
	<%} %>
	<br />
	<%if(imgtitle.size()!=0){ %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"></div>
			<div class="col-xl-8" style="padding: 0">
				<div id="carouselExampleIndicators"
					class="carousel slide carousel-fade" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" style="height: 300px;">
						<div class="carousel-item active">
							<img class="d-block img-fluid w-100"
								src="/damoim/gathering/images/<%=imgtitle.get(0).getImage_name()%>">
							<div class="carousel-caption">
								<h5 class="m-0"></h5>
								<p></p>
							</div>
						</div>
						<div class="carousel-item ">
							<img class="d-block img-fluid w-100"
								src="/damoim/gathering/images/<%=imgtitle.get(1).getImage_name()%>">
							<div class="carousel-caption">
								<h5 class="m-0"></h5>
								<p></p>
							</div>
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid w-100"
								src="/damoim/gathering/images/<%=imgtitle.get(2).getImage_name()%>">
							<div class="carousel-caption">
								<h5 class="m-0"></h5>
								<p></p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xl-2"></div>
		</div>
	</div>
	<%} else{ %>
		<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"></div>
			<div class="col-xl-8" style="padding: 0">
				<div class="boarder rounded" style="height: 300px; background-color: gray;">
					<p class="p-4" style="color: white;">이미지를 넣어주세요</p>
				</div>
			</div>
			<div class="col-xl-2"></div>
		</div>
	</div>
	<% }%>
	<!-- 상단 head 끝 -->
</body>
</html>