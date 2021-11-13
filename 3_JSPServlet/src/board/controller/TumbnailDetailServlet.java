package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;

/**
 * Servlet implementation class TumbnailDetailServlet
 */
@WebServlet("/detail.th")
public class TumbnailDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TumbnailDetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BoardService service = new BoardService();
		// String upd = null; // 수정할 때 접근하는 것이 아니기 때문에 upd에 null값 줌
		Board board = service.selectBoard(bId, null); // 이미 만들어져있는 게시글 상세조회 위한 메소드 호출, upd자리 인자로 null값 집어넣기(update하는 경우가 아니기 때문)
		// ?? 업데이트하고 여기로 온 경우 url 쿼리스트링의 upd에 인위적으로 넣은 값이 있을텐데 이렇게 null을 집어넣어도 되나?
		// getParameter로 가져온 다음 그 변수를 집어넣어야 맞음, 만약 쿼리스트링에 upd가 업으면 null임
		
		ArrayList<Attachment> fileList = service.selectThumnail(bId); // 해당 게시글에 관한 사진들 가져옴
		String page = null;
		if(fileList != null) {
			request.setAttribute("board", board);
			request.setAttribute("fileList", fileList);
			page = "WEB-INF/views/thumbnail/thumbnailDetail.jsp";
		} else {
			request.setAttribute("msg", "사진 게시판 상세 보기 실패");
			page = "WEB-INF/views/common/errorPage.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
