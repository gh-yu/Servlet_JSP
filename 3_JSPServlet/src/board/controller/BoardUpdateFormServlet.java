package board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardUpdateFormServlet
 */
@WebServlet("/boardUpdateForm.bo")
public class BoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		String category = request.getParameter("category");
//		String title = request.getParameter("title");
//		String nickName = request.getParameter("nickName");
//		int count = Integer.parseInt(request.getParameter("count"));
//		String date = request.getParameter("date");
//		String content = request.getParameter("content");
		int bId = Integer.parseInt(request.getParameter("bId"));
//		Board board = new Board(bId, 0, category, title, content, null, nickName, count, null, null, null); // setter매소드 이용이 편함
		 
		// 위에서처럼 상세페이지에서 다 가져올 필요 없고, bId만 가져온 다음 만들어 놓았던 Service의 selectBoard()호출 하고 객체 저장해서 request에 저장하여 넘기면 됨
		// Board board = new BoardService().selectBoard(bId); // 이렇게 가져오면 조회수 증가시키기 때문에 이렇게 하면 안됨
		String upd = "Y";  // upd를 Y로 설정한 다음 service의 select메소드 호출하기 (updateServlet에서는 수정 후 해당 상세페이지 이동할 때 sendRedirect로 url 쿼리스트링에 "&upd=Y" 추가시켜 조회수 증가 막음)
		Board board = new BoardService().selectBoard(bId, upd); 
		
		request.setAttribute("board", board);
		request.getRequestDispatcher("WEB-INF/views/board/boardUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
