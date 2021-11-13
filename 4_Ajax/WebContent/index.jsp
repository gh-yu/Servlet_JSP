<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ajax.model.vo.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<style>
	.test{border: 1px solid orange; min-height: 70px;}
</style>
</head>
<body>
	<h1>jQuery를 이용한 Ajax 구현</h1>
	<h3>1. 버튼 선택 시 전송 값 서버에 출력</h3>
	이름 : <input type="text" id="myName">
	<button id="nameBtn">이름 전송</button>
	<script>
		$('#nameBtn').click(function(){
			var myName = $('#myName').val();
			
			$.ajax({
				url: 'jQueryAjax1.do',
				data: {name:myName}, // url의 매개변수로 쓸 name이라는 변수에 myName의 값을 담는다 -> 서블릿에서 getParameter("name")으로 값 가져와야 함
				type: 'get',
				success: function(data){
					console.log('서버 전송 성공 시 호출되는 함수');
				}, 
				error: function(data){
					console.log('서버 전송 실패 시 호출되는 함수');
				}, 
				complete: function(data){
					console.log('서버 전송 성공/실패 여부와 상관 없이 무조건 호출되는 함수')
				}
			})
		});
	</script>
	
	<h3>2. 버튼 선택시 서버에서 보낸 값 사용자가 수신</h3>
	<button id="getServerTextBtn">서버에서 보낸 값 확인</button>
	<p class="test" id="p1"></p>
	<script>
		$('#getServerTextBtn').click(function(){
			$.ajax({
				url: 'jQueryAjax2.do',
				// type 안 써도 됨
				success: function(data){
					console.log(data);
					$('#p1').text(data);
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	<h3>3. 서버로 기본형 전송 값이 있고 결과로 문자열을 받아 처리</h3>
	<h4>두 개의 값을 더한 결과를 받아옴</h4>
	첫 번째 숫자 : <input type="text" id="firstNum"><br>
	두 번째 숫자 : <input type="text" id="secondNum"><br>
	<button id="plusBtn">더하기</button>
	<p class="test" id="p2"></p>
	
	<script>
		$('#plusBtn').click(function(){
			var firstNum = $("#firstNum").val();
			var secondNum = $("#secondNum").val();
			
 			$.ajax({
				url: 'jQueryAjax3.do',
				data: {firstNum:firstNum, secondNum:secondNum},
				success: function(data){
					console.log(data);
					console.log(typeof data); // string, 받아올때 println해서 넘어오기 때문에 string으로 넘어옴
					$('#p2').text(Number(data)); // 숫자 연산 또 필요하다면 Number(data) 이렇게 형변환 필요
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	<h3>4. Object형태의 데이터를 서버에 전송, 서버에서 처리 후 console로 출력</h3>
	학생 1 : <input type="text" id="student1"><br>
	학생 2 : <input type="text" id="student2"><br>
	학생 3 : <input type="text" id="student3"><br>
	<button id="studentTest">결과 확인</button>	
	<script>
		// 학생 1, 2, 3의 이름을 받아와 ajax를 통해 jQueryAjax4.do로 넘기고
		// 해당 서블릿에서 콘솔에 학생의 이름을 출력
		$("#studentTest").click(function(){
			var student1 = $('#student1').val();
			var student2 = $('#student2').val();
			var student3 = $('#student3').val();
			
			var students = {student1:student1, student2:student2, student3: student3};
			
			$.ajax({
				url: 'jQueryAjax4.do',
				data: students, // 객체를 넣어줘도 됨
				success: function(data){
					console.log('통신성공');
				},
				error: function(data){
					console.log('통신실패');
				}
			});
		});
	</script>
	
	<h3>5. 서버로 기본형 데이터 전송, 서버에서 객체 반환</h3>
	<h4>유저 번호 보내서 해당 유저 정보 가져오기</h4>
	유저 번호 : <input type="text" id="userNo">
	<button id="getUserInfoBtn">정보 가져오기</button>
	<p class="test" id="p3"></p>
	<textarea class="test" id="textarea3" cols="40" rows="5"></textarea>
	<script>
		$('#getUserInfoBtn').click(function(){
			$.ajax({
				url: 'jQueryAjax5.do',
				data: {userNo:$('#userNo').val()},
				/* dataType : "json", */
				success: function(data){
					console.log(data);
					console.log(typeof data); // (JSON 사용 안하면 객체로 보냈는데 string으로 반환됨)
					
					var resultStr = null;
					if (data != null) {
						resultStr = data.userNo + ", " + data.userName + ", " + data.userNation;
					} else {
						resultStr = '해당 회원이 없습니다.';
					}
					
					$('#p3').text(resultStr);
					$('#textarea3').val(resultStr);
					
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	<h3>6. 서버로 기본 값 전송, 서버에게 리스트 객체 반환</h3>
	<h4>유저 번호 요청 --> 해당 유저가 있는 경우 유저 정보, 없는 경우 전체 가져오기</h4>
	유저 번호 : <input type="text" id="userNo2"><br>  <!-- 입력받을때 사용자가 문자를 입력할 경우 고려 정규식 사용 필요 -> 숫자가 아니면 알림문구&버튼 disabled -->
	<button id="getUserInfoBtn2">정보 가져오기</button>
	<p Class="test" id="p4"></p>
	<textarea class="test" id="textarea4" cols="40" rows="5"></textarea>
	<script>
		$('#getUserInfoBtn2').click(function(){
			$.ajax({
				url: 'jQueryAjax6.do',
				data: {userNo:$('#userNo2').val()},
				/* dataType : "json", */
				success: function(data){
					console.log(data); // data 배열(객체배열)로 받음
					
					var resultStr ="";
					var resultStr2 ="";
					for(var i in data) {
						var user = data[i];
						resultStr += user.userNo + ", " + user.userName + ", " + user.userNation + "<br>" // html에 넣을 거기 때문에 <br>태그로 적용
						resultStr2 += user.userNo + ", " + user.userName + ", " + user.userNation + "\n" // textarea에 넣을땐 value에 넣어야 해서 이렇게 줄바꿈문자 추가
					}
					
					$('#p4').html(resultStr);
					$('#textarea4').val(resultStr2);
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	<h3>7. 서버 데이터 여러 개 전송, 서버에서 리스트 객체 반환</h3>
	<h4>유저 번호 전송 --> 현재 있는 유저 정보만 모아서 출력</h4>
	<h4>10 이상의 숫자는, ','로 쓸 수 없다고 설정</h4>
	유저 번호(번호,번호,번호) : <input type="text" id="userNo3"><br>
	<button id="getUserInfoBtn3">정보 가져오기</button><br>
	<textarea class="test" id="textarea5" cols="40" rows="5"></textarea>
	<Script>
 		$('#getUserInfoBtn3').click(function(){
 			$.ajax({
				url: 'jQueryAjax7.do',
				data: {userNo:$('#userNo3').val()},
			 	dataType: "json",
				success: function(data){
					console.log(data); // list형식으로 받아짐(서블릿에서 보낼땐 map방식으로 보냄)
					var result = '';
					for(var i in data.list) { // list 안의 배열에 접근
						result += data.list[i].userNo + ", " + data.list[i].userName + ", " + data.list[i].userNation + "\n";
					}
					
					$('#textarea5').val(result);
				},
				error: function(data){
					console.log(data);
				}
				
			}); 
		}); 
	
	</Script>
	
	<h3>8. 서버 유저 정보로 표 구성하기</h3>
	<button id="userInfoBtn">유저 정보 불러오기</button><br><br>
	<table id="userInfoTable" border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>중국</th>
			</tr>
		</thead>
		<tbody id="userInfoTbody"></tbody>
	</table>
	<script>
		$('#userInfoBtn').click(function(){
			$.ajax({
				url: 'jQueryAjax8.do',
				success: function(data){
					console.log(data);
					
					// jQuery형싱
/* 					$tableBody = $('#userInfoTable tbody');
					// $tableBody ? 변수명에 $가 붙는 이유 : 이 변수는 jQuery를 이용해서 값이 들어가 있다는 뜻, 구별 용도임, 기능이 추가되는 것x
					$tableBody.html(''); // 이렇게 먼저 비워주고 시작, 여러 번 클릭한다고 해도 여러 번 이어붙여지지 않음
							
					$.each(data, function(index, value) {
						var $tr = $('<tr>');
						var $noTd = $('<td>').text(value.userNo);
						var $nameTd = $('<td>').text(value.userName);
						var $nationTd = $('<td>').text(value.userNation);
						
						$tr.append($noTd);
						$tr.append($nameTd);
						$tr.append($nationTd);
						
						$tableBody.append($tr);
					}) */
									
					// javaScript형식
					var tableBody = document.getElementById('userInfoTbody')
					//var tableBody = $('#userInfoTable tbody'); // jQurey방식으로 요소(노드) 가져온다면 이렇게
					tableBody.innerHTML = '';
					
					var html = '';
					for (var i in data) {
						html += '<tr>' + '<td>' + data[i].userNo + '</td>'  + '<td>' + data[i].userName + '</td>'  +  '<td>' + data[i].userNation + '</td>'  + '</tr>'
					}
					
					tableBody.innerHTML = html;
					// node create해서 appendChild()메소드 이용해서 넣는 방법도 있음
					
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	<h3>9. 서버에서 List객체 반환 받아 select태그를 이용해서 보여줌</h3>
	유저 이름 : <input type="text" id="selectUserName">
	<button id="selectListBtn">검색</button>
	<select id="selectListTest"></select>
	<script>
		$('#selectListBtn').click(function(){
			$.ajax({
				url: 'jQueryAjax8.do',
				success: function(data){
					console.log(data);
					
					$select = $('#selectListTest');
					$select.find('option').remove();
					
					for(var i = 0; i < data.length; i++){
						var name = data[i].userName;
						var selected = (name == $('#selectUserName').val()) ? 'selected' : '';
						
						$select.append('<option value="' + data[i].userNo + '" ' + selected + ">" + name + "</option>"); 
						/* <opton value="유저넘버" selected(또는 없이)>이름</option>의 형태 */
						// 검색하면 그 검색 내용 selected되게 함
					}
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	<h3>10. Gson을 이용한  List 반환</h3>
	<button id="gsonListBtn">list 가져오기</button>
	<select id="gsonListSelect"></select>
	<script>
		$('#gsonListBtn').click(function(){
			$.ajax({
				url: 'jQueryAjax9.do',
				success: function(data){
					console.log(data);
					
					$select = $('#gsonListSelect');
					$select.find('option').remove();
					
					for(var i in data){
						var $option = $('<option>');
						$option.val(data[i].userNo);
						$option.val(data[i].userName);
					
						$select.append($option); // 잘 안나옴
					}
					
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	</script>
	
	
	
</body>
</html>