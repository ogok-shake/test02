<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function begin(){
	document.myForm.pw.focus();
	
}

function check(){
	if(!document.myform.pw.value){
		alert("암호를 입력하세요.");
		document.myForm.pw.focus();
		return;
	}
	
	document.myform.submit();
}
</script>
</head>
<body onLoad="begin()">
<form name="myForm" method="post" action="${ctxpath}/member/deletePro.do">
<table width="260" align="center">
	<tr>
		<td colspan="2" valign="middle" align="center">
			<font size="+1"><b>회원 탈퇴</b></font>
		</td>
	</tr>
	<tr>
		<td>암호</td>
		<td>
			<input type="password" name="pw" id="pw" size="15">
			<input type="hidden" name="id" id="id" value="${sessionScope.memId}">			
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="회원탈퇴" onClick="var c=confirm('계정을 삭제 시 해당 계정으로 업로드한 게시물이 전부 삭제 됩니다.\n 정말 삭제하시겠습니까?');if(c==true){check();}else{return false;}">
			<input type="button" value="취소" onClick="location.href='${ctxpath}/board/main.do'">
		</td>
	</tr>
	
</table>

</form>
</body>
</html>