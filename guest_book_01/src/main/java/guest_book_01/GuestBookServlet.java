package guest_book_01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public GuestBookServlet() {
	    super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 데이터베이스 접근
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {		
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book";
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT * FROM content_list";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			// 2. 데이터 파싱(파싱 Parsing: 특정 형식으로 구성된 데이터를 분석하고 그 의미를 이해하는 과정)
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			// DB에 저장된 행들을 불러와서 각 맵에 담은 뒤 리스트에 저장
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("author", rs.getString("g_author"));
				map.put("content", rs.getString("g_content"));
				map.put("date", rs.getDate("g_reg_date"));
				list.add(map);
			}
			
			// System.out.println(list.get(0).get("author"));  // list의 첫번째 인덱스 안에 키가 author인 값을 조회
			
			// 3. view에 list 전달
			request.setAttribute("books", list);
			RequestDispatcher rd = request.getRequestDispatcher("view/guestbook.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. form 정보 받아오기
		request.setCharacterEncoding("UTF-8"); // request에 있는 정보를 UTF-8 방식으로 인코딩해서 받아온다.
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		
		// 2. 데이터베이스 연결
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			// 데이터베이스에 접근하기 위해 필요한 정보 (url, 계정, 계정비밀번호)
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book";   // 127.0.0.1 == localhost
			String user = "root";                                      // 계정명
			String pw = "1234";                                        // 계정 비밀번호
			Class.forName("org.mariadb.jdbc.Driver");                  // 해당 클래스 드라이버를 이렇게(org.mariadb.jdbc.Driver) 부르기로 약속했다는 의미
			conn = DriverManager.getConnection(url, user, pw);         // 드라이버메니저.겟커넥션 함수에 지정한 정보 전달
			
			// 3. insert 
			String sql = "INSERT INTO content_list (g_author, g_content, g_reg_date) VALUES(?, ?, NOW())";  // ?로 사용자가 입력한 변수값을 전달
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);
			pstmt.setString(2, content);
			
			rs = pstmt.executeQuery();       // 실행하겠다는 의미
			
			// 4. 목록 조회
			response.sendRedirect("/book");  // doGet 호출
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
