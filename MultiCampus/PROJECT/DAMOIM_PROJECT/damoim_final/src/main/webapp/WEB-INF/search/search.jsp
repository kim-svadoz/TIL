<%@page import="damoim.search.SearchVO"%>
<%@page import="interest.interest_majorVO"%>
<%@page import="java.util.List"%>
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
<body>
	<%
		List<SearchVO> searchList = (List<SearchVO>) request.getAttribute("searchList");
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="py-5" style="background-color:rgb(243,243,243); border-radius:1.5rem;">
					<div class="container">
								<%
									if (searchList.size() != 0) {
										int listsize = 0;
										if (searchList.size() >= 3)
											listsize = 3;
										else
											listsize = searchList.size();

										for (int i = 0; i < listsize; i++) {
											SearchVO con = searchList.get(i);
								%>

						<div class="row">
							<div class="p-4 col-lg-8">
								<h4 class="mb-3" style="color: #FF399B;"><%=con.getGath_name()%></h4>
								<div class="blockquote text-muted">
									<p class="mb-0" style="color:black;"><%=con.getGath_info()%></p>
								</div>
								<a class="btn btn-info"
									href="/damoim/gathering/home.do?gath_no=<%=con.getGath_no()%>">참가하기
								</a>
							</div>
							<div class="col-md-4 align-self-center">
							</div>
						</div>
								
								<%
									}
									}
								%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>