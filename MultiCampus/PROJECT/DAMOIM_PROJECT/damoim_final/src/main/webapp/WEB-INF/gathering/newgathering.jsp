<%@page import="member.MemberVO"%>
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
	<%
		MemberVO user = (MemberVO) session.getAttribute("user");
		//String userid= (String)request.getAttribute("userid");
	%>
	<%
		if (user != null) {
	%>
	<div class="container"
		style="background-color: lightgray; padding: 5% 0%; border-radius: 2rem;">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<h2 class="">모임생성</h2>
			</div>
			<br /> <br /> <br /> <br />
		</div>
		<div class="row">
			<div class="col-md-4" align="right">
				<h4>타이틀이미지1</h4>
			</div>
			<div class="col-md-6">
				<form id="FILE_FORM" method="post" enctype="multipart/form-data"
					action="">
					<input type="file" name="file" id="file"
						class="border-primary rounded"> <a
						class="btn border-primary rounded" href="javascript:uploadFile();">전송</a>
				</form>
				<script type="text/javascript">
					function uploadFile() {
						var form = $('#FILE_FORM')[0];
						var formData = new FormData(form);
						var url = alert("함수");
						$
								.ajax({
									url : "/damoim/imageupload.do?type=member&value=${userid}",
									type : "POST",
									processData : false,
									contentType : false,
									data : formData,
									success : function(result) {
										alert("업로드성공");
									}
								});
					}
				</script>
			</div>
			<div class="col-md-2"></div>
		</div>
		<hr class="w-75" style="border-color: #FF399B;" />
		<div class="row">
			<div class="col-md-4" align="right">
				<h4>타이틀이미지2</h4>
			</div>
			<div class="col-md-6">
				<form id="FILE_FORM" method="post" enctype="multipart/form-data"
					action="">
					<input type="file" name="file" id="file"
						class="border-primary rounded"> <a
						class="btn border-primary rounded" href="javascript:uploadFile();">전송</a>
				</form>
				<script type="text/javascript">
					function uploadFile() {
						var form = $('#FILE_FORM')[0];
						var formData = new FormData(form);
						var url = alert("함수");
						$
								.ajax({
									url : "/damoim/imageupload.do?type=member&value=${userid}",
									type : "POST",
									processData : false,
									contentType : false,
									data : formData,
									success : function(result) {
										alert("업로드성공");
									}
								});
					}
				</script>
			</div>
			<div class="col-md-2"></div>
		</div>
		<hr class="w-75" style="border-color: #FF399B;" />
		<div class="row">
			<div class="col-md-4" align="right">
				<h4>타이틀이미지3</h4>
			</div>
			<div class="col-md-6">
				<form id="FILE_FORM" method="post" enctype="multipart/form-data"
					action="">
					<input type="file" name="file" id="file"
						class="border-primary rounded"> <a
						class="btn border-primary rounded" href="javascript:uploadFile();">전송</a>
				</form>
				<script type="text/javascript">
					function uploadFile() {
						var form = $('#FILE_FORM')[0];
						var formData = new FormData(form);
						var url = alert("함수");
						$
								.ajax({
									url : "/damoim/imageupload.do?type=member&value=${userid}",
									type : "POST",
									processData : false,
									contentType : false,
									data : formData,
									success : function(result) {
										alert("업로드성공");
									}
								});
					}
				</script>
			</div>
			<div class="col-md-2"></div>
		</div>
		<hr class="w-75" style="border-color: #FF399B;" />
		<form action="/damoim/gathering/makegathering.do" method="POST">
			<div class="row">
				<div class="col-md-4" align="right">
					<h4 class="card-title">모임이름</h4>
				</div>
				<div class="col-md-4">
					<input type="text" name="gath_name" class="form-control">
				</div>
				<div class="col-md-4"></div>
			</div>
			<hr class="w-75" style="border-color: #FF399B;" />
			<div class="row">
				<div class="col-md-4" align="right">
					<h4>모임소개</h4>
				</div>
				<div class="col-md-4">
					<input type="text" name="gath_info" class="form-control">
				</div>
				<div class="col-md-4"></div>
			</div>
			<hr class="w-75" style="border-color: #FF399B;" />
			<div class="row">
				<div class="col-md-4" align="right">
					<h4>모임주제</h4>
				</div>
				<div class="col-md-4">
					<select class="form-control" name=gath_major>
						<option value="I1">공연/연극</option>
						<option value="I2">영화/전시회</option>
						<option value="I3">등산/캠핑/백패킹</option>
						<option value="I4">여행/맛집/낚시</option>
						<option value="I5">봉사/재능기부</option>
						<option value="I6">연애/결혼/육아</option>
						<option value="I7">애완동물</option>
						<option value="I8">직업-구직</option>
						<option value="I9">직업-자기개발</option>
						<option value="I10">영어/외국어</option>
						<option value="I11">자전거</option>
						<option value="I12">다이어트/헬스/요가</option>
						<option value="I13">당구/포켓볼</option>
						<option value="I14">수영/스쿠버다이빙</option>
						<option value="I15">수영/스쿠버다이빙</option>
						<option value="I16">축구/풋살/족구</option>
						<option value="I17">농구/야구</option>
						<option value="I18">보드게임</option>
						<option value="I19">온라인게임</option>
						<option value="I20">노래/악기</option>
						<option value="I22">사진</option>
						<option value="I23">요리</option>
						<option value="I24">책/독서</option>
					</select>
				</div>
				<div class="col-md-4"></div>
			</div>
			<hr class="w-75" style="border-color: #FF399B;" />
			<div class="row">
				<div class="col-md-4" align="right">
					<h4>채팅방링크</h4>
				</div>
				<div class="col-md-4">
					<input type="text" name="gath_chat" class="form-control">
					<div class="col-md-4"></div>
				</div>
			</div>
			<hr class="w-75" style="border-color: #FF399B;" />
			<div class="row">
				<div class="col-md-4" align="right">
					<h4>지역</h4>
				</div>
				<div class="col-md-4">
					<input type="text" name="gath_loc" class="form-control">
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-11" align="right">
					<button type="submit">수정완료</button>
				</div>
				<div class="col-md-1"></div>
			</div>
		</form>
	</div>
	<%
		} else {
	%>
	<div class="container-fluid">
		<div class="jumbotron" align="center">
			<h1>로그인이 필요합니다.</h1>
			<a class="btn btn-primary " href="/damoim/member/login.do">로그인</a> <a
				class="btn btn-primary "
				href="/damoim/gathering/board.do?gath_no=<%=request.getParameter("gath_no")%>&pagenum=0&board_category=all">돌아가기</a>
		</div>
	</div>
	<%
		}
	%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>