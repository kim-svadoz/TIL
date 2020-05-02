<%@page import="damoim.search.SearchVO"%>
<%@page import="interest.interest_majorVO"%>
<%@page import="java.util.List"%>
<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" 	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" 	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
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
		List<interest_majorVO> majorlist = (List<interest_majorVO>) request.getAttribute("majorlist");
		List<SearchVO> searchList = (List<SearchVO>)request.getAttribute("mem_mjno");
		/* boolean[] majorCheck = new boolean[majorlist.size()];
		int lastIndex = 0;*/
	%>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h3 class="">
						관심사로 검색하세요. (중복체크 가능)&nbsp;<br> 
					</h3>
				</div>
			</div>
		</div>
	</div>
	<form method="post" action="/damoim/search/search.do">
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
						<label><input type="checkbox" name="mem_mjno" class="mem_mjno" value=<%=major.getMajor_mjno()%>> <%=major.getMajor_name()%></label>
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
						<label></label><input id="next" type="submit" value="검색하기">
					</div>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>