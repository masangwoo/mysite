<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>


				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr>
							<td >${count-status.index}</td>
							<td style="text-align:left; padding-left:${(item.depth-1)*20 }px">
								<c:if test='${item.depth gt 1}'>
									<img src="${pageContext.request.contextPath}/assets/images/reply.png">							
								</c:if>
							<a href="${pageContext.request.contextPath}/board?a=view&no=${item.no}">${item.title}</a></td>
							<td width=100>${item.userName}</td>
							<td width=100>${item.hit}</td>
							<td>${item.regDate}</td>
							<td>
							<c:choose>
			<c:when test="${authUser.no==item.userNo}">
				<a href="${pageContext.request.contextPath}/board?a=delete&no=${item.no}">
				<img src= "${pageContext.request.contextPath}/assets/images/recycle.png">
				</a>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
							</td>
						</tr>
						</c:forEach>
				</table>
				


				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
			<c:choose>
			<c:when test="${empty authUser}">
				<a href="${pageContext.request.contextPath}/user?a=loginform" id="new-book">글쓰기</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
			</c:otherwise>
		</c:choose>
				
					
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>