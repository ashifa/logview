<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

#container {
	width: 1200px;
	height: 600px;
	margin: 8px auto;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/envision.min.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/mystyle.css'/>" />
<script type="text/javascript"
	src="<c:url value='/scripts/jquery-1.7.1.min.js'/>">
	
</script>

<script type="text/javascript">
	
</script>

</head>
<body>
	<p id=errInfo></p>
	<button type=button onClick="myajax()">the button</button>
	<div id="body">
		<div id="container" />
	</div>

	<script type="text/javascript"
		src="<c:url value='/scripts/flotr2.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/scripts/sort.js'/>"></script>

	<hr />
	<table id="mytable" border="1" class="queryResults">
		<tr id="head">
			<th>index</th>
			<th>message head</th>
			<th>message body</th>
		</tr>
		<c:forEach items="${list}" var="item" varStatus="stat">
			<c:choose>
				<c:when test="${stat.index%2==0}">
					<tr id="${item.date}" style="display: none" class="even">
						<td>${stat.count}</td>
						<c:forEach items="${item.content}" var="subitem">
							<td>${subitem}</td>
						</c:forEach>
					</tr>
				</c:when>
				<c:otherwise>
					<tr id="${item.date}" style="display: none">
						<td>${stat.count}</td>
						<c:forEach items="${item.content}" var="subitem">
							<td>${subitem}</td>
						</c:forEach>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>


</body>
</html>