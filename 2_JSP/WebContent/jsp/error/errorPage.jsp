<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color:red;">에러 페이지입니다.</h1>
	<%= exception %> <!-- exception내장객체 사용 위해서는 isErrorPage="true"설정 -->
	<%= exception.getMessage() %> 
	<%= exception.getClass().getName() %> 
</body>
</html>