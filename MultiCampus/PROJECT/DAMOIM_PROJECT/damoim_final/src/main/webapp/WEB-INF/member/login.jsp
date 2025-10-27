<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="theme.css" type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<script type="text/javascript">
	
<%boolean check = (boolean) request.getAttribute("fail");
			if (check == true) {%>
	alert("아이디/비밀번호가 잘못되었습니다.");
<%}%>
	
</script>


</head>
<body style="">

	<div class="text-center pt-5"
		style="opacity: 0.5; background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.8)); background-position: top left; background-size: 100%; background-repeat: repeat;">
		<div class="container">
			<div class="row">
				<div class="mx-auto col-md-6 col-10 bg-white p-5">
					<h1 class="mb-4">로그인</h1>
					<form name="login_frm" action="/damoim/member/login.do"
						method="post">
						<div class="form-group">
							<input type="text" name="mem_id" class="form-control border-dark"
								placeholder="ID를 입력하세요" id="form9">
						</div>
						<div class="form-group mb-3 border-dark">
							<input type="password" name="mem_pass"
								class="form-control border-dark" placeholder="Password"
								id="form10"> <small
								class="form-text text-muted text-right"> <a href="#"
								class="text-body"> </a>
							</small>
						</div>
						<button type="submit" class="btn btn-primary" style="">로그인</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="">
		<div class="container">
			<div class="row">
			
				
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="row">
						<div class="col-md-12">
							
							<p class="lead text-center pt-3">
								처음이신가요? <b><a href="/damoim/member/reg.do">회원가입</a></b>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</div>
	<div class="py-5"></div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous" style=""></script>
</body>

</html>