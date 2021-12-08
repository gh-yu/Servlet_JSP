<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<form name="personFrm" action="${ pageContext.request.contextPath }/handler.do" method="post">
		<table>
			<tr>
				<td>성명</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					남<input type="radio" name="gender" id="man" value="남">
					여<input type="radio" name="gender" id="man" value="여">
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="number" name="age" value="1"></td>		
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="전송" onclick="goSubmit(0);">
					<input type="button" value="전송" onclick="goSubmit(1);">
					<input type="button" value="전송" onclick="goSubmit(2);">
				</td>	
			</tr>
		</table>
		<input type="hidden" name="view">
	</form>
	
	<script>
		function goSubmit(flag) {
			//var frm = document.personFrm; // form태그의 name값이 personFrm 
			// -> form태그의 id값으로 personFrm을 지정해서 값을 가져와도 되지만 ex. var frm = personFrm;
			// name값으로 personFrm을 할당하고, "document.name속성의값"으로 해당 form태그요소 불러올 수 있음(form태그에 한함)
			
			if (flag == 0) {
				frm.view.value = "02_scriptlet.jsp"; // form태그 안의 view라는 name속성값을 가진 input태그의 value
			} else if (flag == 1) {
				frm.view.value = "03_action.jsp";
			} else {
				frm.view.value = "04_el.jsp";
			}
			
			frm.submit();
		}
	</script>
</body>
</html>