<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<% pageContext.setAttribute("newline","\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<%--
	int count = list.size();
	for(MysiteVo vo:list){
		String msg = vo.getMessage().replace("\n", "<br>");  
		-- 
	--%>
	<c:set var="count" value="${fn:length(list)}"/>  
	<c:forEach var="item" items="${list}" varStatus="status">
	<table width=510 border=1>
		<tr>
			<td>${count-status.index}</td>
			<td width=100>${item.name}</td>
			<td>${item.reg_date}</td>
			<td><a href="${pageContext.request.contextPath}/guestbook/deleteform?no=${item.no}">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4>
			
			${fn:replace(item.message, newline, "<br/>") }
		</td>
		</tr>
	</table>
	</c:forEach>
	
						<br>
					</li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>