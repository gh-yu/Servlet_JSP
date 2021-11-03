<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	String pizza = (String)request.getAttribute("pizza");
	String[] topping = (String[])request.getAttribute("topping");
	String[] side = (String[])request.getAttribute("side");
	int totalPrice = (int)request.getAttribute("price"); // 오토박싱, 오토언방식이 자유롭게 되기 때문에 Integer가 아닌 int로 받아도 상관X
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div{border: 3px solid orange; padding: 10px;}
	p{font-weight: bold;}
	#pizza{color: red;}
	#topping{color: green;}
	#side{color: blue;}
	#price{text-decoration: underline;}
</style>
</head>
<body>
	<div>
		<h3>주문 내역</h3>
		<p>
			피자는 <span id="pizza"><%= pizza %></span>,
			토핑은 <span id="topping">
			<%
				for(int i = 0; i < topping.length; i++) {
					if(i == 0) {
						out.print(topping[i]);
					} else {
						out.print(", " + topping[i]);
					}
				}
			%>
			</span>,
			사이드는 <span id="side">
			<!-- scriptlet태그 분리 사용, 표현식(출력)과 같이 사용 -->
			<%
				for(int i = 0; i < side.length; i++) {
					if(i == 0) {
			%>
					<%= side[i]%> 
			
			<%
					} else {
			%>
						, <%= side[i] %>
			<%
					}
				}
			%>
			</span> 주문하셨습니다.
		<p><br>
		
		<p>총합 : <span id="price"><%= totalPrice %></span><p><br>
		
		<h3 style="color: pink;">즐거운 식사시간 되세요 ~</h3>
	</div>

</body>
</html>