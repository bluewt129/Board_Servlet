<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인기글 TOP 10</title>
<style type="text/css">
table, tr, td {
	border: 1px solid black;
	border-collapse: collapse;
}

.center-form {
	margin: 0 auto;
	width: 400px;
}
</style>
</head>
<%@ include file="./header.jsp"%>
<body>
	<div id="container" class="container">
		<h3>인기글 TOP 10</h3>
		<table class="table table-hover">
			<thead>
				<tr class="info">
					<th>등수</th>
					<th>작성자</th>
					<th>제목</th>
					<th>추천수</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(lists) ne 0}">
					<c:set var="size" value="${fn:length(lists)}" />
					<c:forEach var="dto" items="${lists}" varStatus="vs">
						<tr>
							<td>${vs.index + 1}</td>
							<td>${dto.id}</td>
							<td>
								<a href="./detailBoard.do?seq=${dto.seq}">${dto.title}</a>
							</td>
							<td>${dto.goodcount}</td>
							<td>${dto.readcount}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="btn-group">
			<button class="btn btn-danger" onclick="location.href='./boardList.do'">전체 게시글</button>
		</div>
	</div>
</body>
<%@ include file="./footer.jsp"%>
</html>
