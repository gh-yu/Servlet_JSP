package com.gh.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class PizzaServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pizza = request.getParameter("pizza");
		String[] pSplit = pizza.split("/");
		pizza = pSplit[0];
		int totalPrice = 0;
		totalPrice += Integer.parseInt(pSplit[1]);

		String[] topping = request.getParameterValues("topping");
//		String toppingStr = "";
		for (int i = 0; i < topping.length; i++) {
			String[] tSplit = topping[i].split("/");
//			if(i == 0) {
//				toppingStr += tSplit[0];
//			} else {
//				toppingStr += ", " + tSplit[0];	
//			}
			topping[i] = tSplit[0];
			totalPrice += Integer.parseInt(tSplit[1]);
		}
		
		
		String[] side = request.getParameterValues("side");
		for (int i = 0; i < side.length; i++) {
			String[] sSplit = side[i].split("/");
			side[i] = sSplit[0];
			totalPrice += Integer.parseInt(sSplit[1]);
		}
		
		// ex. value에 가격 안 담고 switch로 일일이 가격 작성하는 방법도 있음
//		int totalPrice = 0;
//		switch(pizza) {
//		case "치즈피자" : totalPrice += 6000; 
//		}
//		
//		for (String t : topping) {
//			switch(t) {
//			case "" : totalPrice += 6000; 
//			}
//		}
//		
//		for (String s : side) {
//			switch(s) {
//			case "" : totalPrice += 6000; 
//			}
//		}
		
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", topping);
		request.setAttribute("side", side);
		request.setAttribute("price", totalPrice);
		
		RequestDispatcher view = request.getRequestDispatcher("jsp/pizzaOrderList.jsp");
		view.forward(request, response);
	}
}
