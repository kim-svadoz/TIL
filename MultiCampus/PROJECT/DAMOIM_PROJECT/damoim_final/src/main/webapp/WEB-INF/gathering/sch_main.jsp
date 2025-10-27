<%@page import="gathering.info.GatheringInfoVO"%>
<%@page import="member.MemberVO"%>
<%@page import="gathering.join.ScheduleJoinVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@page import="gathering.schedule.ScheduleRegisterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/cfae7cf239.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="/damoim/static/common/damoim.css">
<style>
@font-face {
	font-family: 'NanumGothic';
	src: url('fonts/NanumGothic.eot');
	src: url('fonts/NanumGothic.eot?#iefix') format(‘embedded-opentype’),('fonts/NanumGothic.woff')
		format(‘woff’), url('fonts/NanumGothic.ttf') format('truetype'),
		url('fonts/NanumGothic.svg') format('svg');
	src: local(※), url(NanumGothic.woff) format(‘woff’);
}

body {
	font-family: '나눔고딕', 'NanumGothic';
}

.card {
	width: 85%;
	margin: 0 auto;
	
}

.table-header {
	margin-left: 30px;
	margin-top: 5px;
	font-size: 58px;
	font-weight: bolder;
	/* color: #FF75A7; */
	/* color: #FF399B; */
}

.table-header_sub {
	width: 80%;
	margin: 0 auto;
	font-size: 36px;
	font-weight: 400px;
	color: #7C7C7C;
}

.date-header {
	width: 80%;
	margin: 0 auto;
	font-size: 34px;
	font-weight: 550;
	line-height: 26px;
	letter-spacing: 0;
	margin-bottom: 18px;
	text-transform: uppercase;
}

.date-header_sub {
	width: 80%;
	margin: 0 auto; 
	font-size: 26px;
	font-weight: 360;
	padding-left: 30px;
}

.place-header {
	width: 80%;
	margin: 0 auto;
	font-size: 32px;
	font-weight: 600;
}

.place-header_sub {
	width: 80%;
	margin: 0 auto;
	font-size: 27px;
	font-weight: 400;
	padding-left: 30px;
}

.rank {
	width: 80%;
	margin: 0 auto;
}

.rank-header {
	height: 235px;
	/* background-color: #ADADAD; */
	color: black;
	text-align: center;
	/* border-radius: 5px; */
	border-width: 1px;
	/* border-style: outset; */
	background-image: url("/damoim/gathering/images/rank7.jpg");
	background-size:cover;
	background-position: center;
	border:5px solid transparent;
	margin-bottom: 10px;
}

.rank-main-header {
	margin-top:6px;
	font-size: 38px;
	font-weight: 700;
}
.rank-header-main-sub{
	font-size: 32px;
	font-weight: 500; 
}

.rank-icon {
	padding-top: 9px;
}

.rank-main {
	/* background-color: #D3D3D3; */
	color: black;
	text-align: left;
	/* border-radius: 6px; */
	border-width: 2px;
	/* border-style: outset; */
	background-image: url("/damoim/gathering/images/rank7.jpg");
	background-size:cover;
	background-position: center;
	border:5px solid transparent;
}

.rank-footer {
	/* height: 30px;
	text-align: center;
	background-color: #F8F8F8; */
}

.btn-icon {
	background-color: #17A2B8;
	margin-bottom: 20px;
}

.rank-bottom {
	border-radius: 6px;
	width: 90%;
	/* background-color: #F8F8F8; */
	margin: 0 auto;
	box-shadow: 2px 2px 2px 2px #999;
	background-image: url("/damoim/gathering/images/rank4.jpeg");
}

.rank-main-title {
	margin-right: 90px;
	/* font-size: 22px;
	font-weight: 500; */
	text-align: right;
}
.rank-main-title-head{
	margin-right: 77px;
	/* font-size: 22px;
	font-weight: 500; */
	text-align: right;
}



.list {
	font-weight: 500;
	list-style: none;
}

.card {
	margin-top: 20px;
	background-size: 385px auto !important;
	background-position: right -204px bottom -9px;
	padding: 30px 15px;
	margin: 15px 0;
	margin-left: 15px;
	visibility: visible;
	animation-name: slideInUp;
	border-radius: 10px;
	border-style: outset;
	border-width: 1.5px;
	/* background-color: lightgray; */
	background-color: #EFEFEF;
}

.card-wrap {
	width: 88%;
	margin: 0 auto;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	flex-direction: column;
	border-radius: 5px;
	border-style: outset;
	border-width: 2px;
	box-shadow: 3px 3px 3px 3px #999;
	background-color: #F8F8F8;
	background-image: url("/damoim/gathering/images/card.jpeg");
}

.card-container {
	margin-top: 20px;
	width: 100%;
	max-width: 1340px;
	margin: 0 auto;
	box-sizing: border-box;
	max-width: 1340px;
	
}

.btn-join {
	cursor:pointer;
	display: block;
	box-sizing: border-box;
	text-align: center;
	height: 100px;
	font-size: 28px;
	font-weight: 330;
	width: 120%;
	height: 120px;
	border-radius: 8px;
	margin-right:18px;
	background-color:white;
	color:#333;
	/* transition:all 0.9s, color 0.15; */
	transition: color .10s, background-color .15s, border-color .15s,
		box-shadow .15s;
}
.btn-join:hover{color:white;}
.hover1:hover{
	box-shadow: 200px 0 0 0 rgba(0,0,0,0.27) inset;
}
.fas {
	width: 40px;
	heigth: 40px;
}
.fas fa-map-marker-alt{
	color:#F6871B;
}

#create_btn {
	text-align: center;
	width: 70%;
	height: 40px;
	border-radius: 5px 5px 5px 5px;
	cursor: default;
	background-color: #F8F8F8;
	border: solid 1.2px;
	margin-bottom: 25px;
	margin-left:70px;
	font-size: 26px;
	box-shadow: 2px 2px 2px 2px #999;
	cursor: pointer;
}

#create_btn_cal {
	text-align: center;
	width: 90%;
	height: 40px;
	border-radius: 5px 5px 5px 5px;
	cursor: default;
	background-color: #F8F8F8;
	border: solid 1.2px;
	margin-bottom: 25px;
	margin-left:70px;
	font-size: 26px;
	box-shadow: 2px 2px 2px 2px #999;
	cursor: pointer;
}

a:link {
	text-decoration: none; color:#black;
}

a:visited {
	text-decoration: none; color:#black;
}

a:hover {
	text-decoration: none; color:#black;
}
a:active{
	text-decoration: none; color:#black;
}

.menubar_top {
	border-radius: 6px 6px 0 0;
	width: 100%;
	height: 17px;
	background-color: #232227;
	float: right;
}
.col-md-6 {
	float: left;
}

#mypro {
	text-align: left;
	width: 500px;
	margin-left: 0px;
	margin-top: 15px;
}
#mypro_name{
	font-size: 32px;
	font-weight: 700;
}
#mypro_msg{
	font-size: 27px;
	font-weight: 500;
}

.rank-list {
	width: 50%;
	/*display:inline;*/
}

ul.myList li {
	/* float: left; */
	list-style: none;
}

.btn-secondary {
	color: #fff;
	/* background-color: #FF399B; */
	/* border-color: #FF399B; */
	border-color:#FF75A7;
	width: 200px;
	height: 41px;
	float: right;
	box-shadow: 0 0.125rem 0.625rem rgba(122, 90, 125, 600), 0 0.0625rem
		0.125rem rgba(58, 196, 125, 0.5);
	padding: .5rem 2rem;
	line-height: 1.5;
	text-align: center;
	vertical-align: middle;
	user-select: none;
	border: 1px solid transparent;
}

.btn {
	display: inline-block;
	font-weight: 400;
	text-align: center;
	vertical-align: middle;
	user-select: none;
	border: 1px solid transparent;
	padding: .375rem .75rem;
	font-size: 1.25rem;
	line-height: 1;
	border-radius: .3rem;
	transition: color .25s, background-color .35s, border-color .25s,
		box-shadow .35s;
	margin-right: 65px;
	margin-bottom: 5px;
}
#rank_count{
	margin-top:10px;
	text-align: center;
	margin-left:55px;
	font-size:40px;
	font-weight: 800;
	color:black;
}

.human1{
	width:14%;
}
.human2{
	width:85%;
	margin-bottom: 10px;
	padding-bottom: 5px;
}
</style>
</head>
<body class="" onload="startTime()">
	<%
		List<ScheduleRegisterVO> moimlist = (List<ScheduleRegisterVO>) request.getAttribute("moim");
	%>
	<!-- 모임 리스트 -->
	<%
		List<MemberVO> ranklist = (List<MemberVO>) request.getAttribute("list");
	%>
	<!-- 멤버 랭킹 리스트 -->

	<%
		GatheringInfoVO gathering = (GatheringInfoVO) request.getAttribute("gathering");
	%>
	<!-- 모임정보 받아오는 -->
	<%
		MemberVO member = (MemberVO) request.getAttribute("member");
	%>
	<!-- 멤버 정보(mem_id 받아오는) -->

	<%
		MemberVO user = (MemberVO) session.getAttribute("user");
	%>
	<!-- 세션 받아오는 -->
	<%
		if (user != null)
			request.setAttribute("sche_mem_mno", user.getMem_id());
	%>
	
	<!-- 메인화면 시작 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"></div>
			<!-- 왼쪽여백 끝 중앙 컨텐츠 시작 -->

			<div class="col-xl-8">
				<h1 class="table-header" style="color: #FF75A7;">
					THIS <span class="table-header_sub">EVENT</span>
				</h1>
				<div class="row">
					<div class="col-xl-7">
						<a
							href="/damoim/gathering/sch_calendar.do?gath_no=<%=gathering.getGath_no()%>"
							onclick="window.open(this.href, '_blank','width=1270px,height=800px,toolbars=no,scrollbars=no, left=300, top=80'); return false;"><button
								id="create_btn_cal" type="submit">클릭해서 일정을 확인하세요.</button></a>
					</div>
					<div class="col-xl-5">

						<%
							//로그인 성공한 사용자에게 보여줄 컨텐츠
							if (user != null) {
						%>
						<a
							href="/damoim/gathering/sch_create.do?gath_no=<%=gathering.getGath_no()%>"
							onclick="window.open(this.href, '_blank', 'width=1350px,height=870px,toolbars=no,scrollbars=no, left=270, top=50'); return false;"><button
								id="create_btn" type="submit">모임생성</button></a>
						<%
							//로그인 안한 사용자에게 보여줄 컨텐츠
							} else {
						%>

						<%
							}
						%>

					</div>
				</div>

				<br />

					<h1 class="table-header" style="color: #FF75A7;">
						UPCOMING <span class="table-header_sub">EVENT</span>
					</h1>
				<div>
					<div style="padding-bottom: 9px; margin-bottom: 6px;">
					<%if(user!=null){ %>
						<a
							href="/damoim/gathering/sch_moimAll.do?gath_no=<%=gathering.getGath_no()%>"
							onclick="window.open(this.href, '_blank', 'width=1850px,height=930px,toolbars=no,scrollbars=no, left=27'); return false;">
							<button type="submit" class="btn btn-secondary" style="background-color:#FF75A7;">전체모임보기</button>
						</a>
					<%} else {%>
						<a href="/damoim/member/login.do">
							<button type="submit" class="btn btn-secondary" style="background-color:#FF75A7;">전체모임보기</button>
						</a>
					<% } %>
					</div>
				</div>

				<br />
				
				<%
					if (moimlist.size() != 0) {
						for (int i = 0; i < 2; i++) {
				%>
				<div class="card-container">
					<form action="/damoim/gathering/sch_join.do" method="post">

						<%
							if (i > moimlist.size() - 1) {
										break;
									}
									ScheduleRegisterVO row = moimlist.get(i);
						%>
						<input type="hidden" name="gath_no"
							value="<%=gathering.getGath_no()%>" /> <input type="hidden"
							name="sche_no" value="<%=row.getSche_no()%>">
						<div class="card-wrap">
							<div class="card">
								<div class="event"
									style="visibility: visible; animation-name: slideInUp;">
									<h2 class="date-header">
										<%=row.getSche_date()%>
										<span class="date-header_sub"> <%=row.getSche_time()%></span>
									</h2>
									<div class="place-header">
										<div>
											<a
												href="/damoim/gathering/kakaomap.do?sche_loc=<%=row.getSche_loc()%>"
												target="_blank"
												onclick="window.open(this.href, '_blank', 'width=1150px,height=700px,toolbars=no,scrollbars=no, left=370, top=80'); return false;"
												style="color:#FF75A7;">
												<i class="fas fa-map-marker-alt"></i> <%=row.getSche_loc()%>
											</a><span class="place-header_sub"><i class="fas fa-coins"></i><%=row.getSche_fee()%>원</span>
										</div>

										<br /> <span style="font-size: 20px; font-weight: 300;">
											<%=row.getSche_context()%></span>
										<div class="row">
											<div class="col-xl-9"></div>
											<div class="col-xl-3">
											<%if(user!=null){ %>
												<a href="/damoim/gathering/moim.do?gath_no=<%=gathering.getGath_no() %>&sche_no=<%=row.getSche_no()%>"><button type="submit"
														class="btn-join hover1">참석하기</button></a>
											<%}else{ %>
												<a href="/damoim/member/login.do"><button type="button"
															class="btn-join hover1">참석하기</button></a>
											<%} %>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<br />
					</form>
				</div>
				<%
					}
					} else {
				%>
				<br> <br> <br> <br> <br> <br>
				<%
					}
				%>
				<h1 class="table-header" style="color: #FF75A7;">
					우수 <span class="table-header_sub">참석왕</span><i class="fas fa-crown"></i>
				</h1>
				<div class="container">

					<%
						if (ranklist.size() != 0) {
					%>
					<form action="/gathering/sch_rank.do" method="post">
						<div class="rank-bottom">
							<div class="rank">
								<div class="rank-header">
									<%
										MemberVO rowlist = ranklist.get(0);
									%>
									<div class="rank-icon">
										<div class="icon">
											<img src="/damoim/member/memimg/<%=rowlist.getMem_profile()%>" class="human1">
										</div>
									</div>

									<div class="rank-header-main">
										<p class="rank-main-header"><%=rowlist.getMem_name()%></p>
										<p class="rank-header-main-sub"><%=rowlist.getMem_msg()%></p>
									</div>
								</div>

								<div class="rank-main">
									<!-- <h6 class="rank-main-title" style="color: red;">활동 우수자</h6> -->
									<p class="rank-main-title-head">참석 횟수</p>
									<p class="rank-main-title"><i class="far fa-hand-point-down fa-3x" ></i></p>
									<ul class="myList">
										<%
											if (ranklist.size() != 0) {
													for (int i = 1; i < 6; i++) {
														if (i > ranklist.size() - 1) {
															break;
														}
														rowlist = ranklist.get(i);
										%>
										<li>
											<div class="row">
												<div class="col-md-3">
													<img src="/damoim/member/memimg/<%=rowlist.getMem_profile()%>" class="human2">
												</div>
												<div class="col-md-6" id="mypro">
													<p id="mypro_name"><%=rowlist.getMem_name()%></p>
													<p id="mypro_msg"><%=rowlist.getMem_msg()%></p>
												</div>
												<div class="col-md-x3">
													<div id="rank_count"><%=rowlist.getMem_count() %></div>
												</div>
											</div>
										</li>
										<br />
										<%
											}
												}
										%>
										<br />
									</ul>
								</div>
								<!-- <div class="rank-footer"></div> -->
							</div>
						</div>
					</form>
					<%
						} else {
					%>

					</br> </br> </br> </br>
					<%
						}
					%>
				</div>
			</div>
			<!-- 중앙컨텐츠 끝 오른쪽 여백시작 -->
			<div class="col-xl-2"></div>
		</div>
	</div>
	<!-- 메인화면 끝 -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
<!-- 	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script> -->
</body>

</html>