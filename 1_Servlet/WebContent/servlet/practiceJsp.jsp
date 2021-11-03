<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String name = (String)request.getAttribute("name");
 	String gender = (String)request.getAttribute("gender");  
 	String food = (String)request.getAttribute("food");
 	String recommendation = (String)request.getAttribute("recommendation");  
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>test Servlet + jsp</h2>
	<%= name %>님은 <%= gender %>시네요.<br>
	<%= food %>을 좋아하시구요.<br>
	자신에게 주고 싶은 선물로 <%= recommendation %> 어떠신가요?
</body>
</html>