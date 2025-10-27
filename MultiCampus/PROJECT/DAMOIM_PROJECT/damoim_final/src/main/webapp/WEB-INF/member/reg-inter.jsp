<%@page import="interest.interest_majorVO"%>
<%@page import="java.util.List"%>
<%@page import="member.MemberVO"%>
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
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>
<script type="text/javascript">

$(document).ready(function() {
flag = false;
checklist = document.getElementsByClassName("mem_mjno");
length = checklist.length;
$("#next").on("mouseover", function() {
	flag = false;
	for(i=0; i<length; ++i){
		if(checklist[i].checked){
			flag = true;
		}
	}
	if(flag == false){
		alert("종류는 하나 이상 체크해주세요.");
	}
});
});

</script>


<body>
	<!-- user, majorlist -->
	<%
		MemberVO user = (MemberVO) request.getAttribute("reg");
		List<interest_majorVO> majorlist = (List<interest_majorVO>) request.getAttribute("majorlist");
		/* boolean[] majorCheck = new boolean[majorlist.size()];
		int lastIndex = 0;*/
	%>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12" style="">
					<ul class="nav nav-pills" style="">
						<li class="nav-item w-25"><a class="nav-link disabled"
							href="#"><b>1. 본인인증</b></a></li>
						<li class="nav-item w-25" style=""><a href="#"
							class="active nav-link"><b>2. 정보입력</b></a></li>
						<li class="nav-item w-25"><a href="#"
							class="nav-link disabled"><b>3. 정보확인</b></a></li>
						<li class="nav-item-25"><a href="#" class="nav-link disabled"><b>4.
									가입완료</b></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h3 class="">
						관심사를 체크해주세요. (중복체크 가능)&nbsp;<br> <br>모임추천을 받을 수 있습니다.
					</h3>
				</div>
			</div>
		</div>
	</div>
	<form method="post" action="/damoim/member/reg5.do">
		<%
			int size = majorlist.size();
			int start = 0;
			for (int i = start; i < size; ++i) {
		%>
		<div class="my-2">
			<div class="container">
				<div class="row">
					<%
						for (int j = 0; j < 3; ++j) {
								if(start == size){
									break;
								}
								interest_majorVO major = majorlist.get(start);
								++start;
					%>
					<div class="col-md-4">
						<label><input type="checkbox" name="mem_mjno" class="mem_mjno"
							value=<%=major.getMajor_mjno()%>> <%=major.getMajor_name()%></label>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
		<%
			}
		%>

	<div class="">
		<div class="container">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="form-group">
						<label></label><input id="next" type="submit" value="다음 단계로">
					</div>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</div>
	</form>
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
		crossorigin="anonymous"></script>
</body>

</html>