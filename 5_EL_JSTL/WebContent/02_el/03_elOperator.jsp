<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, action.model.vo.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String str1 = "안녕";
		String str2 = new String("안녕");

		int big = 10;
		int small = 3;
		
		Person p1 = new Person("유건휘", '여', 20);
		Person p2 = new Person("유건휘", '여', 20);
	
	 	// page영역에 데이터 담기
	 	pageContext.setAttribute("str1", str1);
	 	pageContext.setAttribute("str2", str2);
	 	pageContext.setAttribute("big", big);
	 	pageContext.setAttribute("small", small);
	 	pageContext.setAttribute("p1", p1);
	 	pageContext.setAttribute("p2", p2);
	 	
	 	ArrayList<String> list = new ArrayList<>();
	 	list.add(str1);
	 	pageContext.setAttribute("list", list);
	 	
	 	ArrayList<String> list2 = new ArrayList<>();
	 	list.add(str2); // list2가 아닌 list에 요소 추가함
	 	pageContext.setAttribute("list2", list2);
	 	
	 	ArrayList<String> list3 = null;
	 	pageContext.setAttribute("list3", list3);
	%>
	<h2>EL연산</h2>
	<h3>산술연산</h3>
	10 * 7 = ${ 10 * 7 }<br>
	10 / 3 = ${ 10 / 3 } = ${ 10 div 3 }<br> <!-- div에 빨간줄 뜨는건 이클립스라는 툴 자체의 오류 -->
	15 % 4 = ${ 15 % 4 } = ${ 15 mod 4 }<br>
	
	<hr>
	
	<h3>비교연산</h3>
	<b>str1 == str2</b><br>
	스크립트릿 str1 == str2 : <%= str1 == str2 %><br> 
	<!-- 자바에서는 ==으로 비교하면 안에 있는 주소값 비교, str2는 new String("안녕")으로 새로운 주소값 할당됐기 때문에 false가 나옴
		 만약, str1과 str2 둘 다 "안녕"으로만 값 할당했으면 같은 주소값 공유하고 있기 때문에 true나올 것
		 만약, 안에 있는 문자열 비교하려면 equals로 비교해야함
	 -->
	el str1 == str2 : ${ str1 == str2 } 또는 ${ str1 eq str2 }<br> <!-- eq : equal -->
	<!-- el은 ==을 사용하더라도 안에 있는 실제 값 문자열이 같은지 비교하기 때문에 true 반환 -->
	<br>
	
	<b>str1 != str2</b><br>
	스크립트릿 str1 != str2 : <%= str1 != str2 %><br>
	el str1 != str2 : ${ str1 != str2 } 또는 ${ str1 ne str2 }<br> <!-- ne : not equal -->
	
	<br>
	
	<b>숫자형은 자동형변환 후 처리</b><br>
	big > small : ${ big > small } 또는 ${ big gt small } <br>
	big < small : ${ big < small } 또는 ${ big lt small } <br>
	big >= small : ${ big >= small } 또는 ${ big ge small } <br>
	big <= small : ${ big <= small } 또는 ${ big le small } <br>
	
	<br>
	
	<b>p1 == p2</b><br>
	스크립트릿 p1 == p2 : <%= p1 == p2 %><br>
	el pl == p2 : ${ p1 == p2 } 또는 ${ p1 eq p2 }<br> <!-- false -->
	<!-- 만약 Person클래스에서 equals와 hashcode 오버라이딩 했으면 안에 있는 값들을 비교했을 것이기 때문에 true 나올 것-->
	
	<br>
	
	<b>객체가 null 또는 비어있는지 체크하는 연산자</b><br>
	list가 비었나요? ${ empty list }<br>
	list2가 비었나요? ${ empty list2 }<br>
	list3가 비었나요? ${ empty list3 }<br>
	
</body>
</html>