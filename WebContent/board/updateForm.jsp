<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<%@ page import="java.util.*" %>

<%@ page import="thumbnail.GetImgTag" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
   <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
   <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
   <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

   <link href="./summernote-0.8.18-dist/summernote.css" rel="stylesheet">
   <script src="./summernote-0.8.18-dist/summernote.js"></script>
	<script src="textEdit.js" type="text/javascript"></script>

	
</head>
<body>
<c:if test="${sessionScope.memId ne dto.id}">
<h3 align="center">작성자가 아니면 글을 수정할 수 없습니다.</h3>
<br>
<div align="center">
<button><a href="${ctxpath}/board/list.do">글목록</a></button>
</div>
<br>
</c:if>

<c:if test="${sessionScope.memId eq dto.id}">
<form name="upload" id="upload" method="post" action="${ctxpath}/board/updatePro.do?pageNum=${pageNum}">
   <input type="hidden" name="num" value="${num}">
          <table width="70%" align="center">
             <tr colspan="2">
                <td>
                     <h2>제목</h2>
                     <input type="text" name="title" id="title" size="100" value="${dto.title}">
                     <select name="category" id="category">
                      <option selected value="${dto.category}">${dto.category}</option>
                      <option value="gallery">gallery</option>
                      <option value="accessory">accessory</option>
                      <option value="pop sockets">pop sockets</option>
                      <option value="qna">Q&A</option>
                      <option value="review">review</option>
                      <option value="notice">notice</option>
                   </select>
                   <br><br>
                </td>
             </tr>
             <tr colspan="2">
                <td>
                   <h2>내용</h2>
                    <textarea id="summernote" name="content" >${dto.content}</textarea>                                      
                    
                 </td>
             </tr>
              <tr colspan="2"><td>
              <table width="100%">
              <tr><td>[대표사진]</td></tr>
              <tr height="100" bgcolor="#f6f2f2"><td>
				<c:set var="img_num" value="${0}"/>
				<c:forEach var="map" items="${map}">
					
					<img id="${map.value}" class="image" src="/krystal/upload/THUMB_${map.key}" width="100" onClick="$('img').attr('style','border:none');$(this).attr('style','border:5px solid #9c3639');document.upload.thumbnail.value='/krystal/upload/THUMB_${map.key}';"/>
					
					<c:if test="${img_num<map.value}">
						<c:set var="num" value="${map.value}"/>
					</c:if>
					
				</c:forEach>

				<input type="hidden" id="img_num" name="img_num" value="${img_num}">
                <input type="hidden" id="thumbnail" name="thumbnail" value="${dto.thumbnail}">

            </td></tr></table>
              </td></tr>
              
             <tr>
                <td align="left">
                   <br>
                   <input type="button" value="글목록" onClick="location='${ctxpath}/board/list.do?pageNum=${pageNum}'">
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