<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

    <link href="${ctxpath}/summernote-0.8.18-dist/summernote.css" rel="stylesheet">
    <script src="${ctxpath}/summernote-0.8.18-dist/summernote.js"></script>
    <script src="textEditAlbum.js" type="text/javascript"></script>
    <link href="${ctxpath}/css/track.css" rel="stylesheet" type="text/css">

</head>
<body>
<c:if test="${sessionScope.memId ne 'admin'}">
<h3 align="center">관리자 전용 페이지 입니다.</h3>
<br>
<div align="center">
<button><a href="${ctxpath}/member/loginForm.do">로그인</a></button>
<button><a href="${ctxpath}/album/list.do">글목록</a></button>
</div>
<br>
</c:if>

<c:if test="${sessionScope.memId eq 'admin'}">
	<form name="al_upload" id="al_upload" method="post" action="${ctxpath}/album/updatePro.do?pageNum=${pageNum}&al_no=${al_no}" encType="multipart/form-data">

	<input type="hidden" id="countTrack" name="countTrack" value="${count}">
	<input type="hidden" id="al_no" name="al_no" value="${al_no}">
		<table align="center" width="50%">
		
			<tr>
				<td width="15%" align="right">앨범 자켓</td>
				<td><input type="file" name="al_jacket" id="al_jacket" value="${albumDto.al_jacket}">등록된 사진 : ${albumDto.al_jacket}</td>
			</tr>
			<tr>
				<td align="right">앨범 코드</td>
				<td><input type="text" name="al_code" id="al_code" size="68" value="${albumDto.al_code}"></td>
			</tr>
			<tr>
			
				<td align="right">앨범 명</td>
				<td><input type="text" name="al_name" id="al_name" size="68" value="${albumDto.al_name}"></td>
			</tr>
			<tr>
				<td align="right">아티스트 명</td>
				<td><input type="text" name="al_artist" id="al_artist" size="68" value="${albumDto.al_artist}"></td>
			</tr>
			<tr>
				<td align="right">앨범 장르</td>
				<td><input type="text" name="al_genre" id="al_genre" size="68" value="${albumDto.al_genre}"></td>
			</tr>
			<tr>
				<td align="right">앨범 분류</td>
				<td><input type="text" name="al_type" id="al_type" size="68" value="${albumDto.al_type}"></td>
			</tr>
			<tr>
				<td align="right">발매사</td>
				<td><input type="text" name="al_distributor" id="al_distributor" size="68" value="${albumDto.al_distributor}"></td>
			</tr>
			<tr>
				<td align="right">기획사</td>
				<td><input type="text" name="al_comp" id="al_comp" size="68" value="${albumDto.al_comp}"></td>
			</tr>
			<tr>
				<td align="right">발매일</td>
				<td><input type="date" name="al_release" id="al_release" size="68" value="${al_release}"></td>
			</tr>
			<tr><td colspan="2" align="center">[수록곡]</td></tr>
			<tr>
				
				<td id="track" class="track" colspan="2" align="center">		
					
					<c:set var="track_num" value="${0}"/>	
					<c:forEach var="musicDto" items="${musicList}">
						<c:set var="track_num" value="${track_num+1}"/>
						<table id="track${track_num}" class="track${track_num}" width="100%">
							
						<tr>
							<td align="right">track</td>
							<td align="left" colspan="3">
								<c:if test="${track_num==1}">
								<input type="button" value="수록곡 삭제" onClick="alert('첫번째 수록곡은 삭제할 수 없습니다.');">
								</c:if>
								<c:if test="${track_num!=1}">
								<input type="button" value="수록곡 삭제" onClick="deleteTrack(${track_num})">
								</c:if>
							</td>
						</tr>					
						<tr>
							<td align="right">곡 명</td>
							<td>
								<input type="hidden" name="mu_no${track_num}" id="mu_no${track_num}" value="${musicDto.mu_no}">
								<input type="text" name="mu_name${track_num}" id="mu_name${track_num}" size="25" value="${musicDto.mu_name}">
							</td>
							<td align="right">아티스트</td>
							<td><input type="text" name="mu_artist${track_num}" id="mu_artist${track_num}" size="25" value="${musicDto.mu_artist}"></td>                                                   
						</tr>
						<tr>
							<td align="right">작사가</td>
							<td><input type="text" name="mu_lyricist${track_num}" id="mu_lyricist${track_num}" size="25" value="${musicDto.mu_lyricist}"></td>
							<td align="right">작곡가</td>
							<td><input type="text" name="mu_songwriter${track_num}" id="mu_songwriter${track_num}" size="25" value="${musicDto.mu_songwriter}"></td>
						</tr>
						<tr>
							<td align="right">편곡가</td>
							<td><input type="text" name="mu_arranger${track_num}" id="mu_arranger${track_num}" size="25" value="${musicDto.mu_arranger}"></td>
							<td align="right">곡 장르</td>
							<td><input type="text" name="mu_genre${track_num}" id="mu_genre${track_num}" size="25" value="${musicDto.mu_genre}"></td>
						</tr>
						<tr>
							<td align="right">뮤직비디오<br>링크</td>
							<td colspan="3"><input type="text" name="mu_video${track_num}" id="mu_video${track_num}" size="82" value="${musicDto.mu_video}"></td>
						</tr>
						<tr>
							<td align="right">가사</td>
							<td colspan="3">
								<textarea name="mu_lyric${track_num}" id="mu_lyric${track_num}" rows="10" cols="85">${musicDto.mu_lyric}</textarea>
							</td>
						</tr>
						</table>
					</c:forEach>

				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					
					<input type="button" value="수록곡 추가" onClick="appendMusicUpdate()"><br><br>
					
				</td>
			</tr>
			<tr><td colspan="2" align="center">[상세 설명]</td></tr>
			<tr>
				
				<td colspan="2" align="center">
					<textarea id="summernote" name="al_detail">${albumDto.al_detail}</textarea>
				</td>
			</tr>
             <tr>
               <td align="left">
                   <br>
                   <input type="button" value="글목록" onClick="location='${ctxpath}/album/list.do'">
                   <br><br>
               </td>
               <td align="right">
                   <br>
                   <input type="button" value="업로드" onClick="check()">
                   <br><br>
               </td>
              </tr>
		</table>
	</form>
</c:if>
</body>
</html>