package guest_book_02.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guest_book_02.model.vo.GuestBookVo;

import static guest_book_02.util.JDBCTemplate.getConnection;
import static guest_book_02.util.JDBCTemplate.close;

public class GuestBookDao {

	// 목록 조회
	public List<GuestBookVo> selectList(Connection conn) {  // 매개변수로 connection을 받아옴(데이터베이스에 접근하는 일은 서비스에서 하도록 분담)
		// Connection conn = getConnection();               // util에 만들어놓은 connection 정보를 호출해서 사용
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try {
			
			String sql = "SELECT * FROM content_list";
			pstmt = conn.prepareStatement(sql); // 쿼리를 보냄
			rs = pstmt.executeQuery();          // 쿼리를 실행 (위에서 보낸 쿼리문의 결과를 담음)
			
			// 파싱
			while(rs.next()) {
				// vo를 사용하면 고정된 key에 value를 넣어줄 수 있는 장점이 있음
				// list 안에 vo가 있는 형식
				
				GuestBookVo vo = new GuestBookVo();
				vo.setAuthor(rs.getString("g_author"));
				vo.setContent(rs.getString("g_content"));
				vo.setReg_date(rs.getDate("g_reg_date"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 닫는 순서는 반대로
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public void insertOne(Connection conn, String author, String content) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "INSERT INTO content_list (g_author, g_content, g_reg_date) VALUES(?, ?, NOW())";
			pstmt = conn.prepareStatement(sql);  // 쿼리 전달
			pstmt.setString(1, author);          // 첫번째 ?에 author  변수값 전달
			pstmt.setString(2, content);         // 두번째 ?에 content 변수값 전달
			
			// 여기서 원래는 ResultSet까진 필요 없지만 확장성을 위해 적음
			rs = pstmt.executeQuery();           // 쿼리 실행
			
			
		} catch (SQLException e) {  // 데이터베이스 관련 처리할 때 사용하는 SQLException
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
	}
}
