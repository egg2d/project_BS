<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Register</title>
    
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/bootstrap/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/bootstrap/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">IN+</h1>

            </div>
            <h3>Register to IN+</h3>
            <p>Create account to see it in action.</p>
            <!-- <form class="m-t" role="form" action="login.html"> -->
            <form:form class="form-horizontal" role="form" id="REGISTER_FORM"  modelAttribute="UserVo" action= "/registerId.do" method="post">
                <div class="form-group">
                
                	<div class="row">
                		<div class=" col-lg-8">
                    	<input type="text" class="form-control" placeholder="Id" required="" id="USER_ID" name="USER_ID" >
                		</div>
                		<button type="button" class="btn btn-success" id="ID_CHECK">중복 체크</button>
                		
                	</div>
                   	    
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Password" required="" id="PASSWORD" name="PASSWORD">
                </div>
                
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="ConFirm-Password" required="" id="PASSWORD_CONFIRM"  name="PASSWORD_CONFIRM">
                </div>
                
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="Email" required="" id="EMAIL" name="EMAIL">
                </div>
              
                <div class="form-group">
                        <div class="checkbox i-checks"><label> <input type="checkbox"><i></i> Agree the terms and policy </label></div>
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="registerConfrim()">Register</button>

                <p class="text-muted text-center"><small>Already have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="login.html">Login</a>
            </form:form>
            <p class="m-t"> <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small> </p>
        </div>
    </div>

    <!-- Mainly scripts -->
   <script src="/bootstrap/js/jquery-3.1.1.min.js"></script>
    <script src="/bootstrap/js/popper.min.js"></script>
    <script src="/bootstrap/js/bootstrap.js"></script>
    <!-- iCheck -->
    <script src="/bootstrap/js/plugins/iCheck/icheck.min.js"></script>
    <script>
        var idOk ='N'
        $(document).ready(function(){
    /*         $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            }); */
            
            $('#ID_CHECK').on('click', function(){
            	// 아이디 중복 체크 
            	var result = checkId();
            	
            	if(result == 'ok') {
            		alert('사용 가능한 아이디 입니다.');
            	} else if(result =='overlap'){
            		alert('중복된 아이디 입니다.');
            	}else if(result =='shortLength') {
            		alert('아이디는 5자리 이상 가능합니다.');
            	}
            	else {
            		alert('서버에서 에러가 발생하였습니다.');
            	}
            	
            });
        });
        
        function registerConfrim () {
        	
			var id = document.getElementById('USER_ID').value;
			var password = document.getElementById('PASSWORD').value;
			var password2 = document.getElementById('PASSWORD_CONFIRM').value;
			var email = document.getElementById('EMAIL').value;        	

			if (password === password2) {
			
				if(!chkPwd(password)) {
					 alert('비밀번호를 확인하세요.\n(영문,숫자를 혼합하여 6~20자 이내)');    
					 $('#PASSWORD').val('');
					 $('#PASSWORD_CONFIRM').val('');
					 return;
				} 
				
				
			} else {
				alert("비밀번호를 동일하게 입력해주시기 바랍니다.");
				return;
			}
			
			if(email == null || email.trim() == "") {
				
				alert("이메일은 필수 값입니다.");
				return;				
			}
			
				// 아이디 체크 	
				var result = checkId();
				
				if(result == 'ok') {
					// register 메서드
					register();
            	} else if(result =='overlap'){
            		alert('중복된 아이디 입니다.');
            	}else if(result =='shortLength') {
            		alert('아이디는 5자리 이상 가능합니다.');
            	}
            	else {
            		alert('서버에서 에러가 발생하였습니다.');
            	}
			
			
			
        }
        
        function chkPwd(str){

        	 var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

        	 if(!reg_pwd.test(str)){

        	  return false;

        	 }

        	 return true;

        }
		
        function checkId() {
         
         var UserVo = {  USER_ID :  $('#USER_ID').val() }	
      	 var result='';
		 

         if($('#USER_ID').val().length < 5) {
        	 
        	return 'shortLength'; 
         }
        
         $.ajax({
			
			type : "POST",
			url :  "/registerCheck.do",
			data : UserVo,
			dataType : "json",
			async: false,
			contentType: "application/x-www-form-urlencoded; charset=utf-8",			
			//contentType: "application/json; charset=utf-8",			
			
			success : function(data) {
				   
			 
				if(data.registerCheck == "ok") {
			        // 사용 가능
			        result ='ok'
					
				} else {
					// 중복 
			        result ='overlap'
				}
			},	
			
			error:function(request,status,error){

				  console.log("에러 code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				  result='error';
			}

			 
		 });
        	
		return result;

        }

		function register () {
			
			document.getElementById('REGISTER_FORM').submit();
			
			
		}
        
        
        
        
    </script>
</body>

</html>
