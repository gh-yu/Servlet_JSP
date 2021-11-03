package com.kh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet4 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height");
		String[] food = request.getParameterValues("food");
		String recommendation = null;
		switch (age) {
		case "10대 미만": recommendation = "비눗방울 건"; break;
		case "10대": recommendation = "슬라임"; break;
		case "20대": recommendation = "애플워치"; break;
		case "30대": recommendation = "돈"; break;
		case "40대": recommendation = "지갑"; break;
		case "50대": recommendation = "바디 프렌드"; break;
		}
		
		// jsp에게 전달할 데이터 담기 : request
		// -> HttpServletRequest.setAttribute(String name, Object obj):void
		// --> 첫 번째 매개변수 String name : 데이터를 담는 변수
		// --> 두 번째 매개변수 Object obj : 데이터
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		String foodStr = "";
		for (int i  = 0; i < food.length; i++) {
			if (i == 0) {
				foodStr += food[i];
			} else {
				foodStr += ", " + food[i];
			}
		}
		request.setAttribute("food", foodStr);
		request.setAttribute("recommendation", recommendation);
		
		// 서블릿에서 결과 화면을 생성하는게 아니라 결과 화면을 출력해주는 곳으로 이동(jsp)
		// 페이지 이동 1. RequsetDispatcher.forward()
		//				-> 이동할 페이지에 데이터 전달 가능 (request, response 유지)
		//				-> url 변경x
		// 페이지 이동 2. HttpServletResponse.sendRedirect() 
		//				-> 이동할 페이지에 데이터 전달 불가능(request, response 새로 생성)
		//				-> url 변경
		
		// RequestDispatcher(String path)객체 생성
		RequestDispatcher view = request.getRequestDispatcher("servlet/testServlet4End.jsp"); // 인자에 넘어갈(이동할) 페이지에 대한 경로(path) 작성
		view.forward(request, response); // 넘겨줄 데이터를 request에  담아서 저장해야 함 -> 코드 필요
		
	}
}
