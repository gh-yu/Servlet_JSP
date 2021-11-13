package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ajax.model.vo.User;

/**
 * Servlet implementation class JQueryAjaxServlet
 */
@WebServlet("/jQueryAjax6.do")
public class JQueryAjaxServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JQueryAjaxServlet6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> userList = new ArrayList<User>(); // 지금은 DB 거치는 과정 생략함
		userList.add(new User(1, "박신우", "한국"));
		userList.add(new User(2, "타일러 라쉬", "미국"));
		userList.add(new User(3, "쯔위", "증극"));
		userList.add(new User(4, "모모", "일본"));
		userList.add(new User(5, "리사", "태국"));
		userList.add(new User(6, "알베르토 몬디", "이탈리아"));
		userList.add(new User(7, "샘 해밍턴", "호주"));
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		User user = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserNo() == userNo) {
				user = userList.get(i);
				break;
			}
		}
		 
		JSONObject userObj = null; //  ajax만 가지고는 객체를 보내지 못함(String으로 인식하기 때문) JSON이 필요
		JSONArray userArr = new JSONArray(); 
		
		if(user != null) {
			userObj = new JSONObject();
			
			userObj.put("userNo", user.getUserNo());
			userObj.put("userName", user.getUserName());
			userObj.put("userNation", user.getUserNation());
			
			userArr.add(userObj);
		} else {
			for(User userInfo : userList) { // 해당 유저가 없으면 전체 회원 정보 보냄
				userObj = new JSONObject();
				
				userObj.put("userNo", userInfo.getUserNo());
				userObj.put("userName", userInfo.getUserName());
				userObj.put("userNation", userInfo.getUserNation());
				
				userArr.add(userObj);
			}
		}
		
// 		response.setContentType("text/html; charset=UTF-8"); // jsp페이지지시어의 contentType과 맞춘 것, 이렇게만 설정하면 jsp페이지에서 ajax 안에다 dataType="json" 적어줘야함		
		response.setContentType("application/json; charset=UTF-8"); // 이렇게 설정해야 객체 보낼 수 있음
		PrintWriter out = response.getWriter(); 
		out.print(userArr); // JSONObject형, 그냥 객체 보내면 jsp에서 String으로 인식함
		out.flush();
		out.close(); // flush와 close 꼭 해주기
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
