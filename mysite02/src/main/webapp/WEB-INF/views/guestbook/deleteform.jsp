<%@page import="com.poscoict.mysite.vo.MysiteVo"%>
<%@page import="java.util.List"%>
<%@page import="com.poscoict.mysite.dao.MysiteDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<%
MysiteDao dao = new MysiteDao();
MysiteVo vo = new MysiteVo();
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<li><a href="">로그인</a>
				<li>
				<li><a href="">회원가입</a>
				<li>
				<li><a href="">회원정보수정</a>
				<li>
				<li><a href="">로그아웃</a>
				<li>
				<li>님 안녕하세요 ^^;</li>
			</ul>
		</div>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post"
					action="${pageContext.request.contextPath}/guestbook">
					<input type="hidden" name="a" value="delete"> <input
						type='hidden' name="no" value="${param.no}"> <label>비밀번호</label>
					<input type="password" name="password"> <input
						type="submit" value="확인">
				</form>
				<a href="">방명록 리스트</a>
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="">안대혁</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018</p>
		</div>
	</div>
</body>
</html>