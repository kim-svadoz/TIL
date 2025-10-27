<%@page import="image.ImageVO"%>
<%@page import="member.MemberVO"%>
<%@page import="board.list.BoardListVO"%>
<%@page import="java.util.List"%>
<%@page import="gathering.info.GatheringInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="/damoim/static/common/damoim.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<style type="text/css">
</style>
</head>
<body class="">
	<% GatheringInfoVO gathering = (GatheringInfoVO)request.getAttribute("gathering"); %>
	<% List<BoardListVO> boardlist = (List<BoardListVO>)request.getAttribute("boardlist"); %>
	<% String gath_no= request.getParameter("gath_no"); %>
	<% MemberVO user = (MemberVO)session.getAttribute("user"); %>
	<% boolean memchk = (boolean)request.getAttribute("memchk"); %>
	<% List<ImageVO> imglist= (List<ImageVO>)request.getAttribute("imglist"); %>
	<!-- 메인화면 시작 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"></div>
			<!-- 왼쪽여백 끝 중앙 컨텐츠 시작 -->
			<div class="col-xl-8">
				<hr style="width: 100%; border-color: #ff399b;" />
				<!-- 게시물 시작 -->
				<div class="table-responsive" style="padding-left: 5%; padding-right: 5%; background-color: white;">
					<table class="table" style="font-size: 1vmax">
						<thead>
							<tr>
								<th width="15%">카테고리</th>
								<th width="45%">제목</th>
								<th width="15%">작성자</th>
								<th width="15%">작성일</th>
								<th width="10%">조회수</th>
							</tr>
						</thead>
						<tbody>
							<%if(boardlist.size()!=0){
								int size = 6;
								if(boardlist.size()<6)
									size = boardlist.size();
									for (int i = 0; i <size; i++) {
										BoardListVO row = boardlist.get(i);
										String path = "";
										if(user!=null){
											if(memchk){
												path = "/damoim/gathering/article.do?gath_no="+gath_no+"&board_no="+row.getBoard_no()+"&pagenum=0";
											} else {
												path = "";
											}
										} else {
										path="/damoim/member/login.do";
									}
								%><tr>
									<td><%=row.getBoard_category()%></td>
									<td><a href="<%=path %>"
										style="color: black;"><%=row.getBoard_title()%><span style="color: red; font-weight:bold;">&nbsp;[<%=row.getComm_count() %>]</span></a></td>
									<td><%=row.getBoard_nickname()%></td>
									<td><%=row.getBoard_date()%></td>
									<td><%=row.getBoard_hit()%></td>
								</tr>
								<%
									}
							} else {
							%>	
								<tr>
									<td></td>
									<td>게시물이 없습니다.</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							<% } %>
						</tbody>
					</table>
				</div>
				<!-- 게시물 끝 -->
				<hr style="width: 100%; border-color: #ff399b;" />
				<br />
				<br />
				<%if(imglist.size()>=8){ %>
				<div class="row" style="margin-bottom: 3%;">
					<%for(int i=0; i<4; i++){ %>
					<div class="col-xl-3">
						<%if(user!=null){ %>
						<a href="/damoim/gathering/article.do?gath_no=<%=gath_no %>&board_no=<%=imglist.get(i).getImage_bno()%>&pagenum=0"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} else {%>
						<a href="/damoim/member/login.do"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} %>
					</div>
					<%} %>
				</div>
				<div class="row" style="margin-bottom: 3%;">
					<%for(int i=4; i<8; i++){ %>
					<div class="col-xl-3">
						<%if(user!=null){ %>
						<a href="/damoim/gathering/article.do?gath_no=<%=gath_no %>&board_no=<%=imglist.get(i).getImage_bno()%>&pagenum=0"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} else {%>
						<a href="/damoim/member/login.do"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} %>
					</div>
					<%} %>
				</div>
				<%} else if(imglist.size()<8&&imglist.size()>=4){%>
				<div class="row" style="margin-bottom: 3%;">
					<%for(int i=0; i<4; i++){ %>
					<div class="col-xl-3">
						<%if(user!=null){ %>
						<a href="/damoim/gathering/article.do?gath_no=<%=gath_no %>&board_no=<%=imglist.get(i).getImage_bno()%>&pagenum=0"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} else {%>
						<a href="/damoim/member/login.do"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} %>
					</div>
					<%} %>
				</div>
				<div class="row" style="margin-bottom: 3%;">
					<%for(int i=4; i<imglist.size(); i++){ %>
					<div class="col-xl-3">
						<%if(user!=null){ %>
						<a href="/damoim/gathering/article.do?gath_no=<%=gath_no %>&board_no=<%=imglist.get(i).getImage_bno()%>&pagenum=0"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} else {%>
						<a href="/damoim/member/login.do"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} %>
					</div>
					<%} %>
				</div>
				<%}else{%>
				<div class="row" style="margin-bottom: 3%;">
					<%for(int i=0; i<imglist.size(); i++){ %>
					<div class="col-xl-3">
						<%if(user!=null){ %>
						<a href="/damoim/gathering/article.do?gath_no=<%=gath_no %>&board_no=<%=imglist.get(i).getImage_bno()%>&pagenum=0"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} else {%>
						<a href="/damoim/member/login.do"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=imglist.get(i).getImage_name()%>"></a>
						<%} %>
					</div>
					<%} %>
				</div>
				<%}%>
			</div>
			<!-- 중앙컨텐츠 끝 오른쪽 여백시작 -->
			<div class="col-xl-2"></div>
		</div>
	</div>
	<!-- 메인화면 끝 -->
</body>
</html>