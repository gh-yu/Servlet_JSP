package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/checkId.me")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputId = request.getParameter("inputId");
		
		int result = new MemberService().checkId(inputId);
		
//		request.setAttribute("result", result);
//		request.setAttribute("checkedId", inputId); // 중복검사 누르기 전 입력했던 값 날아가지 않게 저장해둠
//		
//		request.getRequestDispatcher("WEB-INF/views/member/checkIdForm.jsp").forward(request, response); // 불렀던 곳으로 다시 돌아가기
		
		// ajax방식
		// response.setContentType("application/json; charset=UTF-8"); // 객체로 보내는게 아니라 int를 보낼 것이기 때문애 json 안 써도 됨
		PrintWriter out = response.getWriter(); 
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
