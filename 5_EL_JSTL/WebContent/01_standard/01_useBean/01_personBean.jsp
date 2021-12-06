<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP표준액션 태그 중 useBean을 사용하여 VO클래스의 객체 정보 불러오기</h2>
	<%-- <jsp:useBean id="person1" class="action.model.vo.Person" scope="request"></jsp:useBean> --%>
	<!-- id : 객체를 담는 참조변수명 -->
	<jsp:useBean id="person1" class="action.model.vo.Person" scope="request"/> <!-- 닫는 태그 대신 시작 태그 마지막에 / 붙여서 닫을 수 있음 -->
	<!-- 해당 스코프(request)에서 속성 값이 해당 클래스 타입으로 person1이 존재하면 극 객체를 가져오고 없으면 새로 생성(scope를 지정하지 않으면 page) -->
	<!-- 존재하지 않았으면, request애 person1이라는 객체 만들어짐 -->
	
	이름 : <jsp:getProperty property="name" name="person1"/><br> <!-- property : gettr메소드명(getName의 "name"), name : 어디에 있는(어떤 객체의) 필드인지 --> 
	성별 : <jsp:getProperty property="gender" name="person1"/><br>
	나이 :<%--  <jsp:getProperty property="age" name="person1"/> --%>
		 <jsp:getProperty property="nai" name="person1"/><br>
	
	<!-- person1 객체의 필드에 정보를 저장하지 않았으면(초기화한 적이 없으면), 
		기본 생성자를 통해 값이 초기화되기 전의 각 자료형의 기본값이 출력됨(null, '', 0, ..) -->
		
	<h2>JSP표쥰액션 태그 중 useBean을 사용하여 VO클래스에 데이터 초기화하기</h2>
	<jsp:useBean id="person2" class="action.model.vo.Person" scope="request"/>
	<jsp:setProperty property="name" name="person2" value="강건강"/>
	<jsp:setProperty property="gender" name="person2" value="남"/>
	<jsp:setProperty property="nai" name="person2" value="20"/> <!-- setProperty의 property속성의 값은 setter메소드명에 따름(setAge면 "age", setNai면 "nai") -->	
	
	이름 : <jsp:getProperty property="name" name="person2"/><br>
	성별 : <jsp:getProperty property="gender" name="person2"/><br>
	나이 : <jsp:getProperty property="nai" name="person2"/><br>
	
</body>
</html>