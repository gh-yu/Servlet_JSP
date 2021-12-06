<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>입력한 두 개의 수 사이의 값들을 더한 누적 값 구하기</h2>
	<form action="01_2_sumCalc.jsp" method="post">
		첫 번째 수 : <input type="text" name="firstNum"><br>
		두 번째 수 : <input type="text" name="secondNum"><br>
		<button>계산하기</button> <!-- form태그 안에서의 button태그는 submit의 역할을 함 -->
	</form>
	<!-- 첫 번째 수가 두 번째 수보다 크면 form제출 못하게 막기 -->
</body>
</html>