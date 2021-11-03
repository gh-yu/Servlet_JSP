<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<%
	Date now = new Date();
	String today = String.format("%ty년 %tm월 %td일 %tA", now, now, now, now);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{border-collapse: collapse; font-size: small; height: 300px; width: 400px;}
	tr{padding:20px;}
	form{font-size: small;}
	label:nth-of-type(1), label:nth-of-type(2){margin-right: 13px;}
</style>
</head>
<body>
	<%-- <h2>오늘은 <span style="color: pink;"><%@ include file="today.jsp" %></span>입니다.</h2>
	 --%>
	<h1>오늘은 <span style="color: pink;"><%= today %></span>입니다.</h1>
	 
	<h1>피자 아카데미</h1>
	<table border="1">
			<tr>
				<th>종류</th>
				<th>이름</th>
				<th>가격</th>
				<td rowspan="12" width="10px"></td> <!-- thead영역은 rowspan안됨  -->
				<th>종류</th>
				<th>이름</th>
				<th>기격</th>
			</tr>
			<tr>
				<td rowspan="5">피자</td>
				<td>치즈피자</td>
				<td>5000</td>
				<td rowspan="11">사이드</td>
				<td>오븐구이통닭</td>
				<td>9000</td>
			</tr>
			<tr>
				<td>콤비네이션피자</td>
				<td>6000</td>
				<td>치킨스틱&윙</td>
				<td>4900</td>
	
			</tr>
			<tr>
				<td>포테이토피자</td>
				<td>7000</td>
				<td>치즈오븐스파게티</td>
				<td>4000</td>
			</tr>
			<tr>
				<td>고구마피자</td>
				<td>7000</td>
				<td>새우링&웨지감자</td>
				<td>3500</td>			
			</tr>
			<tr>
				<td>불고기피자</td>
				<td>8000</td>
				<td>갈릭포테이토</td>
				<td>3000</td>			
			</tr>
			<tr>
				<td rowspan="6">토핑</td>
				<td>고구마무스</td>
				<td>1000</td>
				<td>콜라</td>
				<td>1500</td>			
			</tr>
			<tr>
				<td>콘크림무스</td>
				<td>1500</td>
				<td>사이다</td>
				<td>1500</td>			
			</tr>
			<tr>
				<td>파인애플토핑</td>
				<td>2000</td>
				<td>갈릭소스</td>
				<td>500</td>			
			</tr>
			<tr>
				<td>치즈토핑</td>
				<td>2000</td>
				<td>피클</td>
				<td>300</td>			
			</tr>
			<tr>
				<td>치즈크러스트</td>
				<td>2000</td>
				<td>핫소스</td>
				<td>100</td>			
			</tr>
			<tr>
				<td>치즈바이트</td>
				<td>3000</td>
				<td>파마산 치즈가루</td>
				<td>100</td>			
			</tr>

	</table>
	<br>
	<%-- <form action="/2_JSP/pizzaServlet.do" method="post"> --%>
	<form action="<%= request.getContextPath() %>/pizzaServlet.do" method="post">
		<label>피자 :</label>
		<select name="pizza">
			<option value="치즈피자/5000">치즈피자</option>
			<option value="콤비네이션피자/6000">콤비네이션피자</option>
			<option value="포테이토피자7000">포테이토피자</option>
			<option value="고구마피자/7000">고구마피자</option>
			<option value="불고기피자/8000">불고기피자</option>
		</select><br>
		<label>토핑 : </label>
		<input type="checkbox" name="topping" value="고구마무스/1000">고구마무스
		<input type="checkbox" name="topping" value="콘크림무스/1500">콘크림무스
		<input type="checkbox" name="topping" value="파인애플토핑/2000">파인애플토핑
		<input type="checkbox" name="topping" value="치즈토핑/2000">치즈토핑
		<input type="checkbox" name="topping" value="치즈크러스트/2000">치즈크러스트
		<input type="checkbox" name="topping" value="치즈바이트/3000">치즈바이트
		<br>
		<label>사이드 : </label>
		<input type="checkbox" name="side" value="오븐구이통닭/9000">오븐구이통닭
		<input type="checkbox" name="side" value="치킨스틱&윙/4900">치킨스틱&윙
		<input type="checkbox" name="side" value="치즈오븐스파게티/4000">치즈오븐스파게티
		<input type="checkbox" name="side" value="새우링&웨지감자/3500">새우링&웨지감자
		<input type="checkbox" name="side" value="갈릭포테이토/3000">갈릭포테이토
		<input type="checkbox" name="side" value="콜라/1500">콜라
		<input type="checkbox" name="side" value="사이다/1500">사이다
		<input type="checkbox" name="side" value="갈릭소스/500">갈릭소스
		<input type="checkbox" name="side" value="피클/300">피클
		<input type="checkbox" name="side" value="핫소스/100">핫소스
		<input type="checkbox" name="side" value="파마산 치즈가루/100">파마산 치즈가루
		
		<br><br>
		<input type="submit" id="button" value="확인">	
	</form>

</body>
</html>