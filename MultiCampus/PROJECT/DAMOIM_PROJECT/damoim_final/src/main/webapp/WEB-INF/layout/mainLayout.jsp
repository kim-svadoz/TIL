<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="/erp/common/css/main.css" /> -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<tiles:insertAttribute name="top"></tiles:insertAttribute>
	<br/><br/>
	<div class="row">
		<div class="col-xl-2"></div> <!-- 왼쪽 여백 -->
		<div class="col-xl-8">
				<div class="row">
					<div style="width: 100%; height: auto;">
						<tiles:insertAttribute name="content"/>
					</div>
				</div>
		</div>
		<div class="col-xl-2"></div> <!-- 오른쪽 여백 -->
	</div>
	<br/><br/>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>
