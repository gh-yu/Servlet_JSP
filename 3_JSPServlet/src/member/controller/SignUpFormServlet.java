package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpFormServlet
 */
@WebServlet("/signUpForm.me")
public class SignUpFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect("WEB-INF/views/member/signUpForm.jsp");
		// WEB-INF폴더에 뷰파일이 있으면 경로를 이용해서는 페이지 이동 못함 (request.getContextPath()와 같은 위치(location)로로 이동)
		// -> 해결방법: forword()사용
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/member/signUpForm.jsp"); // 단순 페이지 이동(회원가입 버튼 누를시 회원가입 화면으로)
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
