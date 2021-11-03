<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, java.util.HashMap" %> 
<%@ page import="java.util.HashSet" errorPage="error/errorPage.jsp" %>
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
	<% 
		ArrayList<String> list = new ArrayList<String>(); // import자동X, page지시어에 직접 작성
		HashMap<String, String> map = new HashMap<String, String>();
		HashSet<String> set = new HashSet<String>();
		
		list.add(0, null);
		System.out.println(list.get(0).charAt(0)); 
		// list.get(0) == null이기에 charAt(0)메소드 이용시 null에 접근했다는 에러 뜸 -> NullPointerException (null에는 0번째 인덱스가 없음)
		// 이 500에러 처리 위한 페이지로 이동 -> 페이지 지시어에 errorPage="이동할 페이지"
	%>
</body>
</html>