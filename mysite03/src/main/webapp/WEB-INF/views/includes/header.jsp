<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<div id="header">
	<h1> MySite </h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a><li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a>
			</c:when>
			<c:otherwise>
				<li><a	href="${pageContext.request.contextPath}/user/update">회원정보수정</a> <li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a> <li>
				<c:when test="${authUser.role=='authUser'}">
				<li><a href="${pageContext.request.contextPath}/admin">관리자 페이지로</a> <li>
				</c:when>
				<li>${authUser.name}, ${authUser.role} 님안녕하세요^^;</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>