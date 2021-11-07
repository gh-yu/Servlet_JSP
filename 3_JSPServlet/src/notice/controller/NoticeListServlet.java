package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/list.no")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시판 버튼 클릭 -> 단순 페이지 이동이 아닌, 게시판 목록 가져와야함(Service의 메소드 호출)
		ArrayList<Notice> list = new NoticeService().selectList();
		
		String page = null;
		if(list != null) { // noticeList.jsp
			request.setAttribute("list", list);
			page = "WEB-INF/views/notice/noticeList.jsp";
		} else { // errorPage.jsp (list가 null이라는건 DAO에서 list객체 생성할때 문제가 있었다는 것)
			request.setAttribute("msg", "공지사항 조회 실패");
			page = "WEB-INF/views/common/errorPate.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
