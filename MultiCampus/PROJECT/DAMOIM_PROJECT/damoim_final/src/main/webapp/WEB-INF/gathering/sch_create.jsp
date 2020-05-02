
<%@page import="member.MemberVO"%>
<%@page import="gathering.info.GatheringInfoVO"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/cfae7cf239.js"
	crossorigin="anonymous"></script>
<!-- <link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"> -->
 <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script> -->

<!-- ===========각종jQuery파일 -->
<!-- jQuery UI CSS파일  -->
<!-- <link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
jQuery 기본 js파일
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
jQuery UI 라이브러리 js파일
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> -->
<style type="text/css">
.form-text {
	display: block;
	margin-top: .25rem;
}

.small, small {
	font-size: 80%;
	font-weight: 400;
}

.card-title {
	margin-bottom: 5px;
	padding-bottom: 10px;
	padding-left: 50px;
	color: #FF399B;
}

.card-body {
	position: relative;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
		"Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
	font-size: .9rem;
	font-weight: 400;
	line-height: 1.5;
	color: #212529;
	text-align: left;
	-webkit-font-smoothing: antialiased;
	flex: 1 1 auto;
	padding: 1.25rem;
	-webkit-box-flex: 1;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid rgba(0, 0, 0, .125);
	border-radius: .25rem;
}

h5 {
	font-family: Sarabun, sans-serif;
	font-weight: 300;
	font-size: 1.25rem;
	line-height: 1.2;
	color: inherit;
	margin-top: 0;
	display: block;
	margin-block-start: 1.67em;
	margin-block-end: 1.67em;
	margin-inline-start: 0px;
	margin-inline-end: 0px
}

.card {
	position: relative;
	-webkit-box-direction: normal;
	text-align: left;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid rgba(0, 0, 0, .125);
	border-radius: .25rem;
}

.position-relative {
	position: relative !important;
}

.form-group {
	margin-bottom: 1rem;
}

.form-control {
	display: block;
	width: 90%;
	height: calc(2.25rem + 2px);
	padding: .375rem .75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

.input {
	overflow: visible;
	margin: 0;
	font-family: inherit;
	-webkit-writing-mode: horizontal-tb !important;
	text-rendering: auto;
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	text-align: start;
	-webkit-appearance: textfield;
	-webkit-rtl-ordering: logical;
	cursor: text;
	font: 400 13.3333px Arial;
}

input[type="file" i] {
	-webkit-appearance: initial;
	background-color: initial;
	cursor: default;
	align-items: baseline;
	color: inherit;
	text-align: start !important;
	padding: initial;
	border: initial;
}

.label {
	cursor: default;
	display: block;
	margin: 0 0 .5rem;
	font-weight: 700;
	color: #495057;
}

.row {
	display: flex;
	flex-wrap: wrap;
	margin-right: 30px;
	margin-left: 30px;
}

* {
	box-sizing: border-box;
}

html {
	-webkit-tap-highlight-color: transparent;
}

.col-xl-10 {
	-webkit-box-flex: 0;
	flex: 0 0 83.3333333333%;
	max-width: 83.3333333333%;
}

.col-xl-2 {
	-webkit-box-flex: 0;
	flex: 0 0 16.6666666667%;
	max-width: 16.6666666667%;
}

.col, .col-1, .col-10, .col-11, .col-12, .col-2, .col-3, .col-4, .col-5,
	.col-6, .col-7, .col-8, .col-9, .col-auto, .col-lg, .col-lg-1,
	.col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4,
	.col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-lg-auto,
	.col-md, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2,
	.col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8,
	.col-md-9, .col-md-auto, .col-sm, .col-sm-1, .col-sm-10, .col-sm-11,
	.col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6,
	.col-sm-7, .col-sm-8, .col-sm-9, .col-sm-auto, .col-xl, .col-xl-1,
	.col-xl-10, .col-xl-11, .col-xl-12, .col-xl-2, .col-xl-3, .col-xl-4,
	.col-xl-5, .col-xl-6, .col-xl-7, .col-xl-8, .col-xl-9, .col-xl-auto {
	position: relative;
	width: 100%;
	padding-right: 30px;
	padding-left: 30px;
}

.col-form-label {
	padding-bottom: calc(.375rem + 1px);
	margin-bottom: 0;
	font-size: inherit;
	line-height: 1.5;
	padding-top: calc(.375rem + 1px);
}

.btn-secondary {
	color: #fff;
	background-color: #FF399B;
	border-color: #FF399B;
	width: 120px;
	height: 41px;
	float: right;
	box-shadow: 0 0.125rem 0.625rem rgba(58, 196, 125, 0.4), 0 0.0625rem
		0.125rem rgba(58, 196, 125, 0.5);
	padding: .5rem 2rem;
	line-height: 1.5;
	text-align: center;
	vertical-align: middle;
	user-select: none;
	border: 1px solid transparent;
	cursor: pointer;
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
	cursor: pointer;
	transition: color .15s, background-color .15s, border-color .15s,
		box-shadow .15s;
}

button, select {
	text-transform: none;
}

button, input {
	overflow: visible;
}

button {
	margin: 0;
	font-family: inherit;
	-webkit-writing-mode: horizontal-tb !important;
	text-rendering: auto;
	letter-spacing: normal;
	word-spacing: normal;
	text-indent: 0px;
	text-shadow: none;
	align-items: flex-start;
	font: 700 13.3333px Arial;
}

.form-control-file, .form-control-range {
	display: block;
	width: 100%;
}

.text-muted {
	color: #6c757d !important;
}

button, input, optgroup, select, textarea {
	margin: 0;
	font-family: inherit;
	font-size: inherit;
	line-height: inherit;
}
</style>

<script>
	$(function() {
		$("#datepicker").datepicker(
				{
					dateFormat : 'yy-mm-dd',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					changeMonth : true, //월변경가능
					changeYear : true, //년변경가능
					showMonthAfterYear : true, //년 뒤에 월 표시
					closeText : '닫기',
					prevText : '이전달',
					nextText : '다음달',
					currentText : '오늘',
					weekHeader : 'Wk',
					firstDay : 0,
					isRTL : false,
					duration : 200,
					/* showAnim : 'Slide down' */
 					//maxDate : dtNow, // 오늘 날자이후 데이터 클릭은 막기위해
					yearRange: 'c-10:c',
					yearSuffix : '년',
					showOtherMonths : true, // 나머지 날짜도 화면에 표시
					//selectOtherMonths : true, // 나머지 날짜에도 선택을 하려면 true
					
					beforeShow : function(input, inst) {
						// 일자 선택되기전 이벤트 발생
						var today = new Date();
						var dd = today.getDate();
						var mm = today.getMonth()+1;
						var yy = today.getFullYear();
						if(dd<10){
							dd='0'+dd
						}
						if(mm < 10){
							mm = '0'+mm
						}
						date=yy+'/'+mm+'/'+dd;
						var Highlight = todaydate[date];
						if ($.inArray(date, todaydate) >= 0) {    //jquery달력의 날짜가 오늘날짜와 같다면
							return [true, "Highlighted", Highlight];    //스타일 적용
						}else{
							return [true, '', ''];
						}
					},
					onSelect : function(date) {
						date = $(this).val();
			            
						 $("#date").text(date);
			            var arr=date.split("-");
			            var year=arr[0];
			            var month=arr[1];
			            var day=arr[2];
			            
			            $("#year").text(year);
			            $("#month").text(month);
			            $("#day").text(day);
			            
			            //요일 가져오기
			            //데이터를 먼저 가져오고 (숫자로 넘어옴)
			            var date=new Date($("#datepicker").datepicker({dateFormat:"yy-mm-dd"}).val());
			            //일요일 0~
			            
			            week=new Array("일","월","화","수","목","금","토");
			            $("#mydate").text(week[date.getDay()]);
			            
			            var mydate = year+"-"+month+"-"+day;
			            alert("mydate ="+mydate);
			    		
 			            $.ajax({
		    			url:"/damoim/gathering/ajax_gatheringlist3.do",
		    			async : false,
		    			type:"POST",
		    			data:{ 
		    				mydate:"mydate"
		    			}
		    			<%-- <input type="hidden" name="sche_date" value="<%=mydate %>"> --%>
		    			/* success:function(data){
		    				mydata="";
		    				for(i=0; i<data.length;i++){
		    					mydata = mydata + 
		    					"<tr class='sche_name'>"+data[i].sche_name+"</tr>"+
		    					"<tr class='sche_date'>"+data[i].sche_date+"</tr>"+
		    					"<tr class='sche_loc'>"+data[i].sche_loc+"</tr>"+
		    					"<tr class='sche_text'>"+data[i].sche_text+"</tr>"+
		    					"<tr class='sche_master'>"+data[i].sche_master+"</tr>"
		    					
		    				}
		    				
		    				$("mydatalist").empty();
		    				$("mydatalist").append(mydata);
		    			} */
		    		})
			            
					}
				})
		$.datepicker.setDefaults($.datepicker.regional['ko']);
	});
	
	
	
	$(document).ready(function() {
		// 선택된 값 세팅
		EvtChangeMonthYear(dtNow.getFullYear(), dtNow.getMonth());
	});
	function getStrMonth(Month) {
		Month = Month + "";
		if (Month.length == 1) {
			Month = "0" + Month;
		}
		return Month;
	}
	function getStrDay(Day) {
		Day = Day + "";
		if (Day.length == 1) {
			Day = "0" + Day;
		}
		return Day;
	}	
	
</script>


<style type="text/css">
.ui-datepicker {
	font-size: 32px;
	width: 600px;
	height: 420px;
	z-index: 100;
	margin: 0px;
}

.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year
	{
	width: 32%;
}

input[type="date"]::-webkit-calendar-picker-indicator, input[type="date"]::-webkit-inner-spin-button
	{
	display: none;
	appearance: none;
}

input[type="date"]::-webkit-calendar-picker-indicator {
	color: rgba(0, 0, 0, 0);
	opacity: 1;
	display: block;
	background:
		url(https://mywildalberta.ca/images/GFX-MWA-Parks-Reservations.png)
		no-repeat;
	width: 20px;
	height: 20px;
	border-width: thin;
}

.Highlighted a {
	background-color: #456baf !important;
	background-image: none !important;
	color: White !important;
	font-weight: bold !important;
	font-size: 12px;
}
</style>
<script type="text/javascript">
	var dtNow = new Date();
	// 월이나 년이 바뀔때의 이벤트
	function EvtChangeMonthYear(Year, Month) {
		$(".ui-datepicker-current-day")
				.attr("style", "background-color:#ff0000;"); // 선택된 날자에 테두리를 만든다.
		var arrSplit = ($("#datepicker").val()).split("/"); // 선택된 날자를 배열로 받음
		var vDt = new Date();
		var Day = getStrDay(vDt.getDate());
		var dtMin = new Date(Year, Month - 1, 1);
		dtMin = new Date(Year, Month - 1, 1 - dtMin.getDay()); // 달력의 최초 날자를 구하기 위해
		var strMin = dtMin.getFullYear() + "-"
				+ getStrMonth(dtMin.getMonth() + 1) + "-"
				+ getStrDay(dtMin.getDate());
		var dtMax = new Date(new Date(Year, Month, 1) - 86400000);
		var dtMax = new Date(Year, Month - 1, dtMax.getDate() + 6
				- dtMax.getDay()); // 달력의 마지막 날자를 구하기 위해
		var strMax = dtMax.getFullYear() + "-"
				+ getStrMonth(dtMax.getMonth() + 1) + "-"
				+ getStrDay(dtMax.getDate());
		//        var strUrl = "/Lab/NDailyCheck/GetDailyMonthsAjx";
		//        var vLabNo = "@LabNo";
		//        var selCheckGubun = $("#CheckGubunVal").val();
		//        $.ajax({
		//            type: 'POST',
		//            async: true,    //비동기
		//            dataType: "json",
		//            url: strUrl,
		//            data: { LabNo: vLabNo, CheckGubun: selCheckGubun, dtMinDay: strMin, dtMaxDay: strMax },
		//            success: function (data) {   // 통신이 성공적으로 이루어졌을 때 이 함수를 타게 된다.
		//                MathMonths(data, strMin);
		//            },
		//            complete: function (data) {   // 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
		//            },
		//            error: function (xhr, status, error) {
		//                alert("통신에러 → " + xhr.responseText + ":" + error);
		//            }
		//        });
	}
</script>






</head>

<body>
	<%
		GatheringInfoVO gathering = (GatheringInfoVO) request.getAttribute("gathering");
	%>
	<%
		MemberVO member = (MemberVO) session.getAttribute("member");
	%>
	<!-- 세션 받아오는 -->
	<%
		MemberVO user = (MemberVO) session.getAttribute("user");
	%>
	<!-- 세션 받아오는 -->
	<%
		String sche_loc = (String) request.getAttribute("sche_loc");
	%>
	<%
		String sche_lat = (String) request.getAttribute("sche_lat");
	%>
	<%
		String sche_lng = (String) request.getAttribute("sche_lng");
	%>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="card-body">
						<h5 class="card-title" style="font-weight: 400; font-size: 24px;">
							모임생성</h5>
						<form:form class="" name="kakaoform"
							action="/damoim/gathering/sch_create.do" method="post"
							commandName="scheduleRegisterVO">
							<input type="hidden" name="sche_gno"
								value="<%=gathering.getGath_no()%>" />
							<input type="hidden" name="sche_master"
								value="<%=user.getMem_id()%>" />
							<div class="position-relative row form-group">
								<label for="text" class="col-xl-2">별칭</label>
								<div class="col-xl-10">
									<input name="sche_name" id="text" type="text"
										class="form-control" />
									<form:errors path="sche_name" />
									</span>
								</div>
							</div>

							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">회비</label>
								<div class="col-xl-10">
									<textarea name="sche_fee" id="exampleText" class="form-control"></textarea>
								</div>
							</div>


							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">날짜</label>
								<div class="col-xl-10">
									<input type="date" id="datepicker" name="sche_date"
										class="datepicker">
								</div>
							</div>

							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">시간</label>
								<div class="col-xl-10">
									<textarea name="sche_time" id="exampleText"
										class="form-control"></textarea>
								</div>
							</div>

							<!-- <div class="form-group">
								<label for="sel1">Hour:</label> <select
									class="form-control" id="sel1" name="sellist1">
									<option>12시</option>
									<option>13시</option>
									<option>14시</option>
									<option>15시</option>
									<option>16시</option>
									<option>17시</option>
									<option>18시</option>
									<option>19시</option>
									<option>20시</option>
									<option>21시</option>
								</select> <br> <label for="sel2">Minute:</label> <select multiple
									class="form-control" id="sel2" name="sellist2">
									<option>00분</option>
									<option>10분</option>
									<option>20분</option>
									<option>30분</option>
									<option>40분</option>
									<option>50분</option>
								</select>
							</div> -->

							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">위치</label>
								<div class="col-xl-10">
									<a href="/damoim/gathering/kakaomap.do?"
										onclick="window.open(this.href, '_blank', 'width=1150px,height=750px,toolbars=no,scrollbars=no'); return false;">
										<i class="fas fa-map-marker-alt fa-3x"></i>
									</a> <input type="text" name="sche_loc" value="위치를 지정하세요"
										class="form-control">
									<!-- 위치 키워드 값 -->
									<input type="hidden" name="sche_lat" value="<%=sche_lat%>"
										class="form-control">
									<!-- 위도 -->
									<input type="hidden" name="sche_lng" value="<%=sche_lng%>"
										class="form-control">
									<!-- 경도 -->
								</div>
							</div>


							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">모임소개</label>
								<div class="col-xl-10">
									<textarea name="sche_context" id="exampleText"
										class="form-control"></textarea>
									<form:errors path="sche_context" />
									</span>
								</div>
							</div>

							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">카카오톡URL</label>
								<div class="col-xl-10">
									<textarea name="sche_chat" id="exampleText"
										class="form-control"></textarea>
								</div>
							</div>

							<div class="position-relative row form-group">
								<label for="exampleText" class="col-xl-2 col-form-label">정원</label>
								<div class="col-xl-10">
									<textarea name="sche_limit" id="exampleText"
										class="form-control"></textarea>
									<form:errors path="sche_limit" />
									</span>
								</div>
							</div>

							<!-- <div class="position-relative row form-group">
								<label for="exampleFile" class="col-xl-2 col-form-label">File</label>
								<div class="col-xl-10">
									<input name="file" id="exampleFile" type="file"
										class="form-control-file"> <small
										class="form-text text-muted"></small>
								</div>
							</div> -->


							<div class="position-relative row form-check">
								<div class="col-xl-12">
									<!--<a
										href="/damoim/gathering/sch_createFinish.do?gath_no=<%-- <%=gathering.getGath_no() %> --%>"
										onclick="window.open(this.href, '_self', 'width=600px,height=400px,toolbars=no,scrollbars=no'); return false;">-->
									<button type="submit" class="btn btn-secondary">Create</button>
									<!-- </a> -->
								</div>
							</div>



						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

