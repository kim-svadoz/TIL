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
			// 아이디
			$("#id").on("keyup",function(){
				// <<jQuery에서 Ajax로 요청하기>> - get방식
				// url => 요청 path
				// data => 파라미터: json형식
				// 		*json형식 {"name":"value";"name":"value";...}
				// success함수: ajax요청해서 성공적으로 데이터를 받아왔을 때 처리할 내용을 함수로 표현
				// dataType: ajax요청 후 응답받을 데이터의 형식
				$.get("/damoim/member/idCheck.do", // RequestMapping에 걸어준 것
						{"id":$("#id").val()}, 
						function(data) { //응답 데이터
							//alert(data);
							// ajax로 요청해서 응답받은 데이터를 <span>태그 내부에 출력
							$("#checkVal_id").text(data);
						},
						"text")
			});
			
			// 닉네임
			$("#nickname").on("keyup",function(){
				$.get("/damoim/member/nicknameCheck.do", // RequestMapping에 걸어준 것
						{"nickname":$("#nickname").val()}, 
						function(data) { //응답 데이터
							//alert(data);
							// ajax로 요청해서 응답받은 데이터를 <span>태그 내부에 출력
							$("#checkVal_nick").text(data);
						},
						"text")
			});
			
			/* 동적컨텐츠에 이벤트 붙이기-on */
			buttonAble = document.getElementById("sub");
			$("#passCheck").on("keyup",function(){
				check = $("#pass").val();
				if(check == $("#passCheck").val()){
					$("#checkVal_pass").text("일치");
					buttonAble.disabled = false;
				}else{
					$("#checkVal_pass").text("비밀번호가 일치하지 않습니다.");
					buttonAble.disabled = true;
				}
				
			});
			
	
		});
		
	</script>
</head>

<body>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12" style="">
          <ul class="nav nav-pills" style="">
            <li class="nav-item w-25"> <a class="nav-link disabled" href="#"><b>1. 본인인증</b></a> </li>
            <li class="nav-item w-25" style=""> <a href="#" class="active nav-link"><b>2. 정보입력</b></a> </li>
            <li class="nav-item w-25"> <a href="#" class="nav-link disabled"><b>3. 정보확인</b></a> </li>
            <li class="nav-item-25"> <a href="#" class="nav-link disabled"><b>4. 가입완료</b></a> </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5 text-center">
    <div class="container">
      <div class="row" style="" >
        <div class="mx-auto col-lg-6 col-10">
          <h1>Damoim</h1>
          <h3><p class="mb-3">환영합니다!</p></h3>
          <br/>
          <br/>
          <form action="/damoim/member/reg3.do" method="post" class="text-left" style="">
            <div class="form-group"> <label for="form16">아이디</label> <input required="required" type="text" id="id" name="mem_id" class="form-control"  placeholder="4글자 이상">
              <span id="checkVal_id" style="color: red;"></span>
            </div>
            <div class="form-group"> <label for="form17">닉네임</label> <input required="required" type="text" id="nickname" name="mem_nickname" class="form-control" placeholder="4글자 이상"> </div><span id="checkVal_nick" style="color: red;"></span>
            <div class="form-row">
              <div class="form-group col-md-6"> <label for="form19">비밀번호</label> <input required="required" type="password" id= "pass" name="mem_pass" class="form-control" id="form19" placeholder="••••"> </div>
              <div class="form-group col-md-6"> <label for="form20">비밀번호 재확인</label> <input required="required" type="password" id="passCheck" class="form-control" placeholder="••••"> </div>
            </div><label id= "checkVal_pass" class="text-danger"></label>
            <div class="form-group"> <label for="form17">이름</label> <input required="required" type="text" name="mem_name" class= "form-control" id="form17" placeholder="2글자 이상"> </div>
            <div class="form-group"> <label for="form17">생년월일</label> </div>
            <div class="row">
              <div class="col-md-4" style="">
                <div class="form-group row">
                  <div class="col-10 border-dark col-md-6" style="">
                    <select id="year" name="year">
                      <option value="1985">1985</option>
                      <option value="1986">1986</option>
                      <option value="1987">1987</option>
                      <option value="1988">1988</option>
                      <option value="1989">1989</option>
                      <option value="1990">1990</option>
                      <option value="1991">1991</option>
                      <option value="1992">1992</option>
                      <option value="1993">1993</option>
                      <option value="1994">1994</option>
                      <option value="1995">1995</option>
                      <option value="1996">1996</option>
                    </select>
                  </div>
                  <div class="col-10 border-dark col-md-6" style="">
                    <p class="pt-1 pb-1">년</p>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="btn-group">
                  <select id="month" name="month">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                  </select>
                  <div class="col-10 border-dark col-md-6" style="">
                    <p class="pt-1">월</p>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="form-group row">
                  <div class="col-10">
                    <div class="btn-group">
                      <select id="day" name="day">
                        <option value="1">1</option>
                   	 	<option value="2">2</option>
                    	<option value="3">3</option>
                    	<option value="4">4</option>
                    	<option value="5">5</option>
                    	<option value="6">6</option>
                    	<option value="7">7</option>
                    	<option value="8">8</option>
                    	<option value="9">9</option>
                    	<option value="10">10</option>
                    	<option value="11">11</option>
                    	<option value="12">12</option>
                    	<option value="13">13</option>
                    	<option value="14">14</option>
                    	<option value="15">15</option>
                    	<option value="16">16</option>
                    	<option value="17">17</option>
                    	<option value="18">18</option>
                    	<option value="19">19</option>
                    	<option value="20">20</option>
                    	<option value="21">21</option>
                    	<option value="22">22</option>
                    	<option value="23">23</option>
                    	<option value="24">24</option>
                    	<option value="25">25</option>
                    	<option value="26">26</option>
                    	<option value="27">27</option>
                    	<option value="28">28</option>
                    	<option value="29">29</option>
                    	<option value="30">30</option>
                    	<option value="31">31</option>
                        
                      </select>
                      <div class="col-10 border-dark col-md-6" style="">
                        <p class="pt-1">일</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-group" style="opacity: 0.5;"> <label for="form18">휴대전화</label> <input required="required" type="text" 
		name="mem_phone" class="form-control border-dark" id="form18" placeholder="전화번호 입력"> </div>
            <div class="form-group">
              <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4 pl-5"></div>
                <div class="col-md-4"></div>
              </div>
            </div>
          <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4"><button id="sub" type="submit" class="btn btn-secondary"><b>다 음</b></button></div>
          </form>
            <div class="col-md-4"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>