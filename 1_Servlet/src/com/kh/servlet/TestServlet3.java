package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet3 extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
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
		
		String recommendation = null;
		switch (age) {
		case "10대 미만": recommendation = "비눗방울 건"; break;
		case "10대": recommendation = "슬라임"; break;
		case "20대": recommendation = "애플워치"; break;
		case "30대": recommendation = "돈"; break;
		case "40대": recommendation = "지갑"; break;
		case "50대": recommendation = "바디 프렌드"; break;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인 정보 출력화면</title>");
		out.println("<style>");
		out.println("h2{color: red;}");
		out.println("span{font-weight: bold;}");
		out.println("#name{color: orange;}");
		out.println("#gender{color: yellow; background: black;}");
		out.println("#age{color: green;}");
		out.println("#city{color: blue;}");
		out.println("#height{color: navy;}");
		out.println("#food{color: purple;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인 취향 테스 결과(POST)</h2>");
		out.printf("<span id='name'>%s</span>님은 ", name);
		out.printf("<span id='age'>%s</span>이시며, ", age);
		out.printf("<span id='city'>%s</span>에 사는 ", city);
		out.printf("키 <span id='height'>%s</span>cm인", height);
		out.printf("<span id='gender'>%s</span>입니다. ", gender);
		out.println("좋아하는 음식은<span id='food'>");
		for (int i = 0; i < food.length; i++) {
			if (i == 0) {
				out.printf("%s", food[i]);
			} else {
				out.printf(", %s", food[i]);
			}
		}
		out.println("</span>입니다.");
		out.println("<hr>");
		out.println("<h3>" + age + "에 맞는 선물 추천</h3>");
		out.printf("'%s' 선물은 어떠신가요?", recommendation);
		out.println("</body>");
		out.println("</html>");	
	}	
}
