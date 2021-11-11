<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, board.model.vo.* "%> <%--  board.model.vo.Board, board.model.vo.PageInfo 대신 board.model.vo.* 이렇게 가져올 수 있음 --%>
<%
	ArrayList<Board> list = (ArrayList)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.outer{
		width: 800px; height: 500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	#listArea{text-align: center;}
	.tableArea{width:650px;	height:350px; margin-left:auto;	margin-right:auto;}
	th{border-bottom: 1px solid grey;}
	.pagingArea button{border-radius: 15px; /* background: #9ED4C2; */ background: none; border: 1px solid #9ed4c2;} /* #D5D5D5; */
	.buttonArea{margin-right: 50px;}
	.buttonArea button{background: #D1B2FF; border-radius: 5px; color: white; width: 80px; heigth: 25px; text-align: center;}
	button:hover{cursor: pointer;}
	#numBtn{/* background: #9ED4C2; */ background: none; border: 1px solid #9ed4c2;} /* #B2CCFF */
	#choosen{/* background: lightgray; */ background: none; border: 1px solid lightgray;}
	#listArea{margin: auto;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">게시판</h2>
		<div class="tableArea">
			<table id="listArea">
				<tr>	
					<th width="100px">글번호</th>
					<th width="100px">카테고리</th>
					<th width="300px">글제목</th>
					<th width="100px">작성자</th>
					<th width="100px">조회수</th>
					<th width="150px">작성일</th>
				</tr>
				<% if (list.isEmpty()) { %>
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
				<% } else { %>
				<% 		for(Board b : list) { %>
				<tr>	
					<td><%= b.getBoardId() %></td>
					<td><%= b.getCategory() %></td>
					<td><%= b.getBoardTitle() %></td>
					<td><%= b.getNickName() %></td>
					<td><%= b.getBoardCount() %></td>
					<td><%= b.getCreateDate() %></td>
				</tr>	
				<% 		} %>
				<% } %>
			</table>
		</div>
		
		<div class="pagingArea" align="center">
		
			<!-- 맨 처음으로  -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=1'">&lt;&lt; 맨 처음</button>
			
			<!-- 이전 페이지로 -->
			<button id="beforeBtn" onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= pi.getCurrentPage() - 1 %>'">&lt; 이전</button>
			<script>
				if(<%= pi.getCurrentPage() %> <= 1){
					$('#beforeBtn').prop('disabled', true);
				}
			</script>
			
			<!-- 숫자 버튼 -->
			<% for (int p = pi.getStartPage(); p <= pi.getEndPage(); p++) { %>
			<% 		if(p == pi.getCurrentPage()) { %>
			<button id="choosen" disabled><%= p %></button> <!-- 현재 페이지는 선택 못하게 -->
			<%      } else { %>
			<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= p %>'"><%= p %></button>
			<%		 } %>
			<%	} %>
			
			<!-- 다음 페이지로 -->
			<button id="afterBtn" onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= pi.getCurrentPage() + 1 %>'">다음 &gt;</button>
			<script>
				if(<%= pi.getCurrentPage() %> >= <%= pi.getMaxPage() %>){
					$('#afterBtn').prop('disabled', true);
				}
			</script>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.bo?currentPage=<%= pi.getMaxPage() %>'">맨 끝 &gt;&gt;</button>
		</div>
		
		<div class="buttonArea" align="right">
			<% if(loginUser != null) { %>
			<button onclick="location.href='<%= request.getContextPath() %>/writeBoardForm.bo'">작성하기</button>
			<% } %>
		</div>
		<script>
		
			$('#listArea td').mouseenter(function(){
				$(this).parent().css({'background':'darkgray', 'cursor':'pointer'})
			}).mouseout(function(){
				$(this).parent().css('background', 'none');
			}).click(function(){
				var bId = $(this).parent().children().eq(0).text();
				
				if ('<%= loginUser %>' != 'null') { // null에 ''를 넣는 이유. loginUser에 ''로 감싸지 않으면 변수명으로 인식, ''감싸기 때문에 null도 ''로 감싸기
					location.href='<%= request.getContextPath() %>/detail.bo?bId=' + bId;
				} else {
					alert('회원만 이용할 수 있는 서비스입니다.');
				}
			})
			
			
		</script>
	</div>
</body>
</html>