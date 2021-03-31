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

   <link href="./summernote-0.8.18-dist/summernote.css" rel="stylesheet">
   <script src="./summernote-0.8.18-dist/summernote.js"></script>
	<script src="textEdit.js" type="text/javascript"></script>
	
   

</head>
<body>
<c:if test="${empty sessionScope.memId}">
<h3 align="center">로그인 후 글을 작성해 주세요.</h3>
<br>
<div align="center">
<button><a href="${ctxpath}/member/loginForm.do">로그인</a></button>
<button><a href="${ctxpath}/board/list.do">글목록</a></button>
</div>
<br>
</c:if>

<c:if test="${!empty sessionScope.memId}">
<form name="upload" id="upload" method="post" action="${ctxpath}/board/textEditPro.do">
   <input type="hidden" name="num" value="${num}">
   <input type="hidden" name="ref" value="${ref}">
   <input type="hidden" name="re_step" value="${re_step}">
   <input type="hidden" name="re_level" value="${re_level}">
          <table width="70%" align="center">
             <tr>
                <td colspan="2">
                     <h2>제목</h2>
                     <input type="text" name="title" id="title" size="100" placeholder="제목 입력">
                     <select name="category" id="category">
                        <option selected value="">카테고리 선택</option>
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
             <tr>
                <td colspan="2">
                   <h2>내용</h2>
                    <textarea id="summernote" name="content"></textarea>                                      
                    
                 </td>
             </tr>
              <tr><td colspan="2">
              <table width="100%">
              <tr><td>[대표사진]</td></tr>
              <tr height="100" bgcolor="#f6f2f2"><td>
                 <input type="hidden" id="thumbnail" name="thumbnail" value="">
                 <input type="hidden" id="img_num" name="img_num" value="0">
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