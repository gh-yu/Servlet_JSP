package board.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;

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

	public ArrayList selectTList(int i) { // 반환형에 제네릭 빼기 (Board와 AttachMent 중 하나가 반환되기 때문)
		Connection conn = getConnection();
		
		ArrayList list  = null;
		if (i == 1) { // 게시판 목록 가져올 때는 매개변수에 1이라는 값이 들어옴
			list = bDAO.selecBList(conn);
		} else { // 파일 목록 가져올 때는 매개변수에 2라는 값이 들어옴
			list = bDAO.selectFList(conn);
		}
		
		close(conn); 
		
		return list;
	}

	public int inserThumbnail(Board b, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		int result1 = bDAO.insertBoard(conn, b);
		int result2 = bDAO.insertAttachment(conn, fileList);
		
//		if (result1 > 0 && result2 > 0) { // 게시글 insert됐고, 모든 파일이 insert가 됐으면
//			commit(conn);
//		} else {
//			rollback(conn);
//		}
		
		if (result1 > 0 && result2 >= fileList.size()) { // 게시글 insert됐고, 파일 갯수만큼 insert가 되었으면
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result1 + result2;
	}

	
	public ArrayList<Attachment> selectThumnail(int bId) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = bDAO.selectTumbnail(bId, conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> selectReplyList(int bId) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = bDAO.selectReplyList(conn, bId);

		close(conn);
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = bDAO.insertReply(conn, r);
		
		ArrayList<Reply> list = null; 
		if (result > 0) {
			list = bDAO.selectReplyList(conn, r.getRefBId()); // insert한 결과가 성공이면 댓글리스트 select해옴
			
			if (list != null) {
				commit(conn); // list가 있으면 커밋 아니면 롤백
			} else {
				rollback(conn);
			} 
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return list;
	}



}
