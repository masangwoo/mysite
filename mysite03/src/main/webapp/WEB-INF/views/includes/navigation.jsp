<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="navigation">
	<ul>
	<c:choose>
		<c:when test='${param.menu=="gallery" }'>
			<li><a href="${pageContext.request.contextPath}">마상우</a></li>
			<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
			<li><a href="${pageContext.request.contextPath}/guestbook/spa">방명록(spa)</a></li>
			<li><a href="${pageContext.request.contextPath}/board?p=1">게시판</a></li>
			<li class="selected"><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}">마상우</a></li>
			<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
			<li><a href="${pageContext.request.contextPath}/guestbook/spa">방명록(spa)</a></li>
			<li><a href="${pageContext.request.contextPath}/board?p=1">게시판</a></li>
			<li><a href="${pageContext.request.contextPath}/gallery">갤러리</a></li>
		</c:otherwise>
	</c:choose>
		
		
	</ul>
</div>