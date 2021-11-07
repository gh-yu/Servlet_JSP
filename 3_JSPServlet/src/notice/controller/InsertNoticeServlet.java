package notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class InsertNoticeServlet
 */
@WebServlet("/insert.no")
public class InsertNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content"); 
		String noticeWriter = ((Member)request.getSession().getAttribute("loginUser")).getUserId();	
		// noticeWriter는 admin 고정이기 때문에 가져오지 않아도 되지만, 일반 게시판의 경우 필요함
		
		String date = request.getParameter("date"); // date String값으로 넘어오는데 년도-월-일 이렇게 '-'로 구분돼서 넘어옴
		 
		Date noticeDate = null;
		if(date.equals("")) { // 관리자가 date를 입력하지 않았을 경우 -> 오늘날짜로 넣음
			noticeDate = new Date(new GregorianCalendar().getTimeInMillis());
		} else {
			String[] splitDate = date.split("-");
			int year = Integer.parseInt(splitDate[0]);
			int month = Integer.parseInt(splitDate[1]) - 1;
			int day = Integer.parseInt(splitDate[2]);
		
			noticeDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		}
		
		Notice n = new Notice(0, noticeTitle, noticeContent, noticeWriter, null, 0, noticeDate, null);
		// 4개만 있기 때문에 Notice클래스에서 매개변수 4개 있는 생성자 만들어놓는 것이 편함/ 또는 setter로
		
		int result = new NoticeService().insertNotice(n);

		if (result > 0) {
			response.sendRedirect("list.no"); // 공지사항 게시판으로 이동 -> list.no라는 url과 매핑되는 서블릿 만들어놓았기 때문에 이렇게 이동 가능 (경로 적어주지 않아도 됨)
		} else {
			request.setAttribute("msg", "공지사항 등록 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
