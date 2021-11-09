package notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateFormServlet
 */
@WebServlet("/noticeUpdateForm.no")
public class NoticeUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String nickName = request.getParameter("nickName");
		String content = request.getParameter("content");
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		String date = request.getParameter("date");
		
		Date noticeDate = null;
		if(date.equals("")) {
			noticeDate = new Date(new GregorianCalendar().getTimeInMillis());
		} else {
			String[] dateSplit = date.split("-");
			int year = Integer.parseInt(dateSplit[0]);
			int month =  Integer.parseInt(dateSplit[1]) - 1;
			int day = Integer.parseInt(dateSplit[2]);
			noticeDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		}
	
		Notice notice = new Notice(no, title, content, userId, nickName, 0, noticeDate, null);
		
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("WEB-INF/views/notice/noticeUpdateForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
