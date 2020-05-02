<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
	<script type="text/javascript">
	
		$(document).ready(function() {
			<% 
			boolean check = (boolean)request.getAttribute("fail");	
			if(check == true) { %> alert("비밀번호가 잘못되었습니다.");
			<% 	} %>
			
			/* 동적컨텐츠에 이벤트 붙이기-on */
			buttonAble = document.getElementById("sub");
			$("#passCheck").on("keyup",function(){
				check = $("#pass").val();
				if(check == $("#passCheck").val()){
					$("#checkVal").text("일치");
					buttonAble.disabled = false;
				}else{
					$("#checkVal").text("비밀번호가 일치하지 않습니다.");
					buttonAble.disabled = true;
				}
				
			});
			
	
		});
		
	</script>

</head>

<body >
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class=""><b>내정보</b></h2>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
        	<!-- 컨트롤러 호출  -->
          <form class="" name="myform" action="/damoim/member/passCheck.do" method="post">
            <div class="form-group"> <label>Password</label> <input type="password" id= "pass" name="pass" class="form-control w-25 border-dark" placeholder="비밀번호를 입력하세요" style="opacity: 0.5;"> </div>
            <div class="form-group"> <label>비밀번호 재입력</label> <input type="password" id="passCheck" class="form-control w-25" placeholder="다시 입력하세요">
             <!--  <div class="form-group"><label class="text-danger" id="checkVal"><b>비밀번호가 일치하지 않습니다.</b></label></div> -->
              <div style="color: red;" id="checkVal"></div>
              
              <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
                <div class="col-md-4"></div>
              </div>
            </div>
            <button id="sub" type="submit" class="btn btn-primary"><b>확 인</b></button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>