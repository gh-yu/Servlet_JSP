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
@WebServlet("/jQueryAjax7.do")
public class JQueryAjaxServlet7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JQueryAjaxServlet7() {
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
		
		String userNo = request.getParameter("userNo");
		String[] ids = userNo.split(","); // 만약 1만 넣으면?
		
		JSONArray userArr = new JSONArray();
		for(String id : ids) {
			for (int i = 0; i < userList.size(); i++) {
				if (Integer.parseInt(id) == userList.get(i).getUserNo()) {
					User user = userList.get(i);
					
					JSONObject userObj = new JSONObject();
					userObj.put("userNo", user.getUserNo());
					userObj.put("userName", user.getUserName());
					userObj.put("userNation", user.getUserNation());
					
					userArr.add(userObj);
				}
			}
		}
		
		JSONObject result = new JSONObject();
		result.put("list", userArr); // map 방식으로 보내면 객체배열 여러개 보낼 수 있음 -> 지금은 연습을 위해 사용한 것으로 1개만 넣어 보냄, .put()해서 키값 쌍으로 여러 객체배열 보내기 가능
		
		// response.setContentType("text/html; charset=UTF-8"); // jsp페이지지시어의 contentType과 맞춘 것, 이렇게만 설정하면 jsp페이지에서 ajax 안에다 dataType="json" 적어줘야함
		response.setContentType("application/json; charset=UTF-8"); // 이렇게 보내면  jsp에서 dataType="json" 생략해도 객체로 잘 인식함
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
