package board.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Reply;

import static common.JDBCTemplate.close;

import member.model.dao.MemberDAO;

public class BoardDAO {

	private Properties prop = null;

	public BoardDAO() {
		prop = new Properties();

		String fileName = MemberDAO.class.getResource("/sql/board/board-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;

		String query = prop.getProperty("getListCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}

	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;

		String query = prop.getProperty("selectList");
		// 1페이지 -> 1~10 , 2페이지 -> 11~20, .. 가져와야 함
		// 1, 11, 21, ..이 startRow
		// 10, 20, 30, ..이 endRow

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();

			list = new ArrayList<Board>();
			while (rset.next()) {
				Board b = new Board(rset.getInt("board_id"), rset.getInt("board_type"), rset.getString("cate_name"),
						rset.getString("board_title"), rset.getString("board_content"), rset.getString("board_writer"),
						rset.getString("nickName"), rset.getInt("board_count"), rset.getDate("create_date"),
						rset.getDate("modify_date"), rset.getString("status"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getBoardType());
			pstmt.setString(2, board.getCategory());
			pstmt.setString(3, board.getBoardTitle());
			pstmt.setString(4, board.getBoardContent());
			pstmt.setString(5, board.getBoardWriter());

			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	

	public int updateCount(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Board selectBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;

		String query = prop.getProperty("selectBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();

			if(rset.next()) { 
				b = new Board(rset.getInt("board_id"), 
							  rset.getInt("board_type"),
							  rset.getString("cate_name"),
							  rset.getString("board_title"),
							  rset.getString("board_content"),
							  rset.getString("board_writer"),
							  rset.getString("nickname"),
							  rset.getInt("board_count"),
							  rset.getDate("create_date"),
							  rset.getDate("modify_date"),
							  rset.getString("status")); 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return b;
	}

	public int updateBoard(Connection conn, int bId, int cateId, String title, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cateId);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, bId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList selecBList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectBTList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board(rset.getInt("board_id"), 
						  rset.getInt("board_type"),
						  rset.getString("cate_name"),
						  rset.getString("board_title"),
						  rset.getString("board_content"),
						  rset.getString("board_writer"),
						  rset.getString("nickname"),
						  rset.getInt("board_count"),
						  rset.getDate("create_date"),
						  rset.getDate("modify_date"),
						  rset.getString("status")); 
				
				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList selectFList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectFList");
		// selectFList=SELECT * FROM ATTACHMENT WHERE STATUS = 'Y' AND FILE_LEVEL = 0 
		// FILE_LEVEL = 0 썸네일인 것만 가져옴(list이기 때문)
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Attachment>();
			
			while (rset.next()) {
				Attachment a = new Attachment();
				a.setBoardId(rset.getInt("board_id"));
				a.setChangeName(rset.getString("change_name"));
				
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0; 
		
		String query = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < fileList.size(); i++) {
				
				pstmt.setString(1, fileList.get(i).getOriginName());
				pstmt.setString(2, fileList.get(i).getChangeName());
				pstmt.setString(3, fileList.get(i).getFilePath());
				pstmt.setInt(4, fileList.get(i).getFileLevel());
				
//				result = pstmt.executeUpdate();
//				if (result == 0) { // 하나라도 파일 DB저장 실패시 for문 빠져나가서 return result(0)
//					break;
//				}
				
				result += pstmt.executeUpdate();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Attachment> selectTumbnail(int bId, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Attachment>();
			while (rset.next()) {
//				list.add(new Attachment(rset.getInt("file_id"), 
//										rset.getInt("board_id"),
//										rset.getString("origin_name"),
//										rset.getString("change_name"),
//										rset.getString("file_path"),
//										rset.getDate("upload_date"),
//										rset.getInt("file_level"),
//										0, rset.getString("status")
//										));
				
				Attachment a = new Attachment(); // Attachment객체 생성해서 필요한 것만 setter로 값 할당하기
				a.setFileId(rset.getInt("file_id"));
				a.setOriginName(rset.getString("origin_name"));
				a.setChangeName(rset.getString("change_name"));
				a.setFilePath(rset.getString("file_path"));
				a.setUploadDate(rset.getDate("upload_date"));
				a.setFileLevel(rset.getInt("file_level")); // 여기서 file_level 가져오지 않아도 첫번째 사진이 썸네일이기 때문에 굳이 하지 않아도 됨
				
				list.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Reply>();
			while(rset.next()) {
				list.add(new Reply(rset.getInt("REPLY_ID"), 
								   rset.getString("REPLY_CONTENT"), 
								   rset.getInt("REF_BID"), 
								   rset.getString("REPLY_WRITER"), 
								   rset.getString("NICKNAME"), 
								   rset.getDate("CREATE_DATE"), 
								   rset.getDate("MODIFY_DATE"),
								   rset.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBId());
			pstmt.setString(3, r.getReplyWriter());
		
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
