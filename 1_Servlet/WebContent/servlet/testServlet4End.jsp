<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%		
	// request.getAttribute(String name):Object
	// -> 매개변수 String name : 변수명
	String name = (String)request.getAttribute("name"); // Object로 받아왔기 때문에 String으로 다운캐스팅
	String gender = (String)request.getAttribute("gender");
	String age = (String)request.getAttribute("age");
	String city = (String)request.getAttribute("city");
	String height = (String)request.getAttribute("height");
	String food = (String)request.getAttribute("food");
	String recommendation = (String)request.getAttribute("recommendation");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{color: red;}
	span{font-weight: bold;}
	#name{color: orange;}");
	#gender{color: yellow; background: black;}
	#age{color: green;}
	#city{color: blue;}
	#height{color: navy;}
	#food{color: purple;}
</style>
</head>
<body>
	<h2>개인 취향 테스 결과(POST)</h2>
	<span id='name'><%= name %></span>님은 <span id='age'><%= age %></span>이시며, 
	<span id='city'><%= city %></span>에 사는 키 <span id='height'><%= height %></span>cm인 <span id='gender'><%= gender %></span>입니다. 
	좋아하는 음식은 <span id='food'><%= food %></span>입니다.
	
	<hr>
	
	<h3>age에 맞는 선물 추천</h3>
	'<%= recommendation %>' 선물은 어떠신가요?
</body>
</html>