<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); // POST방식으로 한글데이터 보냈기 때문에 인코딩 설정 필요 %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP표준액션태그 중 useBean의 param 속성 이용하기</h2>
	<!-- person이라는 이름의 Person객체 생성 -->
	<jsp:useBean id="person" class="action.model.vo.Person"> 
<%-- 		<jsp:setProperty property="name" name="person" param="name"/> --%>
<%-- 		<jsp:setProperty property="gender" name="person" param="gender"/> --%>
<%-- 		<jsp:setProperty property="nai" name="person" param="nai"/> --%>

		<!-- property값과 param값이 같기 때문에 param속성 지정해주지 않아도 괜찮음 -->
<%-- 		<jsp:setProperty property="name" name="person"/> --%>
<%-- 		<jsp:setProperty property="gender" name="person"/> --%>
<%-- 		<jsp:setProperty property="nai" name="person"/> --%>

		<!-- property값과 param값이 모두 같기 때문에 *로 한 줄로 작성해줘도 괜찮음-->
		<jsp:setProperty property="*" name="person"/> 
	</jsp:useBean> 
	
	이름 : <jsp:getProperty property="name" name="person"/><br>
	성별 : <jsp:getProperty property="gender" name="person"/><br>
	나이 : <jsp:getProperty property="nai" name="person"/><br>
</body>
</html>