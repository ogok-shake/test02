<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		
		<script>
		function openDaumPostcode(){
			new daum.Postcode({
				oncomplete:function(data){
					document.getElementById('zipcode').value=data.zonecode;
					document.getElementById('addr').value=data.address;
				}
			}).open();
		}//openDaumPostcode end
		
		</script>
		
		<script type="text/javascript">
			function idcheck(){
				if(document.getElementById('id').value==''){
					alert("ID를 입력하세요.");
					document.getElementById('id').focus();
					return;	
				}//if
				var id=document.getElementById('id').value;
				var url="join_idcheck.jsp?id="+id;
				window.open(url,"post","height=180,width=360");
				
			}//idcheck
		
			function idfocus(){
				document.join.id.focus();
			}
			
			function check(){

				if(document.join.id.value==''){
					alert("아이디를 입력하세요.");
					document.join.id.focus();
					return;
				}
				
				if(document.join.truefalse.value!='idtrue'){
					alert("아이디 중복체크를 하세요.");
					document.join.id.value="";
					document.join.id.focus();
					return;
				}	
				
				if(document.join.pw.value==''){
					alert("비밀번호를 입력하세요.");
					document.join.pw.focus();
					return;
				}
				
				if(document.join.pw2.value==''){
					alert("비밀번호 확인을 입력하세요.");
					document.join.pw2.focus();
					return;
				}

				if(document.join.name.value==''){
					alert("이름을 입력하세요.");
					document.join.name.focus();
					return;
				}

				if(document.join.email.value==''){
					alert("이메일을 입력하세요.");
					document.join.email.focus();
					return;
				}
				
				if(document.join.hp1.value=='번호 선택'){
					alert("연락처를 입력하세요.");
					document.join.hp1.focus();
					return;
				}
				if(document.join.hp2.value==''){
					alert("연락처를 입력하세요.");
					document.join.hp2.focus();
					return;
				}
				if(document.join.hp3.value==''){
					
					alert("연락처를 입력하세요.");
					document.join.hp3.focus();
					return;
				}
				
				if(document.join.addr.value==''){
					alert("주소를 입력하세요.");
					document.join.addr.focus();
					return;
				}
				if(document.join.addr2.value==''){
					alert("상세 주소를 입력하세요.");
					document.join.addr2.focus();
					return;
				}
			
				
				document.join.submit();//서버 전송
		
			}//check() end
			
			function pwdcheck(){
				pw=document.join.pw.value;
				pw2=document.join.pw2.value;
				if(pw!=pw2){
					alert("암호와 암호 확인이 다릅니다.");
					document.join.pw.value="";
					document.join.pw2.value="";
					document.join.pw.focus();
					return;
				}
			}
			
			function inputIdChk(){
				document.join.truefalse.value="idfalse";
			}

			
		</script>
		
</head>
<body onload="inputIdChk()">
	<form name="join" method="post" action="${ctxpath}/member/inputPro.do">
			<table align="center">
				
				<tr>
					<td colspan="2" align="center">
						<font size="5">회원 가입</font>
					</td>
				</tr>
				<tr>

						<td><label for='id'>ID</label></td>
						<td>
							<input type="text" name="id" id="id" size="30" placeholder="아이디 입력" onKeyDown="inputIdChk()">
							<input type="button" value="중복 체크" onClick="idcheck()">
							<input type="hidden" id="truefalse" name="truefalse" value="idfalse">
						</td>

				</tr>
				<tr>
					<td><label for='pw'>비밀번호</label></td>
					<td>
						<input type="password" name="pw" id="pw" size="14" placeholder="암호 입력">
					</td>
				</tr>
				<tr>
					<td><label for='pw2'>비밀번호 확인</label></td>
					<td>
						<input type="password" name="pw2" id="pw2" size="14" placeholder="암호 확인 입력" onblur="pwdcheck()">
					</td>
				</tr>				
				<tr>
					<td><label for='name'>이름</label></td>
					<td>
						<input type="text" name="name" id="name" size="15" placeholder="이름 입력">
					</td>
				</tr>
				<tr>
					<td><label for='birth'>생년월일</label></td>
					<td>
						<input type="text" name="birth" id="birth" size="30" placeholder="ex)990101">
					</td>
				</tr>
				<tr>
					<td><label for='email'>이메일</label></td>
					<td>
						<input type="text" name="email" id="email" size="30" placeholder="이메일  입력">
					</td>
				</tr>
				<tr>
					<td><label for='hp1'>연락처</label></td>
					<td>
						<select name="hp1" id="hp1">
							<option selected>번호 선택
							<option value="010">010
							<option value="011">011
							<option value="016">016
							<option value="017">017
							<option value="018">018
						</select> -
						<input type="text" name="hp2" id="hp2" size="4" onKeyUp="if(this.value.length==4) join.hp3.focus();"> -
						<input type="text" name="hp3" id="hp3" size="4" onKeyUp="if(this.value.length==4) join.zipcode.focus();">
					</td>
				</tr>								
				<tr>
					<td><label for='zipcode'>우편 번호</label></td>
					<td>
						<input type="text" name="zipcode" id="zipcode" size="7" readonly placeholder="주소 검색">
						<input type="button" value="주소 검색" onClick="openDaumPostcode()">
					</td>
				</tr>								
				<tr>
					<td><label for='addr'>주소</label></td>
					<td>
						<input type="text" name="addr" id="addr" size="40" readonly placeholder="주소 입력">
					</td>	
				</tr>	
				
				<tr>	
					<td><label for='addr2'>상세주소</label></td>
					<td>
						<input type="text" name="addr2" id="addr2" size="40" placeholder="상세 주소 입력">
					</td>
				</tr>					

				<tr>	
					<td colspan="2" align="center">
						<input type="button" value="제출" onClick="check()">
						<input type="reset" value="취소">
					</td>
				</tr>			
			
			
			</table>
		</form>
</body>
</html>