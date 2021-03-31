<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctxpath}/css/nav_nav.css" rel="stylesheet" type="text/css"> 
</head>
<body>

<nav class="nav_nav">
	<%
	String id=(String)session.getAttribute("memId");
	%>
		<ul>
			<li><a href="${ctxpath}/board/main.do">Home</a></li>
	<%
		if(id==null||id.equals("")){//회원/비회원
	%>
			
			<li><a href="${ctxpath}/member/loginForm.do">로그인</a></li>
			<li><a href="${ctxpath}/member/inputForm.do">회원가입</a></li>
			
	<%
		}else if(id.equals("admin")){//관리자
	%>			
			<li><a href="${ctxpath}/member/logout.do">로그아웃</a></li>	
			<li><a href="${ctxpath}/member/modify.do">마이페이지</a></li>	
			<li><a href="${ctxpath}/admin/index.do">관리자 페이지</a></li>	
	<%
		}else{//회원
	%>	
			<li><a href="${ctxpath}/member/logout.do">로그아웃</a></li>	
			<li><a href="${ctxpath}/member/modify.do">마이페이지</a></li>	
	<%
		}	
	%>		
		</ul>
</nav>	

</body>
</html>