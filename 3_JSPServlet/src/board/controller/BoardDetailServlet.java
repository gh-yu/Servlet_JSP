package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Reply;

/**
 * Servlet implementation class boardDetailServlet
 */
@WebServlet("/detail.bo")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		String upd = request.getParameter("upd"); // 수정 후 상세페이지 이동을 위해 이 서블릿으로 넘어올때 url의 쿼리스트링에 "&upd=Y"를 붙이기 때문에 getParameter로 가져옴
		
		Board board = new BoardService().selectBoard(bId, upd); // upd도 넘겨서 upd가 Y일때는 조회수 증가 안 시키도록 할 것
		
		ArrayList<Reply> list = new BoardService().selectReplyList(bId); // 댓글도 같이 가져옴
		
		String page = null;
		if(board != null) {
			page = "WEB-INF/views/board/boardDetail.jsp";
			request.setAttribute("board", board);
			request.setAttribute("list", list);
		} else {
			page = "WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 상세 조회 실패");
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
