package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet2 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		// 값을 받아오기 전 UTF-8로 인코딩 설정
		// post방식은 파라미터의 값을 가져올때 인코딩된 상태로 가져오는데 UTF-8방식이 아니라 한글이 깨짐
		// 값 가져오기 전 UTF-8로 인코딩 설정한 다음 가져와야 함
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");	
		String city = request.getParameter("city");	
		String height = request.getParameter("height");	
		String[] food = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(city);
		System.out.println(height);
		for (String f : food) {
			System.out.println(f);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인정보 출력화면</title>");
		out.println("<style>");
		out.println("h2{color: red;}");
		out.println("span{font-weight: bold;}");
		out.println("#name{color: orange;}");
		out.println("#age{color: yellow; background: black}");
		out.println("#city{color: green;}");
		out.println("#height{color: blue;}");
		out.println("#gender{color: navy;}");
		out.println("#food{color: purple;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인 취향 테스트 결과(POST)</h2>");
		out.println("<span id='name'>" + name + "</span>님은");
		out.printf("<span id='age'>%s</span>이시며, ", age);
		out.printf("<span id='city'>%s</span>에 사는 ", city);
		out.printf("키 <span id='height'>%s</span>cm인 ", height);
		out.printf("<span id='gender'>%s</span>입니다. ", gender);
		out.println("좋아하는 음식은 <span id='food'>");
		
		for (int i = 0; i < food.length; i++) {
			if (i == 0) {
				out.print(food[i]);
			} else {
				out.printf(", %s", food[i]);
			}
		}
		
		out.println("</span>입니다.");
		out.println("</body>");
		out.println("</html>");
		
	}
	
}
