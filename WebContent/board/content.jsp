<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function confirmDelete(){
		var c=confirm('게시물을 삭제 하려면 확인을 눌러주세요.');
		if(c==true){
			location='${ctxpath}/board/delete.do?num=${num}&pageNum=${pageNum}';
		}else{
			return false;
		}
	
	}
</script>
</head>
<body>
   <input type="hidden" name="num" value="${num}">
   <input type="hidden" name="ref" value="${ref}">
   <input type="hidden" name="re_step" value="${re_step}">
   <input type="hidden" name="re_level" value="${re_level}">
          <table width="70%" align="center">
             <tr>
                <td colspan="2" align="center">
                <br><br>
                     <font color="gray">${dto.category}</font>
                </td>
             </tr>
             <tr>
                <td colspan="2" align="center">
                <br>
                     <font size="+1"><b>${dto.title}</b></font>
                <br><br>
                </td>
             </tr>
             <tr>
                <td align="left">
                     <b>${dto.id}</b>&nbsp;<font color="gray">${dto.regdate}</font>
                </td>
                <td align="right">
                     <font color="gray">조회수 : ${dto.readcount}</font>
                </td>
             </tr>
             <tr><td colspan="2">
             <br>
             <hr size="1" color="gray" align="center">
             <br>
             </td></tr>
             <tr>
                <td colspan="2">
                	<br><br>
                   ${content}                                   
                    
                 </td>
             </tr>
             <tr>
                <td align="left">
						<input type="button" value="글목록" onClick="location='${ctxpath}/board/list.do?pageNum=${pageNum}'">
						<input type="button" value="글쓰기" onClick="location='${ctxpath}/board/textEdit.do'">
                 </td>
                <td align="right">
					<c:if test="${sessionScope.memId eq dto.id}">
						<input type="button" value="글수정" onClick="location='${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}'">
						<input type="button" value="글삭제" onClick="confirmDelete()">
					</c:if>                                
                 </td>
             </tr>
			
           </table>


</body>
</html>