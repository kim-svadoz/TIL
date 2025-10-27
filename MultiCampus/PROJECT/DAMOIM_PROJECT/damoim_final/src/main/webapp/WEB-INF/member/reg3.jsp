<%@page import="member.MemberVO"%>
<%@page import="interest.interest_majorVO"%>
<%@page import="job.jobVO"%>
<%@page import="business.businessVO"%>
<%@page import="java.util.List"%>
<%@page import="location.locationVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <style>
.autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
.autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; cursor: pointer; }
.autocomplete-selected { background: blue; color: white; }
.autocomplete-suggestions strong { font-weight: bold; color: orange; }
.autocomplete-group { padding: 2px 5px; }
.autocomplete-group strong { display: block; border-bottom: 1px solid #000; }
</style>
  
  <script>
  $(document).ready(function() {
	  		var availableTags = [
	    	 <% List<locationVO> list = (List<locationVO>)request.getAttribute("location"); 
	    		int size = list.size();
	    		for(int i=1; i<size-1; ++i){
	    			locationVO row = list.get(i); %>
	    			"<%=row.getLoc_name()%>",
	    		<%}
	    		locationVO row = list.get(size-1);%>
	    		"<%=row.getLoc_name()%>"
			];
	   
	  		 $(".tags").autocomplete({
	  		    source: availableTags,
	  		 	autoFocus: true
	  		  });
	  		 
	  		$(".tags").on("keydown", function(){
	  		    if(event.keyCode == 13) {
	  		      if($(".tags").val().length==0) {
	  		          event.preventDefault();
	  		          return false;
	  		      }
	  		    }
	  		 });
	  		
  } ); 
  
  
  
  </script>
</head>

<!-- ajax 아님 -->
<body>
	<!-- business, job -->
	<% List<businessVO> busi_list = (List<businessVO>)request.getAttribute("business"); 
		List<jobVO> job_list = (List<jobVO>)request.getAttribute("job");
		MemberVO user = (MemberVO)request.getAttribute("user");
	%>
 <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12" style="">
          <ul class="nav nav-pills" style="">
            <li class="nav-item w-25"> <a class="nav-link disabled" href="#"><b>1. 본인인증</b></a> </li>
            <li class="nav-item w-25" style=""> <a class="active nav-link" href="#"><b>2. 정보입력</b></a> </li>
            <li class="nav-item w-25"> <a href="#" class="nav-link disabled"><b>3. 정보확인</b></a> </li>
            <li class="nav-item-25"> <a href="#" class="nav-link disabled"><b>4. 가입완료</b></a> </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h3 class=""><b>선택정보 입력</b></h3>
        </div>
      </div>
    </div>
  </div>
  <form action="/damoim/member/inter.do" method="post">
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="col-md-12">
            <p class="lead text-right">집</p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="ui-widget">
            <label for="tags">검색: </label>
            <input required="required" class="tags" name="mem_home">
          </div>
        </div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="col-md-12">
            <p class="lead text-right">직장</p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="ui-widget">
            <label for="tags">검색: </label>
            <input required="required" class="tags" name="mem_office">
          </div>
        </div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
  <div class="py-5" >
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="col-md-12">
            <p class="lead text-right">관심지역</p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="ui-widget">
            <label for="tags">검색: </label>
            <input required="required" class="tags" name="mem_neighbor">
          </div>
        </div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="col-md-12">
            <p class="lead text-right">직업</p>
          </div>
        </div>
        <div class="col-md-4">
          <select required="required" id="business" name="mem_business">
          <% int size1 = busi_list.size(); 
          	for(int i=0; i<size1; ++i){
          		businessVO business = busi_list.get(i); %>
          		<option value=<%= business.getBusi_no()%>><%= business.getBusi_name()%></option>
          	<% } %>
           </select>
        </div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="col-md-12">
            <p class="lead text-right">직무</p>
          </div>
        </div>
        <div class="col-md-4">
          <select required="required" id="job" name="mem_job">
            <% int size2 = job_list.size();
          	for(int i=0; i<size2; ++i){
          		jobVO job = job_list.get(i); %>
          		<option value=<%= job.getJob_no()%>><%= job.getJob_name()%></option>
          	<% } %>
          </select>
        </div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
  
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="col-md-12">
            <p class="lead text-right" contenteditable="true">성별</p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-check form-check-inline"> <label class="form-check-label">
              <input required="required" class="form-check-input" type="radio" value="m" id="inlineRadio1" name="mem_gender"> 남성 </label> </div>
          <div class="form-check form-check-inline"> <label class="form-check-label">
              <input required="required" class="form-check-input" type="radio" value="f" id="inlineRadio1" name="mem_gender"> 여성 </label> </div>
        </div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
 <%--  <!-- id 넘기기 -->
  <div><input type="hidden" name="mem_id" value=<%= user.getMem_id() %>/></div> --%>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 pl-5"><button type="submit" class="btn btn-secondary" ><b>가입하기</b></button></div>
        <div class="col-md-4" style=""></div>
      </div>
    </div>
  </div>
  </form>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>