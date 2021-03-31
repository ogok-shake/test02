<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>apple the clam</title>

</head>
<body>
		<nav id="nav">
		<jsp:include page="/layout/nav.jsp" flush="false"></jsp:include>
		</nav>
		<header id="header">
			<jsp:include page="/layout/header.jsp" flush="false"></jsp:include>
		</header>
		<nav id="nav2">
		<jsp:include page="/layout/nav2.jsp" flush="false"></jsp:include>
		</nav>
		<section id="section">
			<jsp:include page="${CONTENT}" flush="false"></jsp:include>
		</section>
		<footer id="footer">
			<jsp:include page="/layout/footer.jsp" flush="false"></jsp:include>
		</footer>

</body>
</html>