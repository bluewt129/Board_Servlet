<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
.box{
	width: 450px;
	background: #191919;
	text-align: center;
	padding: 40px;
	margin: 100px auto;
}

.box h1{
	color: white;
	text-transform: uppercase;
}

.box input[type=text], .box input[type=password]{
	border: 0px;
	background: none;
	margin: 20px auto;
	text-align: center;
	border:2px solid #3497db;
	padding: 15px 10px;
	width: 200px;
	outline: none;
	color: white;
	border-radius: 25px;
	transition: 0.25s;
}


.box input[type=text]:focus, .box input[type=password]:focus{
	width: 280px;
	border-color: orange;
}

.box input[type="submit"]{
	border: 0px;
	background: none;
	display : block;
	margin: 20px auto;
	text-align: center;
	border:2px solid #3497db;
	padding: 15px 10px;
	outline: none;
	color: white;
	border-radius: 5px;
	width:100px;
	transition: 0.25s;
	cursor: pointer;
}


.box input[type="submit"]:hover{
	background: yellow;
	color: black;
}
</style>
</head>
<%@ include file="./header.jsp"%>
<body>
	<form class="box" action="./login.do" method="post">
		<h1>login</h1>
		<input type="text" name="id" placeholder="아이디 입력">
		
		<input type="password" name="password" placeholder="비밀번호 입력">
		<input type="submit" value="Login">		
	</form>
</body>
<%@ include file="./footer.jsp"%>
</html>