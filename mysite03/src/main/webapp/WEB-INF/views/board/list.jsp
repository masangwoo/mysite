<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
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
					<c:set var="count" value="${fn:length(map.list)}" />
					<c:forEach var="item" items="${map.list}" varStatus="status">
						<tr>
							
							<c:choose>
								<c:when test="${map.p==map.pcnt}"> 
								<td> ${count-status.index}</td>
								</c:when>
								<c:otherwise> 
								<td>${count-status.index+(map.cnt%5)+5*(map.pcnt-p-1)}</td>
								</c:otherwise>
							</c:choose>
							
							
							<td style="text-align:left; padding-left:${(item.depth-1)*20 }px">
								<c:if test='${item.depth gt 1}'>
									<img src="${pageContext.request.contextPath}/assets/images/reply.png">							
								</c:if>
							<a href="${pageContext.request.contextPath}/board/view?no=${item.no}">${item.title}</a></td>
							<td width=100>${item.userName}</td>
							<td width=100>${item.hit}</td>
							<td>${item.regDate}</td>
							<td>
							<c:choose>
									<c:when test="${authUser.no==item.userNo}">
										<a href="${pageContext.request.contextPath}/board/delete?no=${item.no}">
											<img
											src="${pageContext.request.contextPath}/assets/images/recycle.png">
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
						<c:forEach var="cnt" begin="1" end="${map.pcnt}" step="1">
						<li
							<c:if test="${map.p==cnt}"> class="selected" </c:if>
						><a href="${pageContext.request.contextPath}/board?p=${cnt}">${cnt}</a></li>
					</c:forEach>
						
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:choose>
						<c:when test="${empty authUser}">
							<a href="${pageContext.request.contextPath}/user/login"
								id="new-book">글쓰기</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/board/writeform"
								id="new-book">글쓰기</a>
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