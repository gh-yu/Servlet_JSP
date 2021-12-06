<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int firstNum = Integer.parseInt(request.getParameter("firstNum"));
		int secondNum = Integer.parseInt(request.getParameter("secondNum"));
		
		int result = 0;
//		for(int i = firstNum; i <= secondNum; i++) {
		for(; firstNum <= secondNum; firstNum++) {
//			result += i;
			result += firstNum;
		}
		
		request.setAttribute("result", result);
	%>
	
	<jsp:forward page="01_3_sumView.jsp"/>
</body>
</html>