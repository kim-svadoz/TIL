<%@page import="member.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>

<body class="w-100 h-100 align-items-center" >
<%MemberVO mem =(MemberVO)request.getAttribute("member");%>

  <div class="py-3">
    <div class="container">
        <div class="col-md-12"><img class="img-fluid d-block rounded-circle mx-auto w-25" src="/damoim/gathering/images/G001_image_1.jpg"></div>
      </div>
    </div>
  <hr/>
  <div class="py-3">
    <div class="container">
      <div class="row">
        <div class="col-md-3" style=""></div>
        <div class="col-md-2" style="">
          <h4 class="text-right">이름&nbsp; &nbsp; &nbsp; &nbsp;:</h4>
        </div>
        <div class="col-md-3" style="">
          <h4 class="" contenteditable="true"><%=mem.getMem_name() %></h4>
        </div>
        <div class="col-md-3" style=""></div>
      </div>
      <div class="row">
        <div class="col-md-3" style=""></div>
        <div class="col-md-2" style="">
          <h4 class="text-rght">생년월일:</h4>
        </div>
        <div class="col-md-3" style="">
          <h4 class="" ><%=mem.getMem_birth() %></h4>
        </div>
        <div class="col-md-3" style=""></div>
      </div>
      <div class="row">
        <div class="col-md-3" style=""></div>
        <div class="col-md-2" style="">
          <h4 class="text-right">자기소개:</h4>
        </div>
        <div class="col-md-3" style="">
          <h4 class=""></h4>
        </div>
        <div class="col-md-3" style=""></div>
      </div>
      <div class="row">
        <div class="col-md-3" style=""></div>
        <div class="col-md-7" style="">
          <h4 class="text-left" contenteditable="true">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<%=mem.getMem_msg() %></h4>
        </div>
      </div>
    </div>
  </div>
</body>

</html>