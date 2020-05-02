<%@page import="gathering.info.GatheringInfoVO"%>
<%@page import="member.MemberVO"%>
<%@page import="gathering.schedule.ScheduleRegisterVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/cfae7cf239.js"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
<style type="text/css">
table {
	text-align: center;
	margin: 0 auto;
	table-layout: fixed;
	word-break: break-all;
}

.card-wrap {
	/* width: 98%;*/
	height: 300px;
	margin: 0 auto;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	flex-direction: column;
	border-radius: 5px;
	border-style: outset;
	border-width: 2px;
	box-shadow: 3px 3px 3px 3px #999;
	background: repeating-linear-gradient(-45deg, #444, #444 10px, #888 0, #888 20px);
}

a:linked {
	text-decoration: none;
}

a:checked {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}

.card {
	/* width: 100%; */
	height: 100%;
	position: relative;
	-webkit-box-direction: normal;
	text-align: left;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #F8F8F8;
	background-clip: border-box;
	border: 1px solid rgba(0, 0, 0, .125);
	border-radius: .25rem;
}

.card-header_name {
	/* width: 100%; */
	/* margin: 0 auto; */
	/* margin-top: 10px; */
	font-size: 21px;
	text-align: center;
	font-weight: 550;
	/* padding-left: 40px; */
	/* padding-top: 2px; */
	color: #FF399B;
}

.date-header_main {
	/* width: 100%; */
	margin: 0 auto;
	font-size: 17px;
	font-weight: 500;
	/* padding-left: 30px;
	padding-top: 10px; */
}

.date-header_sub {
	width: 100%;
	margin: 0 auto;
	font-size: 17px;
	font-weight: 300;
	/* padding-left: 30px;
	padding-top: 10px; */
}

.place-header {
	width: 80%;
	/* margin-left: 30px;
	margin-top: 20px; */
	font-size: 21px;
	font-weight: 500;
}

.place-header_sub {
	width: 50%;
	/* margin-left: 30px;
	margin-top: 5px; */
	font-size: 17px;
	font-weight: 300;
	/* padding-left: 30px; */
}

.card-footer_master {
	position: absolute;
	width: 100%;
	margin: 0 auto;
	font-size: 17px;
	font-weight: 300;
	/* font-weight: 400;
	padding-top: 10px;
	padding-left: 140px;
	margin-top: 10px;
	margin-right: 10px;*/
	text-align: right;
}

.btn-delete {
	display: flex;
	box-sizing: border-box;
	text-align: center;
	font-size: 24px;
	font-weight: 500;
	/* padding-left: 10px; */
	width: 14%;
	height: 40px;
	border-radius: 8px;
	text-decoration: none;
}

td {
	width: 10%;
}
</style>
</head>
<body>
	<%
		List<ScheduleRegisterVO> moimlist = (List<ScheduleRegisterVO>) request.getAttribute("moimAllList");
	%><!-- 모임 리스트 -->
	<%
		MemberVO user = (MemberVO) session.getAttribute("user");
	%><!-- 세션 받아오는 -->
	<%
		MemberVO member = (MemberVO) request.getAttribute("member");
	%><!-- 멤버정보 받아오는 -->
	<%
		GatheringInfoVO gathering = (GatheringInfoVO) request.getAttribute("gathering");
	%>
	<!-- 모임정보 받아오는 -->
	<%
		ScheduleRegisterVO schedule = (ScheduleRegisterVO) request.getAttribute("schedule");
	%>

	<table style="word-wrap: break-word; word-break: break-all;">
		<%
			if (user != null) {
				String loginUser = user.getMem_id();
			}
			int size = (int) Math.ceil(moimlist.size() / (5.0));
			if (size != 0) {
				for (int i = 0; i < size; i++) {
		%>
		<tr>
			<%
				if (size - 1 == i) {
							for (int j = 0; j < 5; j++) {
								if (j + (i * 5) > moimlist.size() - 1) {
									break;
								}
								ScheduleRegisterVO row = moimlist.get(j + i * 5);
			%>

			<td class="wrap">
				<div class="card-wrap">
					<div class="card">
						<div class="event"
							style="visibility: visible; animation-name: slideInUp;">


							<div class="row">
								<div class="col-xl-9"></div>
								<div class="col-xl-3">

									<%-- <% // user가 master일 때.(삭제 가능)
														if(row.getSche_master() == loginUser){
														%> --%>
									<a
										href="/damoim/gathering/sch_moimDelete.do?gath_no=<%=gathering.getGath_no()%>&sche_no=<%=row.getSche_no()%>">
										<button type="submit" class="btn-delete">
											<i class="fas fa-times fa-1x"></i>
										</button>
									</a>
									<%-- <%}else { %>
														<a href="/damoim/gathering/sch_main.do?gath_no=<%=gathering.getGath_no() %>"><button type="submit" class="btn-delete" ></button></a>
														<%} %> --%>
								</div>
							</div>


							<div class="card-header_name"><%=row.getSche_name()%></div>
							<div class="date-header_main"><%=row.getSche_date()%></div>
							<div class="date-header_sub">
								<%=row.getSche_time()%></div>
							<div class="place-header">
								<div>
									<i class="fas fa-map-marker-alt"></i>
									<%=row.getSche_loc()%>
								</div>

								<br /> <span
									style="font-size: 16px; font-weight: 300; text-align: center;">
									<%=row.getSche_context()%></span>

							</div>
							<div class="card-footer_master">
								master:<%=row.getSche_master()%>
							</div>
						</div>
					</div>
				</div>
			</td>


			<%
				}

						}
			%>

		</tr>
		<%
			}
			}
		%>
	</table>


</body>
</html>