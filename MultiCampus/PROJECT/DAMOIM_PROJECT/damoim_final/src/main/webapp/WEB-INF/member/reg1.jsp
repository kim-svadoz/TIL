<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	// 전부 동의 안하고 다음 누르면 alert(필수 약관에 동의해야 합니다)
	$(document).ready(function() {
		checklist = document.getElementsByClassName('form-check-input');
		size = checklist.length;
		$("#next").on("click", function() {
			result = true;	
			for(i=0; i<size-1; ++i){
				if(!checklist[i].checked){
					result = false;
				}
			}
			if(result==false){
				alert("필수 약관에 동의해야 합니다.");
			}
			else{
				location.href ="/damoim/member/reg2.do";
			}
		});
	});

</script>


</head>

<body style="	box-shadow: 0px 0px 4px  black, 0px 0px 4px  black;">
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12" style="">
          <ul class="nav nav-pills" style="">
            <li class="nav-item w-25" style=""> <a href="#" class="active nav-link"><b>1. 본인인증</b></a> </li>
            <li class="nav-item w-25 text-primary"> <a class="nav-link disabled" href="#"><b>2. 정보입력</b></a> </li>
            <li class="nav-item w-25"> <a href="#" class="nav-link disabled"><b>3. 정보확인</b></a> </li>
            <li class="nav-item-25"> <a href="#" class="nav-link disabled"><b>4. 가입완료</b></a> </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-lg-4 p-3 col-md-6">
          <div class="card">
            <div class="card-body p-4" style="">
              <h2> <b>[이용 약관]</b></h2>
              <p>여러분을 환영합니다. 다모임 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 다모임 서비스의 이용과 관련하여 다모임 서비스를 제공하는 </p>
              <p class="mb-0 lead"> <a href="#">Read more</a> </p>
            </div>
          </div>
          <div class="form-check"> <input class="form-check-input" type="checkbox" id="form21" value="on"> <label class="form-check-label" for="form21">동의</label> </div>
        </div>
        <div class="col-lg-4 p-3 col-md-6">
          <div class="card">
            <div class="card-body p-4">
              <h4 class=""><b>[개인정보 수집 및 이용에 대한 안내(필수)]</b></h4>
              <p>정보통신망법 규정에 따라 다모임에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다. 1. 수집하는 개인정보</p>
              <p class="mb-0 lead"> <a href="#">Read more</a> </p>
            </div>
          </div>
          <div class="form-check"> <input class="form-check-input" type="checkbox" id="form22" value="on"> <label class="form-check-label" for="form21">동의</label> </div>
        </div>
        <div class="col-lg-4 p-3" style="">
          <div class="card">
            <div class="card-body p-4">
              <h4> <b>[위치정보 이용약관 동의(선택)]</b></h4>
              <p>위치정보 이용약관에 동의하시면, 위치를 활용한 광고 정보 수신 등을 포함하는 다모임 위치기반 서비스를 이용할 수 있습니다. 제 1 조 (목적) 이 약관은 네이버 주식회사 (이하 “회사”)가 제공하는 위치정보사업 또는 위치기반서비스사업과 관련하여&nbsp;</p>
              <p class="mb-0 lead"> <a href="#">Read more</a> </p>
            </div>
            <div class="form-check"> <input class="form-check-input" type="checkbox" id="form23" value="on"> <label class="form-check-label" for="form21">동의</label> </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="" >
    <div class="container">
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 pl-5"><a id="next" class="btn btn-secondary" ><b class="">다 음</b></a></div>
        <div class="col-md-4"></div>
      </div>
    </div>
  </div>
  <div class="">
    <div class="container">
      <div class="row">
        <div class="col-md-8"></div>
        <div class="col-md-4"><div>
      </div>
    </div>
  </div>
  <div class="">
    <div class="container">
      <div class="row">
        <div class="col-md-8"></div>
        <div class="col-md-4"></div>
      </div>
      <div class="row">
        <div class="col-md-12 py-5"></div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12"></div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>