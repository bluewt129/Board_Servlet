<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
.profile-container {
	text-align: center;
	margin-bottom: 20px;
}

.profile-pic {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	object-fit: cover;
}
</style>
</head>
<%@ include file="./header.jsp"%>
<body>
	<div id="container" class="container" style="height: 110vh;">
	
		<div class="profile-container">
			<c:choose>
				<c:when test="${dto.profile_img ne null}">
					<c:forEach var="file" items="${dto.profile_img}">
						<img src="./upload/${dto.profile_img}" alt="Profile Image">
					</c:forEach>
				</c:when>
				<c:otherwise>
					<img src="./img/default.png" alt="프로필 사진">
				</c:otherwise>
			</c:choose>
		</div>
		<form action="./profileImg.do" method="post"
			enctype="multipart/form-data">
			<table class="table table-condensed">
				<tr>
					<td><input type="file" name="filename" id="imgFile"></td>
				</tr>
				<tr>
					<td><input type="submit" value="사진 등록" class="btn btn-primary"></td>
				</tr>
			</table>
		</form>
		<div>
			<form action="./myPage.do" method="get">
				<input type="hidden" name="id" value="${dto.id}">
				<h3>나의 정보 조회</h3>
				<table class="table table-hover">
					<thead>
						<tr class="info">
							<th>ID</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>내글 보기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${dto.id}</td>
							<td>${dto.name}</td>
							<td>${dto.phone}</td>
							<td><input type="button" class="btn btn-success" name="myboard"
								onclick="myBoard(event)" value="내글 보기"></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align: center;"><input
								type="button" class="btn btn-info" value="뒤로가기"
								onclick="javascript:history.back(-1)">
						</tr>
					</tfoot>
				</table>
			</form>
			<form action="./deleteMember.do" method="post"  style="text-align: center;">
				<input type="hidden" name="id" value="${dto.id}"> <input
					type="button" class="btn btn-danger" value="회원탈퇴"
					onclick="submit()">
			</form>
		</div>
	</div>
</body>
<%@ include file="./footer.jsp"%>
<script type="text/javascript">
	function myBoard(event) {
		event.preventDefault();
		var id = document.querySelector("input[name=id]").value;
		location.href = "./myBoard.do?myboard=true&id=" + id;
	}
	
</script>
</html>