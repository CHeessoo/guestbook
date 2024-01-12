package com.goodee.guestbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	// HomeController 클래스에 로깅 시스템을 사용한다는 의미
	// private : 내부사용
	// static  : 처음에 한번 만들고나면 다시 만들지 않음 (클래스에 고정된 변수 또는 메소드)
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);  // 형태는 고정 (어떤 클래스를 사용하냐에따라 클래스명 부분은 달라질 수 있다.)
	
	// 경로(path)가 빈 문자열 또는 /로 끝남
	@RequestMapping(value= {"","/"}, method=RequestMethod.GET)
	public String home() {
		LOGGER.info("[Homecontroller] home();");  // Logger 객체가 가진 info 메소드를 사용 (로거 레벨 중 info단계 사용)
		return "home";
	}
	
}
