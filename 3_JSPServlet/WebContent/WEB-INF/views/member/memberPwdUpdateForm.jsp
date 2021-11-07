<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member user = (Member)session.getAttribute("loginUser"); // 다시 안 가져와도 menubar.jsp에서 로그인유저 loginUser에 저장해서 그냥 loginUser. 해서 쓰면 됨
	String currPwd = user.getUserPwd();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.outer{
		width: 600px; height: 500px; background-color: rgba(255, 255, 255, 0.4); border: 5px solid white; 
		color: black; margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	#updatePwdForm td{text-align: right; height: 50px;}
	#updatePwdBtn{background: #D1B2FF;}
	#cancelBtn{background: #B2CCFF;}
	#updatePwdForm>table{margin: auto;}
</style>
<!-- 비밀번호가 같은지 다른지 확인하는 유효성검사도 넣기 -->
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">비밀번호 수정하기</h2>
		
		<form action="<%= request.getContextPath() %>/updatePwd.me" method="post" id="updatePwdForm" name="updatePwdForm" onsubmit="return send();">
			<table>
				<tr>
					<td><label>현재 비밀번호</label></td>
					<td><input type="password" name="userPwd" id="userPwd" required></td>
				</tr>
				<tr>
					<td><label>변경 비밀번호</label></td>
					<td><input type="password" name="newPwd" id="newPwd" required></td>
				</tr>
				<tr>
					<td><label>변경 비밀번호 확인</label></td>
					<td><input type="password" name="newPwd2" id="newPwd2" required></td>
				</tr>
			</table>
			
			<br><br>
			
			<div class="btns" align="center">
				<input id="updatePwdBtn" type="submit" value="변경하기">
				<input type="button" id="cancelBtn" onclick="location.href='javascript:history.back();'" value="취소하기">
			</div>
		</form>
	</div>
	<script>
		var userPwd = document.getElementById('userPwd');
		var newPwd = document.getElementById('newPwd');
		var newPwd2 = document.getElementById('newPwd2');
		
		/* updatePwdBtn.onclick =  function() {*/
		function send() { // onsubmit이벤트로 함수 호출됨
			<%-- if (userPwd.value != "<%= currPwd %>") { // 이렇게 하면 해킹해서 비밀번호 맞추는 악용사례 발생 가능성이 있음, 쿼리문에서 DB에 저장된 비밀번호와 비교하는 과정 거치고, 비밀번호랑 다르면 에러페이지로 이동
				userPwd.focus();
				alert('현재 비밀번호를 다시 확인해주세요.')
			} else if (userPwd.value == newPwd.value){ 
				alert('현재 비밀번호와 변경할 비밀번호와 같습니다.');
				newPwd.focus();
			}  --%>
			if (newPwd.value != newPwd2.value) {
				alert('변경할 비밀번호를 다시 확인해주세요.');
				newPwd2.focus();
			} else {
/* 				if (userPwd.value != '' && newPwd.value != '' && newPwd2.value != '') {
					alert('비밀번호 변경이 완료되었습니다.');
				} */
				return true;
			}
			return false;
		}
	</script>
</body>
</html>