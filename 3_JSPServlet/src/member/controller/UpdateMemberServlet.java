package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateMember
 */
@WebServlet("/update.me")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String myId = request.getParameter("myId");
		String myName = request.getParameter("myName");
		String myNickName = request.getParameter("myNickName");
		String myPhone = request.getParameter("myPhone");
		String myEmail = request.getParameter("myEmail");
		String myAddress = request.getParameter("myAddress");
		String[] myInterest = request.getParameterValues("myInterest");
		
		String strMyInterest = "";
		for(int i = 0; i < myInterest.length; i++) {
			if (i == 0) {
				strMyInterest += myInterest[i];
			} else {
				strMyInterest += ", " + myInterest[i];
			}
		}
		
		Member newInfo = new Member(myId, null, myName, myNickName, myPhone, myEmail, myAddress, strMyInterest, null, null, null);

		int result = new MemberService().updateMember(newInfo);
		
		if(result > 0) {
			// 회원 정보 조회시 세션에 저장된 정보 불려오려면 회원 정보 수정시 세션 영역은 바뀌지 않기 때문에
			// 정보 수정 후 DB에 저장된 정보 불러와서 세션에 있는 정보도 update필요
			Member loginUser = new MemberService().selectMember(myId); 
			request.getSession().setAttribute("loginUser", loginUser);;
			response.sendRedirect("myPage.me"); // 마이페이지로 이동, /myPage.me로 하면 context root에 있는 파일 /는 빼기 
			
		} else {
			request.setAttribute("msg", "회원정보 수정 실패");
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
