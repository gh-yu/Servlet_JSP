package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private JDBCTemplate() {} // 객체 생성하지 않기 위해 private

	public static Connection getConnection() {
		Connection conn = null; 
		
		Properties prop = new Properties();
		
		String fileName = JDBCTemplate.class.getResource("/sql/driver.properties").getPath(); 
		// 자바프로젝트가 아니기에 다른방식으로 properties의 경로 가져옴
		// System.out.println(fileName);
		// /D:/Kh_Courses/5_Servlet_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/3_JSPServlet/WEB-INF/classes/sql/driver.properties
		
		if (conn == null) {
			
			try {
				// 1. 바로 적어주는 방법
				// Class.forName("oracle.jdbc.driver.OracleDriver");
				// conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JSP_Servlet", "JSP_Servlet");
				
				// 2. properties파일에 저장한 정보를 읽어와  키-값 쌍을 통해 가져오는 방법
				prop.load(new FileReader(fileName)); // properties파일에 driver정보와 연결할 DB의 컴퓨터, 계정 이름/비밀번호 정보 담음
				
				Class.forName(prop.getProperty("driver")); // DB에 대한 driver등록
				
				conn = DriverManager.getConnection(prop.getProperty("url"), 
												   prop.getProperty("user"), 
												   prop.getProperty("password")); // DB연결을 위한 Connection객체 생성
				
				
				conn.setAutoCommit(false); // 오토커밋 모드 해제
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) { // 위 메소드와 매개변수 타입이 달라 오버로딩 적용
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) { // 부모클래스 타입의 참조변수가 자식 클래스형 객체(PreparedStatement)도 받아줄 수 있음 -> 다형성 적용
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
}
