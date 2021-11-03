<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="inputValue();"> <!-- onload: 로딩이 되면 함수 실행  -->
	<b>아이디 중복 검사</b>
	<br>
	<form action="<%= request.getContextPath() %>/checkId.me" id="idCheckForm">
		<input type="text" id="inputId" name="inputId">
		<input type="submit" value="중복확인"/>
	</form>
	
	<br>
	<% 
	Integer result = (Integer)request.getAttribute("result");
	if(result != null) { // Integer로 한 이유 -> 중복검사 버튼 누르지 않았을 때, 즉 result값이 null일 때 안내문구 안 뜨게 하기 위해
		if(result > 0) {
	%>
			이미 사용 중인 아이디 입니다.
	<% } else { %>
			사용 가능한 아이디입니다.
	<% } %>
	<%} %>
	<br>
	<br>
	
	<input type="button" id="usedId" value="확인">
	<input type="button" id="cancel" value="취소" onclick="window.close();">
	
	<script>
		function inputValue() {
			if (<%= result %> == null) { // 중복검사 누르기 전에만
				document.getElementById('inputId').value = opener.joinForm.joinUserId.value;
				// opener.joinForm.joinUserId.value -> 부모한테 있었던 값을 가지고 옴(이 아이디 중복검사 창의 부모 창은 회원가입 창)
			} else {
				document.getElementById('inputId').value = "<%= request.getAttribute("checkedId") %>";
			}
		}
		
		document.getElementById('usedId').onclick = function(){
			opener.joinForm.joinUserId.value = document.getElementById('inputId').value;
			self.close(); // 창이 닫힘, window.close()로 해도 됨
		}
	</script>

</body>
</html>