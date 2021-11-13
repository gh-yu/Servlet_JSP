package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns = "/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet()에 내용 기술
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 로그인시 아이디와 비밀번호 검증 및 STATUS가 'Y'인지 검증(DAO에서 쿼리문으로 작성)
		Member loginUser = new MemberService().loginMember(userId, userPwd); // Service에 사용자가 입력한 정보 넘기고 -> 로그인할 회원 정보 받아오기
		
		// 1. SELECT COUNT(*)를  사용하지 않은 이유 : 계정이 있다/없다만 판별하는게 아니라 계정 정보를 이용하기 위해서
		// 2. 계정 정보 이용 중 첫 번째는 가지고 온 데이터를 메인 화면에 뿌리기 : forward()
		// 3. 로그인 후 다른 서비스를 이용할 때 계속해서 추가적인 로그인을 하지 않음 : session영역 활용
		
		if(loginUser != null) {
			HttpSession session = request.getSession(); // HttpSession -> session영역/ getSession()으로 가져옴
			session.setMaxInactiveInterval(600); // 10분 뒤 세션 종료, 기본값은 30분임  
			session.setAttribute("loginUser", loginUser); // 회원 정보가 담긴 객체를 저장
			
			// response.sendRedirect("index.jsp");
			// forward()가 아닌 sendRedirect()를 사용하는 이유?
			// forward()는 url이 유지가 됨(form에서 요청한 url -> ex. login.me)
			// 메인페이지로 이동하면서 요청된 url도 함께 유지가 되는 것을 막기 위해 url변경이 가능한 sendRedirect() 사용
			// 데이터를 session영역에 저장했기에 sendRedirect() 이용으로 request와 response 새로 생성돼도 저장된 데이터는 문제 없음
			response.sendRedirect(request.getContextPath()); // index.jsp라는 url노출시키지 않기 위해 request.getContextPath()로 (노출시키는 것 좋지 않음)

		} else {
			// 로그인 실패시 에러페이지로 이동하기 (실제 구현시에는 알럿창 뜨게 해야할 것)
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
