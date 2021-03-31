<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp"%>
<c:if test="${check==1}">
<meta http-equiv="refresh" content="0;url=${ctxpath}/album/list.do?pageNum=${pageNum}">
</c:if>
<c:if test="${check==0}">
<script>
alert("게시글 수정 중 문제가 발생했습니다.");
</script>
<meta http-equiv="refresh" content="0;url=${ctxpath}/album/list.do?pageNum=${pageNum}">
</c:if>