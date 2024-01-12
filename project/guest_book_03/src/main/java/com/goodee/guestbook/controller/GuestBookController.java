package com.goodee.guestbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.guestbook.model.service.GuestBookService;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Controller
@RequestMapping("/book")
public class GuestBookController {
	
	@Autowired
	GuestBookService service;
	
	// 로그 찍기
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(GuestBookController.class);
	

	/*
	 * 1. ModelAndView
	 * 2. Model
	 * 3. String
	 */
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView guestBookList() {
		// 로그
		LOGGER.info("[GuestBookController] guestBookList();");
		
		// 1. 목록
		// 2. 화면
		
		List<GuestBookVo> list = service.selectBookList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listup");
		mav.addObject("list", list);
		return mav;
	}
	
	
//	1. RequestParam으로 각각의 정보를 하나씩 가져오기 (단순히 하나의 정보만 불러올 때 유용)
//	@RequestMapping(method=RequestMethod.POST)
//	public String guestBookAdd(@RequestParam("author") String author,
//			@RequestParam("content") String content) {
//		// 1. 데이터 추가
//		// 2. 화면 이동
//		
//		service.insertBookOne();
//		
//		// request 정보를 받아오는 법
//		// service, dao에 전달하는 법
//		
//		return "";
//	}
	
	
//	 2. RequestParam으로 모든 정보 Map으로 가져오기 (한번에 여러개의 정보를 가져와서 사용할 때 유용)
//	@RequestMapping(method=RequestMethod.POST)
//	public String guestBookAdd(@RequestParam Map<String, String> param) {
//		String author = param.get("author");
//		String content = param.get("content");
//		service.insertBookOne();
//		return "";
//	}
	
	
//	 3. Vo로 가져오기
	@RequestMapping(method=RequestMethod.POST)
	public String guestBookAdd(GuestBookVo vo) {
		LOGGER.info("[GuestBookController] guestBookAdd();");
		service.insertBookOne(vo);
		return "redirect:/book";
	}
	
	
	
	
	
}
