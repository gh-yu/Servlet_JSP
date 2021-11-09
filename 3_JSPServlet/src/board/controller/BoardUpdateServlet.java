package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/update.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bId = Integer.parseInt(request.getParameter("bid"));
		int cateId = Integer.parseInt(request.getParameter("category")); // String으로 저장해서 Board객체에 정보들 저장해서 넘긴 후 DAO에서 parseInt해도 됨
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int result = new BoardService().updateBoard(bId, cateId, title, content);
		if (result > 0) {
//			Board board = new BoardService().selectBoard(bId); // 수정 성공시, 해당 게시글의 상세페이지로 넘어가기 위해 select해옴
//			request.setAttribute("board", board);
//			request.getRequestDispatcher("WEB-INF/views/board/boardDetail.jsp").forward(request, response); 
			
			response.sendRedirect("detail.bo?bId=" + bId + "&upd=Y"); // "&upd=Y" 집어넣은 이유 -> 수정 후 조회수 증가시키는 걸 막기 위해 
		} else {
			request.setAttribute("msg", "게시글 수정 실패");
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
