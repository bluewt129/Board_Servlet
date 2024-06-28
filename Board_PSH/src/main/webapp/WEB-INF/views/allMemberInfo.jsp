<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style type="text/css">
#container {
	width: 800px;
	margin: 50px auto;
	height: 120vh;
}
</style>
</head>
<%@ include file="./header.jsp"%>
<body>
	<div id="container" class="container" style="height: auto;">
		<div>
			<h3>모든 회원 리스트</h3>
			<form action="./allMemberInfo.do" method="post">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>권한</th>
							<th><select name="enabletype" id="enabletype"
								onchange="this.form.submit()">
									<option value="A"
										<c:if test="${param.enabletype == 'A'}">selected</c:if>>전체보기</option>
									<option value="D"
										<c:if test="${param.enabletype == 'D'}">selected</c:if>>대기회원</option>
									<option value="Y"
										<c:if test="${param.enabletype == 'Y'}">selected</c:if>>활동회원</option>
									<option value="N"
										<c:if test="${param.enabletype == 'N'}">selected</c:if>>탈퇴회원</option>
									<option value="F"
										<c:if test="${param.enabletype == 'F'}">selected</c:if>>거절회원</option>
							</select></th>
						</tr>
					</thead>
					<tbody id="userList">
						<c:if test="${fn:length(alluser) eq 0}">
							<tr>
								<td colspan="5"
									style="color: blue; font-size: 8px; text-align: center;">--
									가입 인원이 없습니다 --</td>
							</tr>
						</c:if>

						<c:if test="${fn:length(alluser) ne 0}">
							<c:set var="size" value="${fn:length(alluser)}" />
							<c:forEach var="user" items="${alluser}" varStatus="vs">

								<c:choose>
									<c:when test="${user.auth eq 'D'}">
										<tr>
											<td>${user.id}</td>
											<td>${user.name}</td>
											<td>${user.phone}</td>
											<td>${user.auth}</td>
											<td style="color: blue;"><b>${user.enable}</b></td>
										</tr>
									</c:when>
									<c:when test="${user.auth eq 'Y'}">
										<tr>
											<td>${user.id}</td>
											<td>${user.name}</td>
											<td>${user.phone}</td>
											<td>${user.auth}</td>
											<td style="color: green;"><b>${user.enable}</b></td>
										</tr>
									</c:when>
									<c:when test="${user.auth eq 'F'}">
										<tr>
											<td>${user.id}</td>
											<td>${user.name}</td>
											<td>${user.phone}</td>
											<td>${user.auth}</td>
											<td style="color: red;"><b>${user.enable}</b></td>
										</tr>
									</c:when>
									<c:when test="${user.auth eq 'N'}">
										<tr>
											<td>${user.id}</td>
											<td>${user.name}</td>
											<td>${user.phone}</td>
											<td>${user.auth}</td>
											<td style="color: orange;"><b>${user.enable}</b></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>${user.id}</td>
											<td>${user.name}</td>
											<td>${user.phone}</td>
											<td>${user.auth}</td>
											<td style="color: blue;"><b>${user.enable}</b></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="7" style="text-align: center;"><input
							type="button" class="btn btn-danger" value="뒤로가기"
							onclick="javascript:history.back(-1)"></td>
							</tr>
					</tfoot>
				</table>
			</form>
		</div>
		<br>
		<br>
		<br>
		<br>
	</div>
</body>
<%@ include file="./footer.jsp"%>

</html>
