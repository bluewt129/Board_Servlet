<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
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
		<form action="./multiDelete.do" method="post"
			onsubmit="return chkSubmit()">
			<div>TOTAL ${page.totalCount}</div>
			<table class="table table-hover">
				<thead>
					<tr class="info">
						<c:choose>
								<c:when test="${loginDto.auth eq 'ADMIN'}">
									<th>
									<input type="checkbox" id="chkbox" class="allCheckBox"
										onclick="checkAll(this.checked)">
										</th>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						<th>연번</th>
						<th>작성자</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(lists) eq 0}">
						<tr>
							<td colspan="5"
								style="color: blue; font-size: 8px; text-align: center;">
								-- 작성된 글이 없습니다 --</td>
						</tr>
					</c:if>

					<c:if test="${fn:length(lists) ne 0}">
						<c:set var="size" value="${fn:length(lists)}" />
						<c:forEach var="dto" items="${lists}" varStatus="vs">
							<tr>
								<c:choose>
										<c:when test="${loginDto.auth eq 'ADMIN'}">
											<th>
											<input type="checkbox" name="ch" class="ch"
												value="${dto.seq}">
												</th>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								<td>${dto.seq}</td>
								<td>${dto.id}</td>
								<td><c:choose>
										<c:when test="${dto.delflag eq 'y'}">
											<span style="font-size: 8px; color: #ccc;">관리자에 의해서
												삭제되었습니다</span>
										</c:when>
										<c:otherwise>
											<a href="./detailBoard.do?seq=${dto.seq}">${dto.title}</a>
										</c:otherwise>
									</c:choose></td>
								<td><fmt:parseDate var="cre" value="${dto.createat}"
										pattern="yyyy-MM-dd" /> <fmt:formatDate value="${cre}"
										pattern="yyyy년 MM월 dd일" /></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="text-align: center;">
							<c:choose>
								<c:when test="${loginDto.auth eq 'ADMIN'}">
									<input type="button" class="btn btn-info" value="관리자 전용 다중 삭제"
										onclick="chSubmit(event)">
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose> <input type="button" class="btn btn-primary" value="새글입력"
							onclick="location.href='./writeBoard.do'"></td>
					</tr>
				</tfoot>
			</table>
		</form>
		<div style="font-size: 20px; text-align: center;">
			<ul class="pagination pagination-lg">
				<c:if test="${page.page > page.countPage}">
					<li><a href="./myBoard.do?myboard=true&id=${loginDto.id}&page=1">&lt;&lt;</a></li>
				</c:if>

				<c:if test="${page.page > 1}">
					<c:choose>
						<c:when test="${(page.stagePage-page.countPage)<0}">
							<li><a href="./myBoard.do?myboard=true&id=${loginDto.id}&page=${page.page - 1}">&lt;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="./myBoard.do?myboard=true&id=${loginDto.id}&page=${page.stagePage - page.countPage}">&lt;</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
					<li ${i==page.page?"class='active'":""}><a href="./myBoard.do?myboard=true&id=${loginDto.id}&page=${i}">${i}</a></li>
				</c:forEach>
				<fmt:parseNumber var="num1" integerOnly="true" value="${(page.totalPage-1) / page.countPage}" />
				<fmt:parseNumber var="num2" integerOnly="true" value="${(page.page-1) / page.countPage}" />

				<c:if test="${num1 > num2}">
					<li><a href="./myBoard.do?myboard=true&id=${loginDto.id}&page=${page.stagePage + page.countPage}">&gt;</a></li>
				</c:if>
				<c:if test="${page.endPage < page.totalPage}">
					<li><a href="./myBoard.do?myboard=true&id=${loginDto.id}&page=${page.totalPage}">&gt;&gt;</a></li>
				</c:if>

			</ul>
		</div>
	</div>
</body>
<%@ include file="./footer.jsp"%>
</html>
