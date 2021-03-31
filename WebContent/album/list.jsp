
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.memId eq 'admin'}">
<table width="70%" align="center">
	<tr>
		<td align="right">
			<a href="${ctxpath}/album/textEdit.do"><button>글쓰기</button></a>
		</td>
	</tr>

</table>
</c:if>
	<c:if test="${count==0}">
		<table border="1" width="70%" height="30%" align="center">
			<tr>
				<td align="center">
				게시판에 저장된 글이 없습니다.
				</td>
			</tr>
		</table>
	</c:if>	
	
<c:if test="${count>0}">
<table align="center" width="70%" margin="0">
	<tr>
		<td>
			<table margin="0">
				<tr>
					<c:forEach var="dto" items="${albumList}" begin="0" end="2" step="1">
						<td width="330">
							
							<c:if test="${dto.al_jacket==null or dto.al_jacket==''}"><!-- 엑박방지 -->
								<a href="${ctxpath}/album/content.do?al_no=${dto.al_no}&pageNum=${pageNum}">
								<img src="${ctxpath}/_img/album.jpg" alt="${dto.al_name}" border="0" height="330" width="330"/>
								</a>
							</c:if>
							<c:if test="${dto.al_jacket!=null and dto.al_jacket!=''}">
								<a href="${ctxpath}/album/content.do?al_no=${dto.al_no}&pageNum=${pageNum}">
								<img src="${ctxpath}/upload/${dto.al_jacket}" alt="${dto.al_name}" border="0" height="330" width="330"/>
								</a>
							</c:if>
							

						<br>
							<a href="${ctxpath}/album/content.do?al_no=${dto.al_no}&pageNum=${pageNum}">
							${dto.al_name}
							</a>
						</td>
					</c:forEach>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table margin="0">
				<tr>
					<c:forEach var="dto" items="${albumList}" begin="3" end="5" step="1">
						<td width="330">
							<c:if test="${dto.al_jacket==null or dto.al_jacket==''}">
								<a href="${ctxpath}/album/content.do?al_no=${dto.al_no}&pageNum=${pageNum}">
								<img src="${ctxpath}/_img/album.jpg" alt="${dto.al_name}" border="0" height="330" width="330"/>
								</a>
							</c:if>
							<c:if test="${dto.al_jacket!=null and dto.al_jacket!=''}">
								<a href="${ctxpath}/album/content.do?al_no=${dto.al_no}&pageNum=${pageNum}">
								<img src="${ctxpath}/upload/${dto.al_jacket}" alt="${dto.al_name}" border="0" height="330" width="330"/>
								</a>
							</c:if>
						<br>
							<a href="${ctxpath}/album/content.do?al_no=${dto.al_no}&pageNum=${pageNum}">
							${dto.al_name}
							</a>
						</td>
					</c:forEach>
				</tr>
			</table>
		</td>
	</tr>
</table>
</c:if>

	<c:if test="${count>0}">
	<table align="center">
	<tr><td>
		<c:if test="${endPage>pageCount}">
			<c:set var="endPage" value="${pageCount}"/>
		</c:if>
		<c:if test="${startPage>10}">
			<a href="${ctxpath}/album/list.do?pageNum=${startPage-10}">
			[이전 블럭]
			</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="${ctxpath}/album/list.do?pageNum=${i}">
			[${i}]
			</a>
		</c:forEach>
		<c:if test="${endPage<pageCount}">
			<a href="${ctxpath}/album/list.do?pageNum=${startPage+10}">
			[다음 블럭]
			</a>
		</c:if>
		</td></tr>
		</table>
	</c:if>
</body>
</html>