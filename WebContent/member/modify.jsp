<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center" height="200">
	<tr>
		<td>
			<form name="modifyForm" method="post" action="${ctxpath}/member/modifyForm.do">
				<input type="hidden" name="id" id="id" value="${sessionScope.memId}">
				<input type="submit" value="내 정보 수정">
			</form>
		</td>
		<td>
			<form name="deleteForm" method="post" action="${ctxpath}/member/deleteForm.do">
				<input type="hidden" name="id" id="id" value="${sessionScope.memId}">
				<input type="submit" value="회원탈퇴">
			</form>
		
		</td>
	</tr>
</table>
</body>
</html>