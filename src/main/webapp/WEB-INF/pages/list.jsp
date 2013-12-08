
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h1>Message : ${message}</h1>
	
<table > 
	<c:forEach items="${list}" var="item">
       <tr><td> ${item.date}</td><td>${item.content }</td></tr>
    </c:forEach>
    
  </table>  
    
</body>
</html>