<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
 <%
	 Member loginUser = (Member)session.getAttribute("loginUser");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP&Servlet</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<style>
	body{
		background:url('<%= request.getContextPath() %>/images/bg.png') no-repeat center center fixed;
		background-size: cover;
	}
	.loginArea{float: right;}
	#loginTable{text-align: right;}
	#loginTable td:nth-child(1){padding-right: 15px;}
	.loginBtns{float: right; margin-left: 5px;}
	.loginBtns input{background: #D1B2FF;}
	.loginBtns input+input{background: #B2CCFF;}
	input[type=button], input[type=submit]{cursor: pointer; border-radius: 15px; color: white;}
	#userInfo label{font-weight: bold;}
	.wrap{background: white; width: 100%; height: 50px;}
	.menu{
		background: white; color: navy; text-align: center; font-weight: bold; 
		vertical-align: middle; width: 150px; height: 50px; display: table-cell;
	}
	nav{width: 600px; margin-left: auto; margin-right: auto;}
	.menu:hover {background: beige; color:orangered; font-weight:bold; cursor:pointer;}
</style>
</head>
<body>
	<h1 align="center">Welcome to JSP&amp;Servlet World!</h1>
	
	<div class="loginArea"> <!-- 로그인되었을 때와 로그인되지 않았을 때의 화면 if문을 통해 다르게 구현 -->
	<% if(loginUser == null) { %>
		<form id="loginForm" action="<%= request.getContextPath() %>/login.me" method="post">
			<table id="loginTable">
				<tr>
					<td><label>ID</label></td>
					<td><input type="text" name="userId" id="userId"></td>
				</tr>
				<tr>
					<td><label>PWD</label></td>
					<td><input type="password" name="userPwd" id="userPwd"></td>
				</tr>
			</table>
			<div class="loginBtns">
				<input type="submit" value="로그인">
				<input type="button" value="회원가입" onclick="location.href='<%= request.getContextPath() %>/signUpForm.me'"> <!-- 페이지 이동을 위한 url -->
			</div>
		</form>
	<% } else { %> 
		<div id="userInfo" align="right">
			<label><%= loginUser.getUserName() %>님의 방문을 환영합니다.</label>
			<br clear="all">
			<div class="loginBtns">
				<input type="button" value="내 정보 보기" onclick="location.href='<%= request.getContextPath() %>/myPage.me'">
				<input type="button" value="로그아웃" onclick="location.href='<%= request.getContextPath() %>/logout.me'">
			</div>
		</div>
	<% }  %>
	</div>
	
	<br clear="all">
	<br>
	<div Class = "wrap">
		<nav>
			<div class="menu" onclick="location.href='<%= request.getContextPath() %>'">HOME</div>
			<div class="menu" onclick="location.href='<%= request.getContextPath() %>/list.no '">공지사항</div>
			<div class="menu" onclick="location.href='<%= request.getContextPath() %>/list.bo '">게시판</div>
			<div class="menu" onclick="location.href='<%= request.getContextPath() %>/list.th '">사진 게시판</div>
		</nav>
		
	</div>
</body>
</html>