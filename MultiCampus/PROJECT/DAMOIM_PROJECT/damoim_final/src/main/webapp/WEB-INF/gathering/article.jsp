<%@page import="image.ImageVO"%>
<%@page import="member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.comment.BoardCommentVO"%>
<%@page import="java.util.List"%>
<%@page import="board.list.BoardListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">+

</style>
</head>
<body class="">
	<% BoardListVO board = (BoardListVO)request.getAttribute("board"); %>
	<% String gath_no = (String)request.getAttribute("gath_no");%>
	<% ArrayList<BoardCommentVO> commentlist = (ArrayList<BoardCommentVO>)request.getAttribute("comment"); %>
	<% int listsize = commentlist.size();%>
	<% int commentcount = (int)request.getAttribute("commentcount") ; %>
	<% int pagenum = Integer.parseInt(request.getParameter("pagenum")); %>
	<% String board_no = (String)request.getAttribute("board_no");%>
	<% MemberVO user = (MemberVO)session.getAttribute("user"); %>
	<% boolean memchk = (boolean)request.getAttribute("memchk"); %>
	<% List<ImageVO> img = (List<ImageVO>)request.getAttribute("img"); %>
	<!-- 메인화면 시작 -->
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"></div>
			<!-- 왼쪽여백 끝 중앙 컨텐츠 시작 -->
			<div class="col-xl-8">
				<div class="row">
				<%if(user!=null){ 
					if(user.getMem_id().equals(board.getBoard_mno())){ %>
					<div class="col-xl-2">
						<a class="btn btn-primary btn-block" href="/damoim/gathering/newarticle.do?gath_no=<%=gath_no%>&board_no=<%=board_no%>&board_category=<%=board.getBoard_category()%>&state=1">수정</a>
					</div>
					<div class="col-xl-2">
						<a class="btn btn-primary btn-block" href="/damoim/gathering/articledelete.do?gath_no=<%=gath_no%>&board_no=<%=board_no%>">삭제</a>
					</div>
					<div class="col-xl-2"></div>
					
				<% } else{ %>
					<div class="col-xl-6"></div>
					<%}} else {%>
					<div class="col-xl-6"></div>
					<%} %>
					<div class="col-xl-4"></div>
					<div class="col-xl-2" align="right">
						<a class="btn btn-primary" href="/damoim/gathering/board.do?gath_no=<%=gath_no%>&pagenum=0&board_category=all">목록</a>
					</div>
					
				</div>
				<%if(user!=null){ 
					if(user.getMem_id().equals(board.getBoard_mno())){ %>
				<div class="row p-2">
					<form id="FILE_FORM" method="post" enctype="multipart/form-data" action="">
					사진업로드: <input type="file" name="file" id="file" class="border rounded">
							<a class="btn border rounded" href="javascript:uploadFile();">전송</a>
					 </form>
					 <script type="text/javascript">
						function uploadFile(){
							var form = $('#FILE_FORM')[0];
							var formData = new FormData(form);
							$.ajax({
								url:"/damoim/imageupload.do?type=board&gath_no=${gath_no}&value=${board_no}",
								type:"POST",
								processData:false,
								contentType:false,	
								data: formData,
								success:function(result){
								}
							});
						}
					</script>
				</div>
				<%}
					}%>
				<br />
				<div class="container-fluid" style="border: solid 1px black; padding: 3% 3%;">
					<div class="row">
						<div class="col-xl-8">
							<p class="lead" style="font-size: 1.6vmax; font-weight: bolder;"><%=board.getBoard_title() %></p>
						</div>
						<div class="col-xl-4" >
							<p style="text-align :right;">
								<kbd style="background-color: gray; font-weight: bold;">전체공개</kbd> <span class="" style=" font-size: 1vmax;">&nbsp;&nbsp;&nbsp;<%=board.getBoard_date()%></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-4">
							<div class="table-responsive" style="padding-left: 3%;">
								<table>
									<tbody>
										<tr>
											<td style="width: 15%;"><i class="fa fa-user-circle fa-2x" aria-hidden="true"></i></td>
											<td style="width: 50%; font-size: 1.1vmax; font-weight: bolder;">&nbsp;<%=board.getBoard_nickname()%> (<%=board.getBoard_name().substring(0,2)%>**)</td>
											<td style="width: 35%; font-size: 1.1vmax; color: gray; text-align: left;">&nbsp; 일반멤버</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-xl-3"></div>
						<div class="col-xl-5" align="right">
							<p style="font-size: 0.8vmax;">
								Http://damoim.naver.com/webclient/164121 &nbsp; &nbsp;
								<kbd type="button" style="background-color: white; border: none 1px; color: black; font-weight: bold; font-size: 0.9vmax;">주소복사</kbd>
							</p>
						</div>
					</div>
					<br />
					<form style="font: red;"></form>
					<div class="row">
						<div class="col-xl-12">
							<%=board.getBoard_content() %>
						</div>
						<br/><br/>
						<% for(int i=0; i<img.size(); i++){ %>
							<a class="w-25 p-2" href="/damoim/gathering/boardimages/<%=img.get(i).getImage_name() %>"><img class="img-thumbnail" src="/damoim/gathering/boardimages/<%=img.get(i).getImage_name()%>"></a>
						<%} %>
					</div>
					<div class="row">
						<div class="col-xl-3" align="left">
							<div class="table-responsive">
								<table style="width: inherit;">
									<tbody align="center" style="font-size: 0.9vmax;">
										<tr>
											<td style="width: 20%;"><span>댓글 </span><span style="color: red;"><%=listsize %></span></td>
											<td style="width: 30%; border-left: solid 1px gray;">조회수 <%= board.getBoard_hit() %> </td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-xl-9"></div>
					</div>
				</div>
				<br />
				<br />
				<div class="container-fluid" style="padding: 3% 3%; background-color: rgb(244, 244, 244);">
				<%
					if(listsize > 10){
						int forsize = 10+pagenum*10;
						if(forsize>listsize){
							forsize=listsize;
							}
						for (int i = 0 + pagenum * 10; i < forsize; i++) {
							BoardCommentVO row = commentlist.get(i);
							
				%>
					<div class="row">
						<div class="col-xl-9">
							<table style="width: inherit;">
								<tr>
									<td rowspan="2" style="width: 50px; padding: 1% 2% 1% 1%;"><i class="fa fa-user-circle fa-2x" aria-hidden="true"></i></td>
									<td><span style="font-weight: bolder; padding: 1% 3%; font-size: 1vmax;"><%=row.getB_comm_nickname() %></span> <span style="color: gray; font-size: 0.8vmax;"><%=row.getB_comm_date() %></span></td>
								</tr>
								<tr>
									<td><span style="padding: 1% 3%; font-size: 1vmax;"><%=row.getB_comm_content() %></span></td>
								</tr>
							</table>
						</div>
						<div class="col-xl-3">
							<table style="width: inherit; font-size: 0.9vmax; text-align: right;">
								<tr>
									<td style="padding: 1%;"></td>
								</tr>
								<tr>
								<% if(row.getB_comm_mno().equals(user.getMem_id())){ %>
									<td>
									 <a class="btn btn-primary" href="/damoim/gathering/article/commentdelete.do?gath_no=<%=gath_no%>&board_no=<%=board_no%>&b_comm_no=<%=row.getB_comm_no()%>" style="font-size: 0.9vmax; padding: 0.1rem 0.3rem;">삭제</a>
									</td>
								<%}else{ %>
									<td></td>
									<%} %>
								</tr>
							</table>
						</div>
					</div>
					<hr />
					<% } 
					} else {
						for (int i = 0 + pagenum * 10; i < listsize; i++) {
							BoardCommentVO row = commentlist.get(i);
					%><div class="row">
						<div class="col-xl-9">
							<table style="width: inherit;">
								<tr>
									<td rowspan="2" style="width: 50px; padding: 1% 2% 1% 1%;"><i class="fa fa-user-circle fa-2x" aria-hidden="true"></i></td>
									<td><span style="font-weight: bolder; padding: 1% 3%; font-size: 1vmax;"><%=row.getB_comm_nickname() %></span> <span style="color: gray; font-size: 0.8vmax;"><%=row.getB_comm_date() %></span></td>
								</tr>
								<tr>
									<td><span style="padding: 1% 3%; font-size: 1vmax;"><%=row.getB_comm_content() %></span></td>
								</tr>
							</table>
						</div>
						<div class="col-xl-3">
							<table style="width: inherit; font-size: 0.9vmax; text-align: right;">
								<tr>
									<td style="padding: 1%;"></td>
								</tr>
								<tr>
								<% if(row.getB_comm_mno().equals(user.getMem_id())){ %>
									<td>
									 <a class="btn btn-primary" href="/damoim/gathering/article/commentdelete.do?gath_no=<%=gath_no%>&board_no=<%=board_no%>&b_comm_no=<%=row.getB_comm_no()%>" style="font-size: 0.9vmax; padding: 0.1rem 0.3rem;">삭제</a>
									</td>
								<%}else{ %>
									<td></td>
									<%} %>
								</tr>
							</table>
						</div>
					</div>
					<hr />
					<%}} %>
					<div class="row">
					<% if(user!=null){ %>
						<div class="mx-auto p-4 col-xl-12">
							<form method="post" action="/damoim/gathering/article/comment.do">
								<input type="hidden" name="b_comm_mno" value="<%=user.getMem_id()%>">
								<input type="hidden" name="b_comm_bno" value="<%=board.getBoard_no()%>">
								<input type="hidden" name="gath_no" value="<%=gath_no%>">
								<input type="hidden" name="board_no" value="<%=board_no%>">
		
								<div class="form-group form-row">
									<div class="col-xl-11">
										<textarea class="form-control" id="form30" name="b_comm_content" rows="3" placeholder="Your message"></textarea>
									</div>
									<button type="submit" class="btn btn-primary btn-xl">등록</button>
								</div>
							</form>
						</div>
						<%} else{ %>
							<div class="mx-auto p-4 col-xl-12">
								<div class="form-group form-row">
									<div class="col-xl-11">
										<textarea class="form-control" id="form30" name="b_comm_content" rows="3" placeholder="댓글을 작성하려면 로그인하세요." readonly></textarea>
									</div>
									<button type="button" class="btn btn-primary btn-xl disabled">등록</button>
								</div>
							</div>
						<%} %>
						<div class="col-xl-12">
						<% if(listsize>=50){%>
					<% if(pagenum<=2){ %>
						<ul class="pagination justify-content-center">
							<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
							<li class="page-item <%if(pagenum==0){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=0%>">1</a>
							</li>
							<li class="page-item <%if(pagenum==1){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=1%>">2</a>
							</li>
							<li class="page-item <%if(pagenum==2){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=2%>">3</a>
							</li>
							<li class="page-item"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=3%>">4</a>
							</li>
							<li class="page-item"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=4%>">5</a>
							</li>
							<li class="page-item"><a class="page-link" 
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=5%>">Next</a>
							</li>
						</ul>
					<%} else if(pagenum>=(Math.ceil(listsize)/10.0)-3){ %>
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" 
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum-3%>">Previous</a></li>
							<li class="page-item <%if(pagenum==(int)Math.ceil(listsize/10.0)-1){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=(Math.ceil(listsize)/10.0)-5%>"><%=(Math.ceil(listsize)/10.0)-4%></a>
							</li>
							<li class="page-item <%if(pagenum==(int)Math.ceil(listsize/10.0)-1){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=(Math.ceil(listsize)/10.0)-4%>"><%=(Math.ceil(listsize)/10.0)-3%></a>
							</li>
							<li class="page-item <%if(pagenum==(int)Math.ceil(listsize/10.0)-1){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=(Math.ceil(listsize)/10.0)-3%>"><%=(Math.ceil(listsize)/10.0)-2%></a>
							</li>
							<li class="page-item <%if(pagenum==(int)Math.ceil(listsize/10.0)-1){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=(Math.ceil(listsize)/10.0)-2%>"><%=(Math.ceil(listsize)/10.0)-1%></a>
							</li>
							<li class="page-item <%if(pagenum==(int)Math.ceil(listsize/10.0)-1){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=(Math.ceil(listsize)/10.0)-1%>"><%=(Math.ceil(listsize)/10.0)%></a>
							</li>
							<li class="page-item disabled"><a class="page-link" 
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=5%>">Next</a>
							</li>
						</ul>
					<%} else{ %>
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" 
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum-3%>">Previous</a></li>
							<li class="page-item"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum-2%>"><%=pagenum-1%></a>
							</li>
							<li class="page-item"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum-1%>"><%=pagenum%></a>
							</li>
							<li class="page-item active"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum%>"><%=pagenum+1%></a>
							</li>
							<li class="page-item"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum+1%>"><%=pagenum+2%></a>
							</li>
							<li class="page-item"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum+2%>"><%=pagenum+3%></a>
							</li>
							<li class="page-item"><a class="page-link" 
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=pagenum+3%>">Next</a>
							</li>
						</ul>
					<%}}else{ %>
						<ul class="pagination justify-content-center">
						<%for(int i=0; i<listsize/10+1; i++){ %>
							<li class="page-item <%if(pagenum==i){%><%="active"%><%} %>"><a class="page-link"
								href="/damoim/gathering/article.do?gath_no=G001&board_no=<%=board.getBoard_no()%>&pagenum=<%=i%>"><%=i+1%></a>
							</li>
					<%}%></ul><%} %>

					</div>
					</div>
					
					
				</div>
			</div>
			<!-- 중앙컨텐츠 끝 오른쪽 여백시작 -->
			
			<div class="col-xl-2"></div>
		</div>
	</div>
	<!-- 메인화면 끝 -->
</body>
</html>