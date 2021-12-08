<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- core라는 태그를 c라는 이름으로 부르겠다고 설정(prefix="c") -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jstl core라이브러리</h1>
	<p>
		사용할 태그 라이브러리 선언하기  : taglib 지시자 사용<br>
		prefix : 앞첨자, 다른 태그와 구분할 수 있는 name space<br>
		uri : 실제 웹상 주소가 아니라 태그 라이브러리를 나타내는 식별자
	</p>
	<h2>c:set태그</h2>
	<!-- prefix="c"로 설정했기 때문에 c라는 접두어로 쓴 것, 만약 core로 설정했으면 core라는 접두어 사용해야함 -->
	<c:set var="no1" value="${ param.num1 }"/> <!-- 변수 설정 -->
	<c:set var="no2" value="${ param.num2 }"/> 
	
	${ param.num1 }
	${ param.num2 }
	<br>
	${ no1 }
	${ no2 }
	
	<br>
	<c:set var="result" value="${ no1 * no2 }"/>
	${ no1 } 곱하기 ${ no2 }의 값은? ${ result }입니다.
	
	<hr>
	
	<h2>c:remove</h2>
	<p>지정한 변수를 모든 scope에서 검색해 삭제하거나 지정한 scope에서 삭제</p>
	<c:set var="result" value="9999" scope="request"/>
	삭제 전 result : ${ result }<br>
	삭제 전 requestScope.result : ${ requestScope.result }<br> 
	
	<br>
	
	<%-- <c:remove var="result"/> --%> <!-- 모든 영역의 result속성 다 삭제 -->
<%-- 	<c:remove var="result" scope="request"/> <!-- request영역에 있는 result속성만 삭제 -->
	삭제 후 result : ${ result }<br> <!-- no1과 no2 곱한 값이 뜸 -->
	삭제 후 requestScope.result : ${ requestScope.result }<br>	<!-- 아무것도 안 뜸 --> --%>
	
	<c:remove var="result" scope="page"/> <!-- page영역에 있는 result속성만 삭제 -->
	삭제 후 result : ${ result }<br> <!-- 9999가 뜸 -->
	삭제 후 requestScope.result : ${ requestScope.result }<br> <!-- 9999가 뜸 -->
	
	
</body>
</html>