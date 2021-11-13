<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.*, java.util.*"%>
<%
	Board b = (Board)request.getAttribute("board");
	ArrayList<Attachment> f = (ArrayList)request.getAttribute("fileList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width:1000px; height:735px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left:auto; margin-right:auto; margin-top:50px;
	}
	.detail{text-align:center;}
	.detail th, .detail td{width: 1000px; padding: 10px; background: rgba(255, 255, 255, 0.4);}
	.detail th{background: white;}
	#titleImgArea{width:500px; height:300px; margin-left:auto; margin-right:auto;}
	#contentArea{height:30px;}
	.detailImgArea{width:250px; height:210px; margin-left:auto; margin-right:auto;}
	#titleImg{width:500px; height:300px;}
	.detailImg{width:250px; height:180px;}
	.downBtn{background: #D1B2FF;}
	#thumbTable{margin: auto;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<table class="detail" id="thumbTable">
			<tr>
				<th width="50px">제목</th>
				<td colspan="5"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getNickName() %></td>
				<th>조회수</th>
				<td><%= b.getBoardCount() %></td>
				<th>작성일</th>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>대표<br>사진</th>
				<td colspan="4">
					<div id="titldImgArea" align="center">
						<% if (!f.isEmpty()) { %>
						<% 		for (int i = 0; i < f.size(); i++) {  // for문 안 돌려도 첫번째 사진이 썸네일이기 때문에 f.get(0)으로 접근해서 가져와도 됨 %> 
						<% 			if (f.get(i).getFileLevel() == 0) {  // fileLevel이 0이면 썸네일임  %>	
										<a href="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= f.get(i).getChangeName() %>" download="<%= f.get(i).getOriginName() %>"> <!-- 파일 다운받을 수 있게 a태그 링크에 파일경로, download속성 값으로 원래파일명 할당 -->		
											<img id="titleImg" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= f.get(i).getChangeName() %>">
										</a>	
						<% 			} %>
						<% 		} %>
						<% } else { %>
							<img id="titleImg" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/">
						<% } %>
					</div>
				</td>
			</tr>
			<tr>
				<th>사진<br>메모</th>
				<td colspan="6">
					<p id="contentArea">
					<%= b.getBoardContent() %>
					</p>
				</td>
			</tr>
		</table>
		
		<table class="detail">
			<tr>
				<% if (!f.isEmpty()) { %>
				<% 		for (int i = 0; i < f.size(); i++) { %> 
				<% 			if (f.get(i).getFileLevel() != 0) {  // fileLevel이 0이 아니면 썸네일 아님  %>				
								<td>
									<div class="detailImgArea">
										<a href="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= f.get(i).getChangeName() %>" download="<%= f.get(i).getOriginName() %>">
											<img class="detailImg" id="detailImg<%= i %>" src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= f.get(i).getChangeName() %>">
											<!-- id 반복되지 않게 i를 붙임 -->
										</a>
									</div>
								</td>
				<% 			} %>
				<% 		} %>
				<% } %>			
			</tr>
		</table>
	</div>
</body>
</html>