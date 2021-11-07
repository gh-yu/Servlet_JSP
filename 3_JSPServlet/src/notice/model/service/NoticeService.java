package notice.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

public class NoticeService {
	
	private NoticeDAO nDAO = new NoticeDAO();

	public ArrayList<Notice> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = nDAO.selectList(conn);
		
		close(conn);
		
		return list;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = nDAO.insertNotice(conn, n);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		
		int result = nDAO.updateCount(conn, noticeNo); // 조회수 먼저 올려주기
		
		Notice notice = null;
		if (result > 0) {
			notice = nDAO.selectNotice(conn, noticeNo);
			if (notice != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		
		
		close(conn);
		
		return notice;
	}

	public int updateNotice(Notice notice) {
		Connection conn = getConnection();
		
		int result = nDAO.updateNotice(conn, notice);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

}
