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
			<form action="./adminPage.do" method="post">
				<h3>가입 승인 대기 명단</h3>
				<table class="table table-hover">
					<thead>
						<tr class="info">
							<th><input type="checkbox" id="chkbox" class="allCheckBox"
								onclick="checkAll(this.checked)"></th>
							<th>ID</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>권한</th>
							<th>가입상태</th>
							<th>승인여부</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(lists) eq 0}">
							<tr>
								<td colspan="7"
									style="color: blue; font-size: 8px; text-align: center;">
									-- 대기중인 인원이 없습니다 --</td>
							</tr>
						</c:if>

						<c:if test="${fn:length(lists) ne 0}">
							<c:set var="size" value="${fn:length(lists)}" />
							<c:forEach var="dto" items="${lists}" varStatus="vs">
								<tr>
									<th><input type="checkbox" name="ch" class="ch"
										value="${dto.id}"></th>
									<td>${dto.id}</td>
									<td>${dto.name}</td>
									<td>${dto.phone}</td>
									<td>${dto.auth}</td>
									<td>${dto.enable}</td>
									<td><select name="access">
											<option value="D">가입승인</option>
											<option value="F">가입거절</option>
									</select></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3" style="text-align: center;">
							<input
								type="button" class="btn btn-primary" value="모든 회원 보기"
								onclick="location.href='./allMemberInfo.do'">
								</td>
							<td colspan="2" style="text-align: center;"><input
								type="button" class="btn btn-info" value="상태 저장"
								onclick="chacSubmit(event)"></td>
							<td colspan="2" style="text-align: center;">
						<input type="button" class="btn btn-danger" value="뒤로가기" onclick="javascript:history.back(-1)">
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>
</body>
<%@ include file="./footer.jsp"%>
<script type="text/javascript">
function chacSubmit(event) {
	event.preventDefault();
	var cnt = chCheckedCount();
	if (cnt > 0) {
		Swal.fire({
			title: "상태 저장",
			text: "승인 허가 및 취소",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "확인"
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire({
					title: "확인",
					text: "상태가 업데이트 되었습니다",
					icon: "success"
				}).then(() => {
					submitForm();
				});
			}
		});
	} else {
		Swal.fire("선택된 아이디가 없습니다");
	}
}

function allMemberInfo() {
	location.href = "./writeBoard.do";
}
</script>
</html>
