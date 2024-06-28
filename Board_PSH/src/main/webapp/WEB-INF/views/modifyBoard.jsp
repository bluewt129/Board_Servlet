<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
<link rel="stylesheet" href="./css/writeForm.css">
</head>
<%@ include file="./header.jsp"%>
<body>
	<div id="container">
		<form action="modifyBoard.do" method="post">
			<input type="hidden" name="seq" value="${dto.seq}">
			<table class="table table-condensed">
				<tr>
					<th class="info">아이디</th>
					<td>${dto.id}</td>
				</tr>
				<tr>
					<th class="info">제목</th>
					<td>${dto.title}</td>
				</tr>
				<tr>
					<th class="info">내용</th>
					<td><textarea rows="5" cols="form-control" name="content"
							id="content">${dto.content}</textarea></td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;">
					<input type="submit" class="btn btn-primary" value="수정완료">
					<input type="button" class="btn btn-danger" value="뒤로가기" onclick="javascript:history.back(-1)">
				</tr>
			</table>
		</form>
	</div>
</body>
<%@ include file="./footer.jsp"%>
</html>