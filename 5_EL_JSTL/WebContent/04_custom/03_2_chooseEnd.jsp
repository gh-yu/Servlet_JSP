<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${ Integer.parseInt(param.num1) % 5 == 0 }">
			인형을 뽑았습니다.
		</c:when>
		<c:when test="${ Integer.parseInt(param.num1) % 5 == 1 }">
			장난감 자동차를 뽑았습니다.
		</c:when>
		<c:when test="${ Integer.parseInt(param.num1) % 5 == 2 }">
			피규어를 뽑았습니다.
		</c:when>
		<c:otherwise>
			꽝입니다.
		</c:otherwise>		
	</c:choose>
</body>
</html>