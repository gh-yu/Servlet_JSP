package member.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdatePwdServlet
 */
@WebServlet(name="UpdatePwdServlet", urlPatterns="/updatePwd.me")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
//		
//		String userId = ((Member)session.getAttribute("loginUser")).getUserId(); // 객체 ()로 묶어서 메소드 호출해서 값 가져오기
//		String newPwd = request.getParameter("newPwd");
//		
//		int result = new MemberService().updatePwd(userId, newPwd);
//		
//		if (result > 0) {
//			Member loginUser = new MemberService().selectMember(userId); 
//			session.setAttribute("loginUser", loginUser);
//			response.sendRedirect("myPage.me");
//		} else {
//			request.setAttribute("msg", "회원정보 수정 실패");
//			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
//		}
//		
//	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId(); // 객체 ()로 묶어서 메소드 호출해서 값 가져오기
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		HashMap<String, String> map = new HashMap<String, String>(); // 매개변수에 3개 다 담아도 되지만, map에 담아 넘기는 방법
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		map.put("newPwd", newPwd);
		
		int result = new MemberService().updatePwd(map);
		
		if (result > 0) {
			loginUser.setUserPwd(newPwd);
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect("myPage.me");
		} else {
			request.setAttribute("msg", "비밀번호 변경 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
