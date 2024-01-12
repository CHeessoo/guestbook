package com.goodee.guestbook.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.goodee.guestbook.model.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// 로그
	private static final Logger LOGGER =
			LoggerFactory.getLogger(GuestBookDao.class);

	public List<GuestBookVo> selectBookAll() {
		LOGGER.info("[GuestBookDao] selectBookAll();");
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try {
			
			String sql = "SELECT * FROM content_list";
			list = jdbcTemplate.query(sql, new RowMapper<GuestBookVo>() { // (어떤 쿼리를 전달하는지, 어떤 형태의 데이터를 가져오는지)
				@Override
				public GuestBookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					GuestBookVo vo = new GuestBookVo();
					vo.setAuthor(rs.getString("g_author"));
					vo.setContent(rs.getString("g_content"));
					vo.setReg_date(rs.getDate("g_reg_date"));
					return vo;
				}
			});  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public int insertGuestBook(GuestBookVo vo) {
		LOGGER.info("[GuestBookDao] insertGuestBook();");
		
		String sql = "INSERT INTO content_list (g_author, g_content, g_reg_date) "
				+ "VALUES('" + vo.getAuthor() + "', '" + vo.getContent() + "', NOW())";
		int result = -1;
		try {
			
			result = jdbcTemplate.update(sql);  // 수행이 되면 row 가 들어간 수만큼 반환, 수행이 안 됐으면 -1 반환
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
