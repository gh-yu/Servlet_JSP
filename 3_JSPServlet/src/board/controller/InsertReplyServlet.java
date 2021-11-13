package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import board.model.service.BoardService;
import board.model.vo.Reply;

/**
 * Servlet implementation class InsertReplyServlet
 */
@WebServlet("/insertReply.bo")
public class InsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		Reply r = new Reply();
		r.setRefBId(bId);
		r.setReplyWriter(writer);
		r.setReplyContent(content);
		
		ArrayList<Reply> list = new BoardService().insertReply(r); // 댓글 insert하고 insert한 댓글까지 select해서 가져오기 위해 ArrayList로 받아줌
		
		response.setContentType("application/json; charset=UTF-8"); 
		// Gson gson = new Gson();  
		GsonBuilder gb =new GsonBuilder();
		GsonBuilder gb2 = gb.setDateFormat("yyyy-MM-dd"); // 원하는 날짜 형식으로 포맷하기 위한 과정
		Gson gson = gb2.create();
		gson.toJson(list, response.getWriter()); // GSON방식으로 댓글 list 보냄
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
