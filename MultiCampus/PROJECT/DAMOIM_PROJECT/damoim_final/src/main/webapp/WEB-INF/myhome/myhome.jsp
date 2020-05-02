<%@page import="member.MemberVO"%>
<%@page import="damoim.myhome.mylist.MyListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">


</head>
<body>
	<% MemberVO user = (MemberVO)session.getAttribute("user"); %>
	<% List<MyListVO> mylist = (List<MyListVO>)request.getAttribute("mylist"); %>
	<% List<MyListVO> myschedulelist = (List<MyListVO>)request.getAttribute("myschedulelist"); %>
	<% List<MyListVO> recommendlist = (List<MyListVO>)request.getAttribute("recommendlist"); %>
	<!-- 내모임 목록 -->
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="py-5">
					<div class="container">
						<div class="row">
							<div class="text-center mx-auto">
								<h2>내 모임 새글</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-10 p-6v text-center mx-auto">
								<div class="card bg-light text-center">
									<div class="card-body p-4">
									<h5>새글</h5>
									<hr>
									<% if(mylist.size() != 0) {
											int listsize=0;
											if(mylist.size()>=3)
												listsize=3;
											else
												listsize=mylist.size();
													for(int i=0; i<listsize;i++){
											MyListVO con= mylist.get(i);
											%>
										<ul class="list-unstyled my-3">
											<li><a class="btn" 	href="/damoim/gathering/home.do?gath_no=<%=con.getGath_no()%>"><%=con.getGath_name() %></a>
											<br><%=con.getBoard_title() %>  &nbsp;&nbsp;|&nbsp;&nbsp;  <%=con.getBoard_date() %></li>
										</ul>
										<%}
										}%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<!-- 내모임 일정 -->
			<div class="col-md-6">
				<div class="py-5">
					<div class="container">
						<div class="row">
							<div class="text-center mx-auto">
								<h2>내 모임 일정</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-10 p-6 text-center mx-auto">
								<div class="card bg-light text-center">
									<div class="card-body p-4">
									<h5>일정</h5>
									<hr>
										<% if(myschedulelist.size() != 0) {
											int listsize=0;
											if(myschedulelist.size()>=3)
												listsize=3;
											else
												listsize=myschedulelist.size();
													for(int i=0; i<listsize;i++){
											MyListVO con = myschedulelist.get(i); 
										%>

										<ul class="list-unstyled my-3">
											<li class="mb-1"><a class="btn"
												href="/damoim/gathering/sch_main.do?gath_no=<%=con.getGath_no()%>"><%=con.getGath_name()%></a>
												<br> <%=con.getSche_name() %>   &nbsp;&nbsp;|&nbsp;&nbsp;   <%=con.getSche_date() %>
											</li>
										</ul>
										<%}
								}%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  -->
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="text-center mx-auto">
					<h2>맞춤 추천</h2>
				</div>
			</div>
			<div class="row">
			<% if(recommendlist.size() != 0) {
				int listsize=0;
				if(recommendlist.size()>=3)
					listsize=3;
				else
					listsize=recommendlist.size();
						for(int i=0; i<listsize;i++){
					MyListVO con = recommendlist.get(i); 
			%>
				<div class="col-lg-4 p-3">
					<div class="card bg-light">
						<div class="card-body p-4">
							<div class="row">
								<div class="col-8">
									<h3 class="mb-0">
									<a class="btn"
										href="/damoim/gathering/home.do?gath_no=<%=con.getGath_no()%>">
										<%=con.getGath_name()%></a></h3>
								</div>
							</div>
							<hr>
							<ul class="pl-3">
								<li><%=con.getGath_info()%></li>
							</ul>
							<a class="btn btn-primary mt-3" href="/damoim/gathering/home.do?gath_no=<%=con.getGath_no()%>">참여하기</a>
						</div>
					</div>
				</div>
				<% }
				}%>
			</div>
		<div class="col-lg-4 p-3"></div>
		</div>
	</div>
</body>
</html>