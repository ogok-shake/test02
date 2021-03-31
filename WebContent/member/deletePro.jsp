<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${check==1}">
<c:remove var="memId" scope="session"/>
<table width="270" cellpadding="5" align="center">
<tr>
<td align="center"><font size="+1"><b>회원정보가 삭제되었습니다.</b></font></td>
</tr>
<tr>
<td align="center">
<p>5초 후에 메인페이지로 이동합니다.</p>

</td>
</tr>
<tr>
<td align="center">
<input type="button" value="메인으로 " onClick="location.href='${ctxpath}/board/main.do'">
</td>
</tr>
</table>
<meta http-equiv="Refresh" content="5;url=${ctxpath}/board/main.do">
</c:if>
<c:if test="${check==-1}">
<script>
alert("암호 틀림");
history.back();
</script>
</c:if>

</body>
</html>