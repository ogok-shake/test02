<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*"%>    
<%@ include file="/layout/head.jsp" %>

<%
	
request.setCharacterEncoding("utf-8");
int rst=0;
String id=(String)request.getParameter("id");
MemberImpl daocheck=MemberImpl.getInstance();
rst=daocheck.idCheck(id);

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복 확인</title>

<style type="text/css">
	form{
		text-align:center;
	}

</style>

<script type="text/javascript">
	function truefalsecheck(){
		if(<%=rst%>==1){
			opener.document.join.id.value="";
			opener.document.join.truefalse.value="idfalse";
		}else if(<%=rst%>==-1){
			opener.document.join.truefalse.value="idtrue";
		}
		window.close();
	}//

</script>
</head>

<body onunload="truefalsecheck()">
		<form name="yn">
		<%if(rst==-1){%>
			<div align="center">
			<br>
			<h3>사용 가능한 아이디 입니다.</h3>
			<br>
			<input type="button" value="아이디 사용" onClick="truefalsecheck()">
			</div>
		<%}else if(rst==1){%>

			<div align="center">
			<br>
			<h3>해당 아이디가 이미 존재 합니다.</h3> 
			<h4>새로운 아이디를 입력해주세요.</h4>
			<br>
			<input type="button" value="확인" onClick="truefalsecheck()">
			</div>
		<%}%>
		</form>

</body>
</html>