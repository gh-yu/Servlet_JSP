<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
 <%
 	Date tnow = new Date();
 	String ttoday = String.format("%ty년 %tm월 %td일 %tA", tnow, tnow, tnow, tnow);
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= ttoday %>
</body>
</html>