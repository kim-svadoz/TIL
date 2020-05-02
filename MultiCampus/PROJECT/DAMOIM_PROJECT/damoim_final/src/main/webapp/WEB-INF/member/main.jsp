<%@page import="gathering.info.GatheringInfoVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>

<body>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<style type="text/css">
/* @media (min-width: 1200px) */

.container {
	max-width: 100%;
}

.container {
	width: 100%;
	padding-right: 0;
	padding-left: 0;
	margin-right: 0;
	margin-left: 0;
}

h1 {
	text-align: center;
}

h5 {
	color: green;
}

</style>
</head>

<body>
	<%
		ArrayList<GatheringInfoVO> toplist = (ArrayList<GatheringInfoVO>) request.getAttribute("toplist");
		System.out.println(toplist.size());
		ArrayList<GatheringInfoVO> mylist = (ArrayList<GatheringInfoVO>) request.getAttribute("mylist");
		ArrayList<GatheringInfoVO> recommendlist = (ArrayList<GatheringInfoVO>) request.getAttribute("recommendlist");
	%>
	<div class="pb-0">
		<div class="container">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<img class="img-fluid d-block" src="../static/images/main.PNG">
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</div>
	<div class="py-5" style="">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h4 class="">인기모임</h4>
					<div class="row">
						<div class="col-md-12 pb-5">
							<ul class="list-group list-group-flush">
								<%
									for (int i = 0; i < toplist.size(); ++i) {
										GatheringInfoVO row = toplist.get(i);
								%>
								<li class="list-group-item"><i
									class="fa fa-bookmark text-primary mr-2"></i> <a
									href="/damoim/gathering/home.do?gath_no=<%=row.getGath_no()%>">
										<%=row.getGath_major()%>/<%=row.getGath_name()%>/<%=row.getGath_opendate()%></a></li>
								<%
									}
								%>

							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="col-md-12">
						<h4 class="">개설된 모임</h4>
						<div class="row">
							<div class="col-md-12">
								<ul class="list-group list-group-flush">
									<%
										for (int i = 0; i < mylist.size(); ++i) {
											GatheringInfoVO row = mylist.get(i);
									%>
									<li class="list-group-item"><i
										class="fa fa-cloud text-primary mr-2"></i> <a
										href="/damoim/gathering/home.do?gath_no=<%=row.getGath_no()%>">
											<%=row.getGath_major()%>/<%=row.getGath_name()%>/<%=row.getGath_opendate()%></a></li>
									<%
										}
									%>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="py-5" style="">
		<div class="container">
			<div class="row">
			</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-12 pb-5">
						<h4 class="">카테고리별 모임</h4>
						<div class="row">
							<div class="col-md-12">
								<ul class="list-group list-group-flush">
									<%
										for (int i = 0; i < recommendlist.size(); ++i) {
											GatheringInfoVO row = recommendlist.get(i);
									%>
									<li class="list-group-item"><i
										class="fa fa-life-ring text-primary mr-2"></i> <a
										href="/damoim/gathering/home.do?gath_no=<%=row.getGath_no()%>">
											<%=row.getGath_major()%>/<%=row.getGath_name()%>/<%=row.getGath_opendate()%></a></li>
									<%
										}
									%>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="col-md-12">
						<h4 class="">공지사항</h4>
						<div class="row">
							<div class="col-md-12">
								<ul class="list-group list-group-flush">
									<li class="list-group-item" style="color:black;"><i
										class="fa fa-cloud text-primary mr-2"></i>[점검] 1월 9일 카페 점검 안내</li>
									<li class="list-group-item" style="color:black;"><i
										class="fa fa-bookmark text-primary mr-2"></i>[안내] 12월의 업데이트 소식</li>
									<li class="list-group-item" style="color:black;"><i
										class="fa fa-bell text-primary mr-2"></i></li>
									<li class="list-group-item" style="color:black;"><i
										class="fa fa-life-ring text-primary mr-2"></i></li>
									<li class="list-group-item" style="color:black;"><i
										class="fa fa-paper-plane text-primary mr-2"></i></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous" style=""></script>

</body>

</html>