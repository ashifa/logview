<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/mystyle.css'/>" />
<title>Insert title here</title>
<script type="text/javascript"
	src="<c:url value='/scripts/jquery-1.7.1.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/scripts/telnet.js'/>"></script>
</head>
<body>
	<p id="errInfo"></p>
	<table class="queryResults">
		<tr id="head">
			<th>column1</th>
			<th>column2</th>
		</tr>
<%-- 		<c:forEach items="${bayList}" var="bay">
			<tr id="${bay[1]}">
				<c:forEach items="${bay}" var="bayCol">
					<td>${bayCol}</td>
				</c:forEach>
			</tr>
		</c:forEach> --%>

		<c:forEach items="${bayMap}" var="bay">
			<tr id="${bay.key }">
				<td>${bay.key}</td>
				<td>${bay.value}</td>
			</tr>
		</c:forEach>

	</table>
	<button onclick="getInfo()">click me</button>

</body>
</html>