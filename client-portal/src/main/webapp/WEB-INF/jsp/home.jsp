<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
		<%@include file="style.css"%>
		 
.login-reg-panel{
    position: relative;
    top: 50%;
    transform: translateY(-50%);
	text-align:center;
    width:70%;
	right:0;left:0;
    margin:auto;
    height:400px;
    background-color: rgba(236, 48, 20, 0.9);
}
.white-panel{
    background-color: rgba(255,255, 255, 1);
    height:70%;
    position:absolute;
    top:20%;
    left:35%;
    width:30%;
    right:calc(50% - 50px);
    transition:.3s ease-in-out;
    z-index:0;
    box-shadow: 0 0 15px 9px #00000096;
    opacity:0.89;
}
.login-reg-panel input[type="radio"]{
    position:relative;
    display:none;
}
.login-reg-panel{
    color:#B8B8B8;
}
.login-reg-panel #label-login, 
.login-reg-panel #label-register{
    border:1px solid #9E9E9E;
    padding:5px 5px;
    width:150px;
    display:block;
    text-align:center;
    border-radius:10px;
    cursor:pointer;
    font-weight: 600;
    font-size: 18px;
}
.login-info-box{
	height: 100%;
    width:50%;
    padding:0 50px;
    top:20%;
    left:0;
    position:absolute;
    text-align:left;
}
.register-info-box{
    height: 100%;
    width:50%;
    padding:0 50px;
    top:20%;
    left:0;
    position:absolute;
    text-align:left;
}
.right-log{right:50px !important;}

.login-show, 
.register-show{
    z-index: 1;
    display:none;
    opacity:0.5;
    transition:0.3s ease-in-out;
    color:#242424;
    text-align:left;
    padding:50px;
}
.show-log-panel{
	background-image: url(../images/road.jpg);
    display:block;
    opacity:0.8;
}
.login-show input[type="text"], .login-show input[type="password"]{
    height: 100%;
    width: 100%;
    display: block;
    margin:20px 0;
    padding: 10px;
    border: 1px solid #b5b5b5;
    outline: none;
}
.login-show input[type="submit"] {
    max-width: 150px;
    border-radius:10px;
    width: 100%;
    background: #444444;
    color: #f9f9f9;
    border: none;
    padding: 10px;
    text-transform: uppercase;
    border-radius: 20px;
    float:center;
    cursor:pointer;
}
.login-show input[type="reset"] {
    max-width: 150px;
    border-radius:10px;
    width: 100%;
    background: #444444;
    color: #f9f9f9;
    border: none;
    padding: 10px;
    text-transform: uppercase;
    border-radius: 20px;
    float:center;
    cursor:pointer;
}
.login-show a{
    display:inline-block;
    padding:1% 0;
}

.register-show input[type="text"], .register-show input[type="password"]{
    height: 100%;
    width: 100%;
    display: block;
    margin:10px 0;
    padding: 10px;
    border: 1px solid #b5b5b5;
    outline: none;
}
    </style>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body style="background-image: url(../images/home.png); background-size: 90% 90%;">
	<h1 align="center"  class="glow">Return Service</h1>
	
		<c:choose>
		
		<c:when test="${msg == null}">
		<sf:form method="post" action="response" modelAttribute="processRequest" name="f1">
			 <div class="white-panel">
             	<div class="login-show" >
             		<table>
                    	<!--<tr>
                        	<td>Request Id:</td>
                        	<td> <sf:input type="text" path="requestId" name="requestId" size="20" required="required"/></td>
                        </tr>-->
                        <tr>
                        	<td>Name:</td>
                        	<td><sf:input type="text" path="name" name="name"  pattern=".{3,}" title="minimum 3 character required" /></td>
                        </tr>
                        <tr>
                        	<td>Contact Number:</td>
                        	<td><sf:input type="text" path="contactNumber" name="contactNumber" pattern="[0-9]{10}" title="minimum 10 digits required and character not allowed" size="20" required="required"/></td>
                        </tr>
                        <tr>
                        	<td>CreditCard Number:&nbsp;</td>
                        	<td><sf:input type="text" path="creditCardNumber" name="creditCardNumber" pattern="[0-9]{10}" title="10 digit required and character not allowed" size="20"  required="required"/></td>
                        </tr>
               
                    <sf:form modelAttribute="dc">
                    	<table>
              
                    	<tr>
                    		<td>Component Type:</td>
                    		<td><sf:select path="componentType" name="componentType"  items="${componentTypes}" required="required"/></td>
                    	</tr>
                    	<tr>
                    		<td>Component Name:&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    		<td><sf:input type="text" path="componentName" name="componentName"  size="20" required="required"/></td>
                    	</tr>
                    	<tr>
                    		<td>Quantity:</td>
                    		<td><sf:input type="text" path="quantity" name="quantity"  size="20" pattern="^\d*[1-9]\d*$" title="minimum one quantity required not 0" required="required"/></td>
                    	</tr>
                    	<tr>
                    		<td>Priority Request:</td>
                    		<td><input type="radio" path="priorityRequest" name="priorityRequest" size="20" ></td>
                    	</tr>
                    	<tr>
                    		<td></td>
                    		<td><input type="submit" ></td>
                    	</tr>
                    	</table>
                    </sf:form>
        		</table>
			
		
        		
        	</div>
        	</div>
		</sf:form>
	</c:when>
	<c:when test="${msg != null}">
				${msg}<br>
			</c:when>
	</c:choose>
	<ul class="nav">
			<li></li>
  			<li><a href="logout">LogOut</a></li>
	</ul>
	       
              <script type="text/javascript">

              $(document).ready(function(){
              $('.login-info-box').fadeOut();
              $('.login-show').addClass('show-log-panel');
       });


       $('.login-reg-panel input[type="radio"]').on('change', function() {
              if($('#log-login-show').is(':checked')) {
             $('.register-info-box').fadeOut(); 
             $('.login-info-box').fadeIn();
        
             $('.white-panel').addClass('right-log');
             $('.register-show').addClass('show-log-panel');
             $('.login-show').removeClass('show-log-panel');
        
              }
              else if($('#log-reg-show').is(':checked')) {
                    $('.register-info-box').fadeIn();
                    $('.login-info-box').fadeOut();
        
                    $('.white-panel').removeClass('right-log');
        
                    $('.login-show').addClass('show-log-panel');
                    $('.register-show').removeClass('show-log-panel');
              }
       });
       
       </script>
</body>
</html>