<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{height:30%;padding-top:5%;}
	fieldset{width:400px;}
	form input[type=button]{padding:15px;}
	#label{text-align:center;}
</style>
<script>
	function idfocus(){
		document.login.id.focus();
		}
</script>
<script type="text/javascript">
	function check(){
		if(document.login.id.value==''){
			alert("ID를 입력해주세요.");
			document.login.id.focus();
			return;
		}
		if(document.login.pw.value==''){
			alert("비밀번호를 입력해주세요.");
			document.login.pw.focus();
			return;
		}
		
		document.login.submit();
	}//login()
	
</script>
</head>
<body onLoad="idfocus()">

	<form name="login" method="post" action="${ctxpath}/member/loginPro.do">
		<div align="center">
			
			<fieldset>
				<table>

					<legend>로그인</legend>
					<tr>
						<td id="label"><label for="id">ID</label></td>
						<td><input type="text" name="id" id="id" size="20" placeholder="아이디 입력"></td>
						<td rowspan="2" align="center">
							<input type="button" value="로그인" onClick="check()">
						</td>
					</tr>
					<tr>
						<td id="label"><label for="pw">비밀번호</label></td>
						<td><input type="password" name="pw" id="pw" size="20" placeholder="비밀번호 입력"></td>
					</tr>


				</table>
			</fieldset>
		</div>

	</form>

</body>
</html>