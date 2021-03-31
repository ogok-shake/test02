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
	<form name="al_upload" id="al_upload" method="post" action="${ctxpath}/album/textEditPro.do" encType="multipart/form-data">

	<input type="hidden" id="countTrack" name="countTrack" value="${1}">
		<table align="center" width="50%">
		
			<tr>
				<td width="15%" align="right">앨범 자켓</td>
				<td><input type="file" name="al_jacket" id="al_jacket"></td>
			</tr>
			<tr>
				<td align="right">앨범 코드</td>
				<td><input type="text" name="al_code" id="al_code" size="68"></td>
			</tr>
			<tr>
			
				<td align="right">앨범 명</td>
				<td><input type="text" name="al_name" id="al_name" size="68"></td>
			</tr>
			<tr>
				<td align="right">아티스트 명</td>
				<td><input type="text" name="al_artist" id="al_artist" size="68"></td>
			</tr>
			<tr>
				<td align="right">앨범 장르</td>
				<td><input type="text" name="al_genre" id="al_genre" size="68"></td>
			</tr>
			<tr>
				<td align="right">앨범 분류</td>
				<td><input type="text" name="al_type" id="al_type" size="68"></td>
			</tr>
			<tr>
				<td align="right">발매사</td>
				<td><input type="text" name="al_distributor" id="al_distributor" size="68"></td>
			</tr>
			<tr>
				<td align="right">기획사</td>
				<td><input type="text" name="al_comp" id="al_comp" size="68"></td>
			</tr>
			<tr>
				<td align="right">발매일</td>
				<td><input type="date" name="al_release" id="al_release" size="68"></td>
			</tr>
			<tr><td colspan="2" align="center">[수록곡]</td></tr>
			<tr>
				
				<td id="track" class="track" colspan="2" align="center">		
					
						

					<table id="track1" class="track1" width="100%">
						<tr>
							<td align="right">track</td>
							<td align="left" colspan="3">
								<input type="button" value="수록곡 삭제" onClick="alert('첫번째 수록곡은 삭제할 수 없습니다.');">
							</td>
						</tr>					
						<tr>
							<td align="right">곡 명</td>
							<td><input type="text" name="mu_name1" id="mu_name1" size="25"></td>
							<td align="right">아티스트</td>
							<td><input type="text" name="mu_artist1" id="mu_artist1" size="25"></td>
						</tr>
						<tr>
							<td align="right">작사가</td>
							<td><input type="text" name="mu_lyricist1" id="mu_lyricist1" size="25"></td>
							<td align="right">작곡가</td>
							<td><input type="text" name="mu_songwriter1" id="mu_songwriter1" size="25"></td>
						</tr>
						<tr>
							<td align="right">편곡가</td>
							<td><input type="text" name="mu_arranger1" id="mu_arranger1" size="25"></td>
							<td align="right">곡 장르</td>
							<td><input type="text" name="mu_genre1" id="mu_genre1" size="25"></td>
						</tr>
						<tr>
							<td align="right">뮤직비디오<br>링크</td>
							<td colspan="3"><input type="text" name="mu_video1" id="mu_video1" size="82"></td>
						</tr>
						<tr>
							<td align="right">가사</td>
							<td colspan="3">
								<textarea name="mu_lyric1" id="mu_lyric1" rows="10" cols="85"></textarea>
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					
					<input type="button" value="수록곡 추가" onClick="appendMusic()"><br><br>
					
				</td>
			</tr>
			<tr><td colspan="2" align="center">[상세 설명]</td></tr>
			<tr>
				
				<td colspan="2" align="center">
					<textarea id="summernote" name="al_detail"></textarea>
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
















