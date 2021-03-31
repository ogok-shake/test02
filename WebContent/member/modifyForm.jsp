<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

	function openDaumPostcode(){
	   new daum.Postcode({
	      oncomplete:function(data){
	         document.getElementById('zipcode').value=data.zonecode;
	         document.getElementById('addr').value=data.address;
	       }
	   }).open();
	}//openDaumPostcode()---

</script>
<script type="text/javascript">

function check(){	
	
	if(document.userForm.pw.value==''){
		alert("비밀번호를 입력하세요.");
		document.userForm.pw.focus();
		return false;
	}
	
	if(document.userForm.pw2.value==''){
		alert("비밀번호 확인을 입력하세요.");
		document.userForm.pw2.focus();
		return false;
	}

	if(document.userForm.name.value==''){
		alert("이름을 입력하세요.");
		document.userForm.name.focus();
		return false;
	}

	if(document.userForm.email.value==''){
		alert("이메일을 입력하세요.");
		document.userForm.email.focus();
		return false;
	}
	
	if(document.userForm.hp1.value==''){
		alert("연락처를 입력하세요.");
		document.userForm.hp1.focus();
		return false;
	}
	if(document.userForm.hp2.value==''){
		alert("연락처를 입력하세요.");
		document.userForm.hp2.focus();
		return false;
	}
	if(document.userForm.hp3.value==''){
		
		alert("연락처를 입력하세요.");
		document.userForm.hp3.focus();
		return false;
	}
	
	if(document.userForm.zipcode.value==''){
		alert("주소를 입력하세요.");
		document.userForm.zipcode.focus();
		return false;
	}
	if(document.userForm.addr.value==''){
		alert("주소를 입력하세요.");
		document.userForm.addr.focus();
		return false;
	}
	if(document.userForm.addr2.value==''){
		alert("상세 주소를 입력하세요.");
		document.userForm.addr2.focus();
		return false;
	}

	
	return true;

}//check() end

function pwdcheck(){
	pw=document.userForm.pw.value;
	pw2=document.userForm.pw2.value;
	if(pw!=pw2){
		alert("암호와 암호 확인이 다릅니다.");
		document.userForm.pw.value="";
		document.userForm.pw2.value="";
		document.userForm.pw.focus();
		return;
	}
}

</script>

</head>
<body>
<form name="userForm" method="post" action="${ctxpath}/member/modifyPro.do" onSubmit="return check()">
	<table width="600" cellspacing="0" cellpadding="3" align="center">
		<tr>
			<td colspan="2" align="center">
				<font size="5"><b>내정보 수정</b></font>
			</td>
 		</tr>
 		<tr>
 			<td>ID&nbsp;</td>
 			<td>
 			${dto.id}
 				<input type="hidden" name="id" id="id" value="${dto.id}">
 			</td>
 		</tr>
 		<tr>
 			<td>암호&nbsp;</td>
 			<td><input type="password" name="pw" id="pw" size="10"></td>
 		</tr>
 		<tr>
 			<td>암호확인&nbsp;</td>
 			<td><input type="password" name="pw2" id="pw2" size="10" onblur="pwdcheck()"></td>
 		</tr>
 		
 		<tr>
 			<td>이름&nbsp;</td>
 			<td><input type="text" name="name" id="name" value="${dto.name}" size="30"></td>
 		</tr>
		<tr>
			<td><label for='birth'>생년월일</label></td>
			<td>
			<input type="text" name="birth" id="birth" size="30" value="${dto.birth}" placeholder="ex)990101">
			</td>
		</tr>
 		<tr>
 			<td>이메일&nbsp;</td>
 			<td><input type="text" name="email" id="email" size="30" value="${dto.email}">
 		
 			</td>
 		</tr>
 		
 		<tr>
			<td><label for='hp1'>연락처</label></td>
			<td>
				<select name="hp1" id="hp1">
					<option selected value="${hp1}">${hp1}</option>
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="018">018</option>
				</select> -
				<input type="text" name="hp2" id="hp2" size="4" value="${hp2}" onKeyUp="if(this.value.length==4) userForm.hp3.focus();"> -
				<input type="text" name="hp3" id="hp3" size="4" value="${hp3}" onKeyUp="if(this.value.length==4) userForm.zipcode.focus();">
			</td>
		</tr>	
 		
 		<tr>
 			<td>우편번호&nbsp;</td>
 			<td><input type="text" name="zipcode" id="zipcode" size="7" value="${dto.zipcode}" readonly>
 			<input type="button" value="주소검색" onClick="openDaumPostcode()">
 			</td>
 		</tr>
 		<tr>
 			<td>주소&nbsp;</td>
 			<td><input type="text" name="addr" id="addr" size="50" value="${dto.addr}" readonly><br>
 			상세주소 : <input type="text" name="addr2" id="addr2" size="40" value="${dto.addr2}">
 			</td>
 		</tr>
 		<tr>
 			<td colspan="2" align="center">
				<input type="submit" value="내정보 수정">
				<input type="button" value="취소" onClick="location.href='${ctxpath}/board/main.do'">
			</td>
 			
 		</tr>
 		
 		
	</table>
</form>
</body>
</html>