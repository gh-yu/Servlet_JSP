package member.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage.me")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. DB에 저장되어 있는 내 정보를 select
//		HttpSession session = request.getSession();
//		Member loginUser = (Member)session.getAttribute("loginUser");
//		String userId = loginUser.getUserId();
//		 
//		Member member = new MemberService().selectMember(userId);
//		
//		request.setAttribute("member", member);
//		
//		request.getRequestDispatcher("WEB-INF/views/member/myPage.jsp").forward(request, response);
		
		// 2. 로그인하면서 session에 저장된 내 정보 불러오는 방법 <- 정보 수정이 있을시 session영역에 저장된 회원정보 업데이트해야 제대로된 결과 불러올 수 있음
		request.getRequestDispatcher("WEB-INF/views/member/myPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
