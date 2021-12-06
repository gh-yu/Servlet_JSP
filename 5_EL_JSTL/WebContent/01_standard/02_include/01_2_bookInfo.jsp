<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<h3>신간 소개</h3>
		도서 명 : 치카치카 이를 닦아요<br>
		저자 : 윤예의<br>
		페이지 수 : 17페이지<br>
		가격 : 12,000원<br>
		
		<hr>
		<hr>
		
		<%@ include file="01_1_bookCopyRight.jsp" %>
		
		<hr>
		
		<jsp:include page="01_1_bookCopyRight.jsp"/> <!-- 컴파일 전에 include, 컴파일과 동시에 include한다는 차이는 있지만 결과는 같음 -->
	</div>
</body>
</html>