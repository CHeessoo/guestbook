package guest_book_02.model.service;

import java.sql.Connection;
import java.util.List;

import guest_book_02.model.dao.GuestBookDao;
import guest_book_02.model.vo.GuestBookVo;

import static guest_book_02.util.JDBCTemplate.getConnection;
import static guest_book_02.util.JDBCTemplate.close;

public class GuestBookService {
	
	/* 새로운 객체를 생성해서 이용
	 * 
	 * 단점
	 * new 코드를 사용할 때마다 메모리 용량을 사용하게 된다.
	 */
//	public List<GuestBookVo> selectList() {
//		Connection conn = getConnection();
//		List<GuestBookVo> list = new GuestBookDao().selectList(conn);
//		close(conn);
//		return list;
//	}
	
	/*
	 * 의존성 주입 (Dependency Injection, DI)
	 * 
	 * 객체간의 의존성이 존재한다는 의미
	 * 
	 * 장점
	 * 1. 메모리 사용을 줄일 수 있다.
	 * 2. 직관적으로 확인이 가능하다.
	 */
	
	/*
	 * 제어의 역전 (Inversion Of Control, IoC)
	 * 
	 * 원래는 개발자가 해줘야 했던 의존성 주입을 스프링이 대신 해줌으로 제어가 역전 되었다는 의미
	 */
	public List<GuestBookVo> selectList(GuestBookDao dao) {
		Connection conn = getConnection();
		List<GuestBookVo> list = dao.selectList(conn);
		close(conn);
		return list;
	}
	
	public void insertOne(String author, String content) {
		Connection conn = getConnection();
		new GuestBookDao().insertOne(conn, author, content);
		close(conn);
	}

}
