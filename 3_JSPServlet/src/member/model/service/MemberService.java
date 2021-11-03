package member.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDAO  mDAO = new MemberDAO();

	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		
		Member loginUser = mDAO.loginMember(userId, userPwd, conn);
		
		close(conn); // 매번 불러올 때마다 close로 Connection 닫아주기
		
		return loginUser;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		int result = mDAO.insultMember(conn, member);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int checkId(String inputId) {
		Connection conn = getConnection();
		
		int result = mDAO.checkId(conn, inputId);
		
		close(conn);
		
		return result;
	}

}
