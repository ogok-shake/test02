<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctxpath}/css/nav_menu.css" rel="stylesheet" type="text/css"> 
</head>
<body>

		<table class="nav_menu" align="center">
			<tr>
				<td><a href="${ctxpath}/board/list.do">gallery</a></td>
				<td><a href="${ctxpath}/album/list.do">album</a></td>
				<td><a href="${ctxpath}/board/*.do">pop sockets</a></td>
				<td><a href="${ctxpath}/board/*.do">Q&A</a></td>
				<td><a href="${ctxpath}/board/*.do">review</a></td>
				<td><a href="${ctxpath}/board/*.do">notice</a></td>
			</tr>	
		</table>


</body>
</html>