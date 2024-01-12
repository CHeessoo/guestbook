package guest_book_02.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guest_book_02.model.service.GuestBookService;
import guest_book_02.model.vo.GuestBookVo;

@WebServlet("/book")
public class GuestBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public GuestBookServlet() {
		super();
	}
	
	/*
	 * 메소드 형식
	 * 
	 * 접근제한자가 protected인 메소드
	 * 반환타입은 void
	 * 의존성 주입
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		/*
		 * 1. 데이터베이스접근 -> util         |  getConnection()
		 * 2. 목록 조회        -> dao          |  selectList()
		 * 3. 결과 파싱        -> service      |
		 * 4. view 전달        -> controller   |
		 */
		
		// 1. 데이터베이스접근
		List<GuestBookVo> list = new GuestBookService().selectList();
		
		// 전달할 정보 
		req.setAttribute("books", list);
		RequestDispatcher rd = req.getRequestDispatcher("view/guestbook.jsp");
		// 정보 전달
		rd.forward(req, resp);
	}

	/*
	 * Override / Overload
	 * 
	 * Override : 상속(extends)시 부모가 가진 메소드를 자식이 사용하는 것
	 * Overload : 기술이 아니고 행위, 같은 이름을 가진 메소드이나 매개변수가 다름(하는 일(행위)은 비슷하나 주고받는 인자가 다름)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. form이 보낸 정보 받아오기 (인코딩해서 받아옴)
		req.setCharacterEncoding("UTF-8");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		
		// 2. 데이터베이스 insert
		new GuestBookService().insertOne(author, content);
		
		// 3. 목록으로 돌아가기
		resp.sendRedirect("/book");
		
	}
	
	
}
