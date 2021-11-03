<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	page 지시어 : 현재 JSP페이지에서 필요한 속성을 기술하는 부분, 보통 맨 위에 위치
		language		사용할 스크립트 언어 유형 지정
		contentType		브라우저가 받아볼 페이지 인코딩 방식
		pageEncoding   	JSP파일에 기록된 소스코드 자체의 인코딩 방식
		import			자바 import와 동일
		errorPage		현재 JSP페이지에서 오류가 발생할 경우 호출할 페이지 지정
		isErrorPage		오류를 처리할 페이지에 설정, false 기본 값, true로 지정하면 exception내장객체 사용 가능
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  HTML 주석 : 페이지 소스보기나 f12를 누르면 소스 코드에서 확인 가능-->
	<%-- JSP 주석 : 클라이언트에게 전달이 되지 않는 주석 --%> 
	
	<% // 스크립트릿 태그 
		// 스크립트릿에서는 자바코드 사용 가능
		int total = 0; 
		
		for(int i = 1; i <= 10; i++){
			total += i;
//			out.println("안녕<br>"); // out : JSP의 내장객체
	%> 
			반가워<br>
	<% 
		} // for문 중간에 끊어서 사용 가능
		System.out.println("또 보자"); // 웹이 아닌 콘솔에 출력되는 출력문
	%>
	
	<br>
	expression 출력 : 1부터 10까지의 합은 <%= total %>입니다.
	<br>
	<br>
	scriptlet 출력 : 1부터 10까지의 합은 <% out.print(total); %>입니다.
</body>
</html>