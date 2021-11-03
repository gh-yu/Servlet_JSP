package com.kh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PracticeServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String[] food = request.getParameterValues("food");
		String recommendation = null;
		switch(gender) {
		case "여자" : recommendation = "시계"; break;
		case "남자" : recommendation = "운동화"; break;
		}
		
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		String foodStr = "";
		for (int i = 0; i < food.length; i++) {
			if (i == 0) {
				foodStr += food[i];
			} else {
				foodStr += ", " + food[i];
			}
		}
		request.setAttribute("food", foodStr);
		request.setAttribute("recommendation", recommendation);
		
		RequestDispatcher view = request.getRequestDispatcher("servlet/practiceJsp.jsp");
		view.forward(request, response);
	}
}
