<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="action.model.vo.Person"%>
<%
	Person p = (Person)request.getAttribute("person");
	String[] products = (String[])request.getAttribute("products"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>scriptlet을 통해 request객체에 저장된 데이터 출력하기</h2>
	<h4>개인정보(<%= (int)request.getAttribute("year") %>)</h4>
	이름 : <%= p.getName() %><br>
	성별 : <%= p.getGender() %><br>
	나이 : <%= p.getNai() %><br>
	<h4>취향정보</h4>
	<%= p.getName() %>님이 가장 좋아하는 음료 : <%= (String)request.getAttribute("beverage") %><br>
	<%-- <%= p.getName() %>님이 가장 좋아하는 물건 : <%= ((String[])request.getAttribute("products"))[0] %>, <%= ((String[])request.getAttribute("products"))[1] %>, <%= ((String[])request.getAttribute("products"))[2] %><br> --%>
	<!-- 2개만 입력시 NullPointerException 발생 -> for문 돌려야함 -->
	<%= p.getName() %>님이 가장 좋아하는 물건 : 
	<% for(int i = 0; i < products.length; i++) { %>
		<% if(i < products.length - 1) { %>
			<%= products[i] %>,
		<% } else { %>
			<%= products[i] %>
		<% } %>
	<% } %>
	<br>
	<%= p.getName() %>님이 가장 좋아하는 도서 : <%= (String)session.getAttribute("book") %><br>
	<%= p.getName() %>님이 가장 좋아하는  영화: <%= (String)application.getAttribute("movie") %><br>
	
	
	<hr>
	
	<h2>el의 내장객체 XXXScope를 통해 저장된 데이터 출력하기</h2>
	<h4>개인정보(${requestScope.year})</h4>
	이름 : ${requestScope.person.name}<br>
	성별 : ${requestScope.person.gender}<br>
	나이 : ${requestScope.person.nai}<br>
	<h4>취향정보</h4>
	${requestScope.person.name}님이 가장 좋아하는 음료 : ${requestScope.beverage}<br>	
	
	${requestScope.person.name}님이 가장 좋아하는 물건 :
	${ requestScope.products[0] }, ${ requestScope.products[1] }, ${ requestScope.products[2] }
	<!-- 1개나 2개만 입력시 NullPointerException 발생 -> for문 돌려야함 -->
	<br>
	${requestScope.person.name}님이 가장 좋아하는 도서 : ${ sessionScope.book }<br>
	${requestScope.person.name}님이 가장 좋아하는  영화: ${ applicationScope.movie }<br>
	<!-- request.getAttribute했을때 존재하지 않는 속성 가져오려고 하면 null이 나오는데, requestScope. 해서 가져오면 아무것도 가져오지 않음 -->
	${requestScope.person.name}님이 가장 좋아하는 도서 : ${ requestScope.book }<br> 
	${requestScope.person.name}님이 가장 좋아하는  영화: ${ requestScope.movie }<br>
	
	<hr>
	
	<h2>scope를 생략하여 저장된 데이터 출력하기</h2>
	<p>
		el내장객체 pageScope, requestScope, sessionScope, applicationScope 생략 가능<br>
		el은 pageScope -> requestScope -> sessionScope -> applicationScope순으로 찾음
		여러 scope에 동일한 속성을 기록해서 이름 충돌이 의심되면 명시적으로 scope를 지정해야 해당 scope의 속성을 가져올 수 있음
	</p>
	이름 : ${person.name}<br>
	성별 : ${person.gender}<br>
	나이 : ${person.nai}<br>
	<h4>취향정보</h4>
	${person.name}님이 가장 좋아하는 음료 : ${beverage}<br>
	${person.name}님이 가장 좋아하는 물건 :
	${products[0]}, ${products[1]}, ${products[2]}<br>
	${person.name}님이 가장 좋아하는 도서 : ${ book }<br>
	${person.name}님이 가장 좋아하는  영화: ${ movie }<br>
	${person.name}님이 가장 좋아하는 도서 : ${ book }<br> 
	${person.name}님이 가장 좋아하는  영화: ${ movie }<br>	
	
</body>
</html>