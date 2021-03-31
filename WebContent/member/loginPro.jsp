<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp"%>

<c:if test="${check==1}">
	<c:set var="memId" value="${id}" scope="session"/>
	<meta http-equiv="Refresh" content="0;url=${ctxpath}/board/main.do">

</c:if>
<c:if test="${check==0}">
	<script>
	alert("암호가 틀립니다.");
	history.back();
	</script>
</c:if>
<c:if test="${check==-1}">
	<script>
	alert("존재하지 않는 아이디입니다.");
	history.back();
	</script>
</c:if>