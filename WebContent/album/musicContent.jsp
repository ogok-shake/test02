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
</head>
<body>
<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>곡 정보</b></td></tr></table>
<div id="music_info">
<table width="70%" align="center">
	<tr>
		<td rowspan="6" width="200" height="200"><img src="${ctxpath}/upload/${al_jacket}" width="200"></td>
		<td colspan="2" align="left">
		
			<h2>${musicDto.mu_name}</h2><a href="${ctxpath}/album/content.do?al_no=${al_no}&pageNum=${pageNum}">${al_name}</a>
			
			
		</td>
	</tr>
	<tr>
		<td width="10%">아티스트</td>
		<td>${musicDto.mu_artist}</td>
	</tr>
	<tr>
		<td>작곡가</td>
		<td>${musicDto.mu_songwriter}</td>
	</tr>
	<tr>
		<td>작사가</td>
		<td>${musicDto.mu_lyricist}</td>
	</tr>
	<tr>
		<td>편곡가</td>
		<td>${musicDto.mu_arranger}</td>
	</tr>
	<tr>
		<td>장르/스타일</td>
		<td>${musicDto.mu_genre}</td>
	</tr>
</table>
</div>
<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>가사</b></td></tr></table>
<div id="music_lyric">
	<c:if test="${empty musicDto.mu_lyric}">
		<center>가사가 없습니다.</center>
	</c:if>
	<c:if test="${!empty musicDto.mu_lyric}">
		<table align="center"width="70%">
			<tr>
	
			<td>
				${mu_lyric}
			</td>

			</tr>
		</table>
	</c:if>
</div>
<table width="70%" bgcolor="#f7eff0"><tr><td align="left"><b>뮤직 비디오</b></td></tr></table>

<div id="album_video">
	<c:if test="${empty musicDto.mu_video}">
		<center>뮤직비디오가 없습니다.</center>
	</c:if>
	<c:if test="${!empty musicDto.mu_video}">
		<table align="center"width="70%">
			<tr>
			
			<td width="200">
				<iframe width="224" height="126" src="${musicDto.mu_video}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				
				<br>
				${musicDto.mu_name}
			</td>
			
			</tr>
		</table>
	</c:if>
</div>
<table width="70%">
	<tr>
		<td align="left">
		<input type="button" value="글목록" onClick="location='${ctxpath}/album/list.do?pageNum=${pageNum}'">
		</td>
		<td align="right">
		<input type="button" value="앨범 정보" onClick="location='${ctxpath}/album/content.do?al_no=${al_no}&pageNum=${pageNum}'">
		</td>
	</tr>
</table>
</body>
</html>

















