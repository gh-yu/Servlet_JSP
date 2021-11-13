package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.vo.Member;

/**
 * Servlet implementation class WriteBoardServlet
 */
@WebServlet("/insert.bo")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content= request.getParameter("content");
		String boardWriter = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		Board board = new Board(1, category, title, content, boardWriter);
		// Board board = new Board()
		// b.setCategory(category); ... 이런 식으로 객체 생성 후 setter메소드 이용해서 원하는 필드만 초기화시켜도 됨
		
		int result = new BoardService().insertBoard(board);
		
		if(result > 0) {
			response.sendRedirect("list.bo");
		} else {
			request.setAttribute("msg", "게시글 작성 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
