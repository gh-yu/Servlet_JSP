package board.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.vo.Board;
import board.model.vo.PageInfo;

public class BoardService {

	private BoardDAO bDAO = new BoardDAO();
	
	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = bDAO.getListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = bDAO.selectList(conn, pi);
		
		close(conn);
		
		return list;
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();
		
		int result = bDAO.insertBoard(conn, board);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Board selectBoard(int bId, String upd) {
		Connection conn = getConnection();
		
		int result = 0;
		if (!(upd != null && upd.equals("Y"))) { // upd가 쿼리스트링에 없으면 조회수 업데이트
			result = bDAO.updateCount(conn, bId);
		}
		
		Board b = bDAO.selectBoard(conn, bId); 
		if(result > 0 && b != null) { // 조회수 증가를 시켰을 때만 commit (조회수 증가 update문 이용하기 때문)
				commit(conn);
		} else {
				rollback(conn);
		}
		
		close(conn);
		
		return b;
	}

	public int updateBoard(int bId, int cateId, String title, String content) {
		Connection conn = getConnection();
		
		int result = bDAO.updateBoard(conn, bId, cateId, title, content);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int deleteBoard(int bId) {
		Connection conn = getConnection();
		
		int result = bDAO.deleteBoard(conn, bId);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}



}