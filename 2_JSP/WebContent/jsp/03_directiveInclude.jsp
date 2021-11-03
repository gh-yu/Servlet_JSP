<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date" %>
<%
 	Date now = new Date();
	String today = String.format("%tY년 %tm월 %td일 %tA", now, now, now, now);
 %> <!-- 오늘 날짜 구해서 출력하는 일 많음 -> 따로 오늘 날짜 출력하는 jsp파일 만들기 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
 		오늘 날짜 <span style="color : Lightgray;"><%= today %></span><br>
 		오늘 날짜 <span style="color : Lightgray;"><%@ include file="today.jsp" %></span>
		<!-- include하면 그 페이지의 소스가 현재 페이지로 복사됨, 소스 안에 선언된 변수 사용 가능/ 현재페이지와 변수명 겹치면 오류-->
		<!-- 출력문 있는 페이지 include만 했는데 출력됨(출력문까지 복사됨) -->
	</h2>
</body>
</html>