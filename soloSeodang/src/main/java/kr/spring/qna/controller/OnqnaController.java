package kr.spring.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.qna.service.OnqnaService;
import kr.spring.qna.vo.OnqnaVO;



@Controller
public class OnqnaController {

	private static final Logger logger = LoggerFactory.getLogger(OnqnaController.class);
	
	
	//(1)의존관계 주입
	@Autowired
	private OnqnaService onqnaService;
	
	
	//(2)자바빈 초기화
	@ModelAttribute
	public OnqnaVO initCommand() {
		return new OnqnaVO();
	}
	
	//(3)글등록 form
	@GetMapping("/onqna/onqnaWrite.do")	//view생성
	public String form() { 
		return "onqnaWrite";	//tiles등록
	}
	
	//글 등록 폼에서 전송된 데이터 처리
	@PostMapping("/onqna/onqnaWrite.do")
	public String submit(@Valid OnqnaVO onqnaVO, BindingResult result,
			HttpSession session, HttpServletRequest request) {

		//<1>로그처리
		logger.info("<<Oqna게시판 글 저장>> : " + onqnaVO);
		
		//<2>유효성 체크결과 오류가 있으면 form호출
		if(result.hasErrors()) {
			return form();
		}
		
		//<3>유효성 체크결과 오류가 없을시
		//회원번호 세팅
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		onqnaVO.setUser_num(user_num);
		//글쓰기
		onqnaService.insertOnqna(onqnaVO);
		
		//<4> 글등록 후 목록으로 redirect
		return "redirect:/onqna/onqnaList.do";
	}	
	
	//onqna 글 목록
	@RequestMapping("/onqna/onqnaList.do")
	public ModelAndView process() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("onqnaList");
		
		return mav;
	}
	
	
}
