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
	<c:forEach var="i" begin="1" end="6">
	</c:forEach>
	
	<hr>
	
	<c:forEach var="i" begin="1" end="6" step="2">
		<h${ i }>반복문</h${ i }>
	</c:forEach>
	
	<hr>
	
	<%-- <c:forEach var="i" begin="7" end="2" step="-1"> step은 0이하는 설정 불가 --%> 
	<c:forEach var="i" begin="1" end="6" varStatus="vs">
		<h${ 7 - i }>반복문</h${ 7 - i }>
		vs.first : ${ vs.first }<br>
		vs.last : ${ vs.last }<br>
		vs.index : ${ vs.index }<br> <!-- (0기반) begin을 1로 설정했기 때문에 1이 뜸 -->
		vs.count : ${ vs.count }<br>
		vs.current : ${ vs.current }<br>
	</c:forEach>	
</body>
</html>