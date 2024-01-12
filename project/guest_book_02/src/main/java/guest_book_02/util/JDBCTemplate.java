package guest_book_02.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 데이터베이스에 접근하는 모든 일을 담당
// connection 관련 객체들을 만들어줌
public class JDBCTemplate {

	// connection 객체를 return하는 함수의 역할을 하는 정적(static) 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book";
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 최종 리턴
		return conn;
	}
	
	/*
	 * 닫는 함수 3개
	 * (Overload 메소드)
	 */
	public static void close(Connection conn) {
		// 데이터베이스와 관련된 코드를 사용할 땐 반드시 try-catch문을 사용
		try {
			if(conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && pstmt.isClosed() == false) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null && rs.isClosed() == false) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
