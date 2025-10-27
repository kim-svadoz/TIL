<%@page import="gathering.info.GatheringInfoVO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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

<% GatheringInfoVO gathering =(GatheringInfoVO)request.getAttribute("gathering");%>
<!-- 모임정보 받아오는 -->

<%String gath_no = (String)request.getAttribute("gath_no");%>
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
					
					/* onChangeMonthYear : function(year, month, inst) {
						// 년 또는 월이 변경시 이벤트 발생
						EvtChangeMonthYear(year, month);
					},
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
						if ($.inArray(date, todaydate) != -1) {    //jquery달력의 날짜가 오늘날짜와 같다면

							return [true, "Highlighted", Highlight];    //스타일 적용

						}else{

							return [true, '', ''];

						}
					}, */
					onSelect : function(date) {
						date = $(this).val();
						/* alert(date+" 선택되었습니다"); */
						 /* $("#date").text(date); */
			            
			            /* $("#year").text(year);
			            $("#month").text(month);
			            $("#day").text(day); */
			            /* var date=new Date($("#datepicker").datepicker({dateFormat:"yy-mm-dd"}).val()); */
			            var arr=date.split("-");
			            var year=arr[0];
			            var month=arr[1];
			            var day=arr[2];
			            
			            mydate = year+month+day;
			            alert(mydate+"로 선택되었습니다");
			            /* //요일 가져오기
			            //데이터를 먼저 가져오고 (숫자로 넘어옴)
			            var date=new Date($("#datepicker").datepicker({dateFormat:"yy-mm-dd"}).val());
			            //일요일 0~
			            week=new Array("일","월","화","수","목","금","토");
			            $("#mydate").text(week[date.getDay()]);*/
			            
			            /* +gath_no */
			    		$.ajax({
			    			url:"/damoim/gathering/ajax_gatheringlist.do",
			    			type:"get",
			    			async:false,
			    			data:{ 
			    				"mydate":mydate,
			    				"gath_no":"${gath_no}"
			    			},
			    			success:function(data){
			    				mydata =
			    					"<tr class='sche_name'><td>"+data[0].sche_name+"</td></tr>"+
			    					"<tr class='sche_date'><td>"+data[0].sche_date+"</td></tr>"+
			    					"<tr class='sche_loc'><td>"+data[0].sche_loc+"</td></tr>"+
			    					"<tr class='sche_context'><td>"+data[0].sche_context+"</td></tr>"+
			    					"<tr class='sche_master'><td>master:"+data[0].sche_master+"</td></tr>";
			    				$("#mydatalist").empty();
			    				$("#mydatalist").append(mydata);
			    			},
			    			error:function(error,m,n){
			    				alert("error!"+error+":"+m+":"+n);
			    			}
			    		})
			    	$.ajax({
			    			url:"/damoim/gathering/ajax_gatheringlist2.do",
			    			type:"get",
			    			async:false,
			    			data:{
			    				"mydate":mydate,
			    				"gath_no":"${gath_no}"
			    			},
			    			success:function(data){
			    				mydata2="";
			    				for(i=0; i<data.length;i++){
			    					
			    					if(data[i].gath_grade == "운영자"){
				    					mydata2 = mydata2 +
				    					"<tr><td class='mem_profile' style='width:20%''><img alt='' style='width:35%' src='/damoim/member/memimg/"+data[i].mem_profile+"'></td>"+
				    					"<td class='mem_info' style='width:10%''><div class='mem_name'>"+data[i].mem_name+"</div>"+
				    					"<div class='mem_age'>"+data[i].mem_age+"</div></td>"+
				    					"<td></td>"+
				    					"<td class='mem_grade' style='width:20%; color:#007FFF; border-color:black;'>"+" "+"</td></tr>"
			    					}else {
			    						mydata2 = mydata2 +
			    						"<tr><td class='mem_profile' style='width:20%''><img alt='' style='width:35%' src='/damoim/member/memimg/"+data[i].mem_profile+"'></td>"+
				    					"<td class='mem_info' style='width:10%''><div class='mem_name'>"+data[i].mem_name+"</div>"+
				    					"<div class='mem_age'>"+data[i].mem_age+"</div></td>"+
				    					"<td></td>"+
				    					"<td class='mem_grade' style='width:20%; color:black, border-color:black;'>"+" "+"</td></tr>"
			    						
			    					}
			    					
			    				}
			    				$("#mydatalist2").empty();
			    				$("#mydatalist2").append(mydata2);
			    			},
			    			error:function(error){
			    				alert("error!");
			    			}
						})
				}
	});
	});
	
	
	/* $(document).ready(function() {
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
	}	 */ 
	
</script>


<style type="text/css">
.ui-datepicker {
	font-size: 44px;
	width: 1100px;
	height: 620px;
	z-index: 100;
	margin: 0px;
}

.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year
	{
	width: 22%;
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
table{
	border: solid 1px;
	width: 97%;
	margin:0 auto;
	border-radius: 5px;
	background-color: #F8F8F8;
	/* border-collapse: separate;*/
	border-spacing: 2px 2px; 

}
.mydatalist{
	background-image: url("/damoim/gathering/images/cal.jpeg");
}
.moimInfo{
	font-size: 30px;
	margin-left:13px;
	margin-bottom:5px;
}
.people{
	font-size: 30px;
	margin-left:13px;
	margin-bottom:5px;
}
.sche_name{
	text-align:center;
	font-size:31px;
	font-weight:520;
	margin-left: 6px;
	border-radius: 3px;
}
.sche_date{
	font-size:22px;
	margin-left: 6px;
}
.sche_loc{
	font-size:22px;
	margin-left: 6px;
}
.sche_context{
	font-size:17px;
	text-align: center;
}
.sche_master{
	float: right;
	font-size:22px;
	margin-right: 6px;
}
.mem_profile{
	text-align:center;
	margin:0 auto;
	
}
.mem_info{
	text-align: center;
	font-size: 24px;
}
.mem_member{
	text-align: center;
	font-size: 24px;
}
.mem_age{
	text-align: center;
	font-size: 24px;
}
.mem_grade{
	border-color:black;
	font-size:27px;
}
</style>
</head>
<body>
	<div>
		<div type="date" id="datepicker"></div>
		</br>
		<div class="moimInfo">모임 정보</div>
		<table id="mydatalist" rules="">
			<tr class="sche_name"><td></td></tr>
			<tr class="sche_date"><td></td></tr>
			<tr class="sche_loc"><td></td></tr>
			<tr class="sche_context"><td></td></tr>
			<tr class="sche_master"><td></td></tr>
		</table>
		</br>
		<div class="people">참석자 명단</div>
		<table id="mydatalist2" rules="rows">
			<tr>
				<td class="mem_profile" style="width:20%"><img alt="" style="width:15" src="/damoim/member/memimg/"></td>
				<td class="mem_info" style="width:10%">
					<div class="mem_member">
						
					</div>
					<div class="mem_age">
						
					</div>
				</td>
				<td style="width:50%"></td>
				<td class="mem_grade" style="width:20%"></td>
			</tr>
		</table>
	</div>
</body>
</html>