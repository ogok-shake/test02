<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table,tr,td{
margin:auto;
padding:5px;
}
</style>
<script>
	function confirmDelete(){
		var c=confirm('게시물을 삭제 하려면 확인을 눌러주세요.');
		if(c==true){
			location='${ctxpath}/album/delete.do?al_no=${al_no}&pageNum=${pageNum}';
		}else{
			return false;
		}
	
	}
</script>
</head>
<body>

<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>앨범 정보</b></td></tr></table>

<div id="album_info">
<table width="70%" align="center">
	<tr>
		<td rowspan="7" width="200" height="200"><img src="${ctxpath}/upload/${albumDto.al_jacket}" width="200"></td>
		<td colspan="2" align="left"><h2>${albumDto.al_name}</h2></td>
	</tr>
	<tr>
		<td width="10%">앨범분류</td>
		<td>${albumDto.al_type}</td>
	</tr>
	<tr>
		<td>아티스트</td>
		<td>${albumDto.al_artist}</td>
	</tr>
	<tr>
		<td>장르/스타일</td>
		<td>${albumDto.al_genre}</td>
	</tr>
	<tr>
		<td>발매사</td>
		<td>${albumDto.al_distributor}</td>
	</tr>
	<tr>
		<td>기획사</td>
		<td>${albumDto.al_comp}</td>
	</tr>
	<tr>
		<td>발매일</td>
		<td>${al_release}</td>
	</tr>
</table>
</div>

<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>수록곡</b></td></tr></table>

<div id="album_track">
	<c:if test="${empty musicList}">
		<center>수록곡이 없습니다.</center>
	</c:if>
	<c:if test="${!empty musicList}">
	<table width="70%" align="center">
	<c:set var="number" value="${1}"/>
		<c:forEach var="musicDto" items="${musicList}">
			<tr>
				<td>${number}</td>
				<td><a href="${ctxpath}/album/musicContent.do?pageNum=${pageNum}&mu_no=${musicDto.mu_no}&al_no=${al_no}&al_name=${albumDto.al_name}">${musicDto.mu_name}</a></td>
				<td align="right">${musicDto.mu_artist}</td>
				<td align="right" style="color:lightgray"><a href="${ctxpath}/album/musicContent.do?pageNum=${pageNum}&mu_no=${musicDto.mu_no}&al_no=${al_no}&al_name=${albumDto.al_name}">곡정보</a></td>
				
			</tr>
			<c:set var="number" value="${number+1}"/>
			
		</c:forEach>
	</table>
	</c:if>
</div>

<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>뮤직 비디오</b></td></tr></table>

<div id="album_video">
	<c:if test="${empty musicVideo}">
		<center>뮤직비디오가 없습니다.</center>
	</c:if>
	<c:if test="${!empty musicVideo}">
		<table align="center"width="70%">
			<tr>
			<c:forEach var="video" items="${musicVideo}">
			<c:if test="${!empty video.mu_video and video.mu_video ne ''}">
			<td width="200">
				<iframe width="224" height="126" src="${video.mu_video}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				<!-- <embed src="${video.mu_video}" type="video/mp4" autostart=false width="200"/> -->
				<br>
				${video.mu_name}
			</td>
			</c:if>
			</c:forEach>
			</tr>
		</table>
	</c:if>
</div>

<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>상세 설명</b></td></tr></table>


<div id="album_introduce">
	<c:if test="${empty albumDto.al_detail||albumDto.al_detail eq ''}">
		<center>앨범 설명이 없습니다.</center>
	</c:if>
	<c:if test="${!empty albumDto.al_detail}">
		<table align="center"width="70%">
			<tr><td>
				${albumDto.al_detail}
			</td></tr>
		</table>
	</c:if>	
</div>

<table width="70%">
	<tr>
		<td align="left">
		<input type="button" value="글목록" onClick="location='${ctxpath}/album/list.do?pageNum=${pageNum}'">
		</td>
		<td align="right">
		<c:if test="${sessionScope.memId eq 'admin'}">
		<input type="button" value="글쓰기" onClick="location='${ctxpath}/album/textEdit.do'">
		<input type="button" value="글수정" onClick="location='${ctxpath}/album/updateForm.do?al_no=${al_no}&pageNum=${pageNum}'">
		<input type="button" value="글삭제" onClick="confirmDelete()">
		</c:if>                                
		</td>
	</tr>
</table>

</body>
</html>














