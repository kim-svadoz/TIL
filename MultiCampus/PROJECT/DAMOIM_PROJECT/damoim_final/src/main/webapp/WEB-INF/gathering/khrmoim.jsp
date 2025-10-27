<%@page import="gathering.info.GatheringInfoVO"%>
<%@page import="java.util.List"%>
<%@page import="member.MemberVO"%>
<%@page import="com.sun.rowset.internal.Row"%>
<%@page import="moim.list.MoimListVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description"
	content="A new design system for developing with less effort.">
<meta name="author" content="BootstrapBay">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="theme.css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<style type="text/css"></style>
<style type="text/css">
</style>


</head>

<body class="align-items-center">
	<%
		List<MemberVO> list = (List<MemberVO>) request.getAttribute("list");
		MoimListVO moim = (MoimListVO) request.getAttribute("moim");
	%>
	<%
		String gath_no = (String) request.getParameter("gath_no");
	%>
	<%
		GatheringInfoVO vo = (GatheringInfoVO) request.getAttribute("gathering");
	%>
	<%
		MemberVO user = (MemberVO) session.getAttribute("user");
	%>
	<%
		boolean memchk = (boolean) request.getAttribute("memchk");
	%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" id="all">
				<div class="py-3">
					<div class="container">
						<div class="row">
							<div class="col-md-3 border border-light col-6">
								<h4 class="my-3">
									<%=moim.getSche_date()%>
								</h4>
							</div>
							<div class="col-md-2 border border-light col-6" style="">
								<h4 class="my-3">
									<%=moim.getSche_time()%>
								</h4>
							</div>
							<div class="col-md-5 border border-light col-6" style="">
								<h4 class="my-3">
									<%=moim.getSche_loc()%>
								</h4>
							</div>
							<div class="col-md-2 border border-light col-6" style="">
								<h4 class="my-3">
									<%=moim.getSche_fee()%>
								</h4>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="container col-md-6">
						<div class="row">
							<div class="w-100 align-items-center col-md-12" style="">
								<div id="map" style="width: 700px; height: 400px;"></div>
								<script type="text/javascript"
									src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5013996650a9ed2ec46c144fe8297133"></script>
								<script>
								var mapContainer = document
								.getElementById('map'), // ������ ǥ���� div 
									mapOption = {
										center : new kakao.maps.LatLng(
												<%=moim.getSche_lat()%>,
												<%=moim.getSche_lng()%>), // ������ �߽���ǥ
										level : 3
									// ������ Ȯ�� ����
									};

									var map = new kakao.maps.Map(
											mapContainer,
											mapOption); // ������ �����մϴ�

									// ��Ŀ�� ǥ�õ� ��ġ�Դϴ� 
									var markerPosition = new kakao.maps.LatLng(
											<%=moim.getSche_lat()%>,
											<%=moim.getSche_lng()%>);

									// ��Ŀ�� �����մϴ�
									var marker = new kakao.maps.Marker(
											{
												position : markerPosition
											});
									
									marker.setMap(map);
									
									var iwContent = '<div style="width:110%; padding:5px;"> <%=moim.getSche_loc()%> </div>',
									
								    iwPosition = new kakao.maps.LatLng(
											<%=moim.getSche_lat()%>,
											<%=moim.getSche_lng()%>); //���������� ǥ�� ��ġ�Դϴ�
								
								// ���������츦 �����մϴ�
								var infowindow = new kakao.maps.InfoWindow({
								    position : iwPosition, 
								    content : iwContent 
								});
								  
								// ��Ŀ ���� ���������츦 ǥ���մϴ�. �ι�° �Ķ������ marker�� �־����� ������ ���� ���� ǥ�õ˴ϴ�
								infowindow.open(map, marker); 
									
								</script>
							</div>
						</div>
					</div>
				</div>

				<br />
				<div class="col-md-12">
					<div class="row">
						<script type="text/JavaScript">
														function text_copy() {
															var doc = document
																	.createElement('textarea');
															doc.textContent = document
																	.getElementById("pagelink").textContent;
															document.body
																	.append(doc);
															doc.select();
															document
																	.execCommand('copy');
															doc.remove();
															alert('�ҽ��� ����Ǿ����ϴ�. �ٿ��ֱ� �Ͻø� �˴ϴ�.');
														}
													</script>
						<script type="text/javascript"></script>
						<textarea name="pagelink" cols="70" rows="1" id="pagelink"><%=moim.getSche_chat()%></textarea>
						<input name="button" type="button" onclick="text_copy();"
							value="�����ϱ�">
					</div>
				</div>
				<br />


				<div class="col-md-3"></div>
				<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
					integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
					crossorigin="anonymous" style=""></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
					integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
					crossorigin="anonymous" style=""></script>
				<script
					src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
					integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
					crossorigin="anonymous" style=""></script>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>

</html>