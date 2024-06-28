<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<link rel="stylesheet" href="./css/writeForm.css">
</head>
<%@ include file="./header.jsp"%>
<body>
	<div>
	<form action="./writeBoard.do" method="post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="id" value="${loginDto.id}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="50" name="content" placeholder="내용을 입력해주세요"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="3" style="text-align: center;">
					<input type="submit" class="btn btn-primary" value="글 등록">
				</th>
			</tr>
		</table>
		</form>
	</div>
</body>
<%@ include file="./footer.jsp"%>
</html>