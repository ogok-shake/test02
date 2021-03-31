<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>

<c:if test="${check==1}">
<meta http-equiv="refresh" content="0;url=${ctxpath}/board/list.do?pageNum=${pageNum}">
</c:if>

<c:if test="${check==0}">
<center>글 삭제에 실패했습니다.</center>
<a href="javascript:history.go(-1)">[이전으로]</a>
</c:if>