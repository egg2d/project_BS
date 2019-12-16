<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

    
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Login</title>
    <script src="/bootstrap/js/jquery-3.1.1.min.js"></script>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/bootstrap/css/style.css" rel="stylesheet">
  <!-- Mainly scripts -->
    <script src="/bootstrap/js/popper.min.js"></script>
    <script src="/bootstrap/js/bootstrap.js"></script>

</head>


<script>

 $(function(){

	 
	 $('#login_button').click(function() {
			
		 var UserVo = {
		   USER_ID :  $('#id').val(),
		   PASSWORD : $('#passwd').val()
				  
		 }
		 //UserVo = UserVo.serialize();


		 $.ajax({
		
			type : "POST",
			url :  "/loginTry.do",
			data : UserVo,
			dataType : "json",
			contentType: "application/x-www-form-urlencoded; charset=utf-8",			
			//contentType: "application/json; charset=utf-8",			
			
			success : function(data) {
				   
			
				if(data.loginCheck == "success") {
					location.replace("/main.do");					
				} else {
					alert("아이디 또는 비밀번호가 틀렸습니다.");
				}
			},	
			
			error:function(request,status,error){

				  alert("에러 code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}

			 
		 });
		 
	 });
	 
	 
	 
	 
 });
 

 
 
</script>
<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">IN+</h1>

            </div>
            <h3>Welcome to IN+</h3>
            <p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
            </p>
            <p>Login in. To see it in action.</p>
            <form class="m-t" role="form">
                <div class="form-group">
                    <input type="text"   id="id" name="id" class="form-control" placeholder="Username" required="">
                </div>
                <div class="form-group">
                    <input type="password"  id="passwd" name="passwd" class="form-control" placeholder="Password" required="" >
                </div>
                <button type="button" id="login_button" name="login_button" class="btn btn-primary block full-width m-b">Login</button>

                <a href="#"><small>Forgot password?</small></a>
                <p class="text-muted text-center"><small>Do not have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
            </form>
            <p class="m-t"> <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small> </p>
        </div>
    </div>

  
</body>

</html>
