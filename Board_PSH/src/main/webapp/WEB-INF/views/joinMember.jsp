<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<style>

table {
    border: 1px solid #ccc;
    border-collapse: collapse;
    background-color: #ffffff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    margin: 150px auto 0;
    width: 600px;
    border-radius: 8px;
    overflow: hidden;
}

tr {
    border-bottom: 1px solid #ddd;
}

tr:last-child {
    border-bottom: none;
}

td, th {
    padding: 15px;
    text-align: left;
    border-right: 1px solid #ddd;
}

td:last-child, th:last-child {
    border-right: none;
}

th {
    background-color: #f7f7f7;
    text-align: right;
    padding-right: 20px;
    color: #333;
}

td {
    text-align: center;
}

.center-form {
	margin: 200px auto 0;
	text-align: center;
}

input[type="text"], input[type="password"], input[type="button"], input[type="submit"]
	{
	width: 80%;
	padding: 10px;
	margin: 5px 0;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
	text-align: center;
}

input[type="button"], input[type="submit"] {
	background-color: #007BFF;
	color: white;
	border: none;
	cursor: pointer;
	text-align: center;
}

input[type="button"]:hover, input[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
<%@ include file="./header.jsp"%>
<body>
	<form action="./joinMember.do" method="post">
		<table>
			<tr>
				<th style="text-align: center;">아이디</th>
				<td>
				<input type="text" title="n" name="id" placeholder="ID">
				<!-- <input type="button" value="중복체크" onclick="idCheck()"> -->
				</td>
			</tr>
			<tr>
				<th style="text-align: center;">비밀번호</th>
				<td><input type="text" name="password" placeholder="PASSWORD"></td>
			</tr>
			<tr>
				<th style="text-align: center;">성명</th>
				<td><input type="text" name="name" placeholder="홍길동"></td>
			</tr>
			<tr>
				<th style="text-align: center;">전화번호</th>
				<td><input type="text" name="phone" placeholder="01012341234(숫자만 입력)"></td>
			</tr>
			<tr>
				<th colspan="2" style="text-align: center;">
				<input type="submit" value="회원가입"></th>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
</body>
<%@ include file="./footer.jsp"%>
</html>