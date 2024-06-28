<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>

<link rel="stylesheet" href="./css/reply.css">

</head>
<%@ include file="./header.jsp"%>
<body>
	<c:set var="dto" value="${dto}" />
	<c:set var="rdto" value="${rdto}" />
	<div id="container">
		<h2>${dto.seq}게시글상세</h2>
		<table class="boardTable">
			<tbody>
				<tr>
					<td class=info><b>글번호</b></td>
					<td>${dto.seq}</td>
				</tr>
				<tr>
					<td class=info><b>아이디</b></td>
					<td>${dto.id}</td>
				</tr>
				<tr>
					<td class=info><b>제목</b></td>
					<td>${dto.title}(${replyCount})</td>
				</tr>
				<tr>
					<td class=info><b>조회수</b></td>
					<td>${dto.readcount}</td>
				</tr>
				<tr>
					<td class=info><b>내용</b></td>
					<td><textarea rows="5" cols="40" readonly="readonly">${dto.content}</textarea></td>
				</tr>
				<tr>
					<td class=info><b>등록일</b></td>
					<td>${dto.createat}</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><c:if
							test="${loginDto == null}">
							<button class="btn btn-primary"
								onclick="location.href='./login.do'">로그인 후 추천</button>
							<span class="goodcount"><b>추천수 ${dto.goodcount}</b></span>
						</c:if> <c:if test="${loginDto != null}">
							<button class="btn btn-primary" id="goodUpdate">추천</button>
							<span class="goodcount"><b>추천수 ${dto.goodcount}</b></span>
						</c:if></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" style="text-align: center;">
						<form class="btn-group btn-group-justified">
							<input type="hidden" name="seq" value="${dto.seq}">
							<c:if test="${loginDto.auth eq 'ADMIN'}">
								<div class="btn-group">
									<button class="btn btn-danger" onclick="del(event)">관리자
										전용 삭제</button>
								</div>
							</c:if>
							<c:if test="${loginDto.id eq dto.id}">
								<div class="btn-group">
									<button class="btn btn-info" onclick="del(event)">내글
										삭제</button>
								</div>
							</c:if>
							<c:if test="${loginDto.id eq dto.id}">
								<div class="btn-group">
									<button class="btn btn-primary" onclick="modify(event)">내글
										수정</button>
								</div>
							</c:if>
							<c:if test="${loginDto.id eq null}">
								<div class="btn-group">
									<button class="btn btn-primary"
										onclick="location.href='./login.do'">재로그인 하세요</button>
								</div>
							</c:if>
						</form>
					</td>
				</tr>
			</tfoot>
		</table>
		<c:if test="${loginDto != null}">
			<div class="btn-group">
				<form action="./boardList.do" method="get">
					<button type="submit" class="btn btn-info">목록</button>
				</form>
			</div>
		</c:if>
		<br> <br>
		<form action="./replyBoard.do" method="post"
			style="text-align: center;">
			<input type="hidden" name="post_id" value="${dto.seq}"> <input
				type="hidden" name="user_id" value="${loginDto.id}">
			<textarea name="rcontent" rows="4" cols="100" id="rcon"
				placeholder="댓글을 입력하세요"></textarea>
			<br>
			<button type="submit" class="btn btn-success" onclick="reply(event)">댓글
				작성</button>
		</form>
		<c:choose>
			<c:when test="${rdto ne null}">
				<h3>댓글 목록</h3>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<c:forEach var="replyB" items="${replyB}" varStatus="vs">
			<table class="replyBest">
				<caption style="color: red;">
					<b>*Best 댓글*</b>
				</caption>
				<tbody>
					<c:choose>
						<c:when test="${replyB ne null}">
							<tr>
								<td class="replyHeader">&nbsp;&nbsp;&nbsp;<b>${replyB.user_id}</b>
									&nbsp;: &nbsp;${replyB.regdate} &nbsp;&nbsp;|&nbsp;&nbsp; <span
									class="regoodcount"><b>추천수 ${replyB.regoodcount}</b></span> <input
									type="hidden" name="rseq" value="${replyB.seq}">
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;${replyB.rcontent}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="replyHeader">-----Best 댓글이 없습니다-----</td>
							<tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</c:forEach>
		<br>
		<c:forEach var="reply" items="${rdto}" varStatus="vs">
			<table class="replyList">
				<tbody>
					<tr>
						<td class="replyHeader">&nbsp;&nbsp;&nbsp;<b>${reply.user_id}</b>
							&nbsp;: &nbsp;${reply.regdate} &nbsp;&nbsp;|&nbsp;&nbsp; <span
							class="regoodcount"><b>추천수 ${reply.regoodcount}</b></span> <input
							type="hidden" name="rseq" value="${reply.seq}">
							<button class="btn btn-primary replygoodcount" id="regoodUpdate">추천</button>
						</td>
					</tr>
					<tr>
						<td class="replyBody">&nbsp;&nbsp;&nbsp;${reply.rcontent} <c:choose>
								<c:when test="${loginDto.id eq reply.user_id}">
									<input type="hidden" name="rseq" value="${reply.seq}">
									<input type="hidden" name="post_id" value="${reply.post_id}">
									<button class="btn btn-danger delreply"
										onclick="delreply(event)">삭제</button>
									<button class="btn btn-success modifyreply"
										onclick="modifyreply(event)">수정</button>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
			<br>
		</c:forEach>
	</div>

</body>
<script type="text/javascript">
	function modify(event) {
		event.preventDefault();
		var frm = document.forms[0];
		var seq = document.querySelector("input[name=seq]").value;
		frm.action = "./modifyBoard.do?seq=" + seq;
		frm.method = "get";
		frm.submit();
	}
	function modifyreply(event) {
		event.preventDefault();
		console.log("수정");
	}
</script>

<%@ include file="./footer.jsp"%>
</html>
