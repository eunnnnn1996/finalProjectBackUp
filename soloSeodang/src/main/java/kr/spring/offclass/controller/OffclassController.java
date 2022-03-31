package kr.spring.offclass.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.offclass.service.OffclassService;
import kr.spring.offclass.vo.OffTimetableVO;
import kr.spring.offclass.vo.OffclassVO;
import kr.spring.offclass.vo.OfflikeVO;
import kr.spring.onclass.vo.OnclassVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class OffclassController {
	
	private Logger logger = LoggerFactory.getLogger(OffclassController.class);
	
	@Autowired
	private OffclassService offclassService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public OffclassVO initCommand() {
		return new OffclassVO();
	}
	
	//오프라인 클래스 폼
	@GetMapping("/offclass/offclassOpen.do")
	public String form(HttpSession session) {
		ArrayList<OffTimetableVO> list  =(ArrayList<OffTimetableVO>)session.getAttribute("list");
		session.removeAttribute("list");
		return "offclassOpen";
	}
	
	//오프라인 클래스 등록
	@PostMapping("/offclass/offclassOpen.do")
	public String sumbit(@Valid OffclassVO offclassVO, BindingResult result,HttpSession session) {
		
		logger.info("<<오프라인 클래스 등록>>: "+offclassVO);
		
		if(result.hasErrors()) {
			return "offclassOpen";
		}
		
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		//회원번호 세팅
		offclassVO.setUser_num(user_num);
		
		//글 작성
		ArrayList<OffTimetableVO> list  =(ArrayList<OffTimetableVO>)session.getAttribute("list");
		offclassService.insertOffClass(offclassVO,list);
		session.removeAttribute("list");
		
		return "redirect:/offclass/offclassList.do";
	}
	
	//오프라인 클래스 목록
	@RequestMapping("/offclass/offclassList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue = "1") int currentPage) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = offclassService.selectRowCount(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count, 20, 10, "offclassList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<OffclassVO> list =null;
		if(count>0) {
			list = offclassService.selectListOffClass(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("offclassList");
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//게시판 글 상세
	@RequestMapping("/offclass/offclassDetail.do")
	public ModelAndView processdetail(@RequestParam int off_num,HttpSession session) {
		Integer user_num= (Integer)session.getAttribute("session_user_num");
		ModelAndView mav = new ModelAndView();
		logger.info("<<게시판 글 상세 - 글 번호>>: "+off_num);

		if(user_num!=null) {
			OfflikeVO offLikeVO=offclassService.selectLike(user_num, off_num);
			if(offLikeVO==null) {
				mav.addObject("likecheck",0);
			}else {
				mav.addObject("likecheck",offLikeVO.getOlike());
			}
		}
		List<OffTimetableVO> list = offclassService.selectListOffTimetable(off_num);
		
		OffclassVO offclass=offclassService.selectOffClass(off_num);
		offclass.setOff_name(StringUtil.useNoHtml(offclass.getOff_name()));
		mav.addObject("offclass", offclass);
		mav.addObject("list", list);
		mav.setViewName("offclassDetail");
		
		return mav;
	}
	//이미지 보이기
	@RequestMapping("/offclass/imageView.do")
	public ModelAndView viewImage(@RequestParam int off_num) {
		OffclassVO offclass = offclassService.selectOffClass(off_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",offclass.getOff_uploadfile());
		mav.addObject("filename", offclass.getOff_filename());
		return mav;
	}	
	//오프라인 클래스 수정폼
	@GetMapping("/offclass/offclassUpdate.do")
	public ModelAndView formUpdate(@RequestParam int off_num,HttpSession session) {
		
		OffclassVO offclassVO = offclassService.selectOffClass(off_num);
		logger.info("<<offclassVO >>"+offclassVO);
		List<OffTimetableVO> list = new ArrayList<OffTimetableVO>();
		list = offclassService.selectListOffTimetable(off_num);
		System.out.println(list);
		ModelAndView mav = new ModelAndView();
		if(list!=null) {
			mav.addObject("list", list);
			session.setAttribute("list", list);
		}
		
		
		mav.setViewName("offclassUpdate");
		mav.addObject("offclassVO",offclassVO);
		
		return mav;
	}
	//오프라인 클래스 수정
	@PostMapping("/offclass/offclassUpdate.do")
	public String submitUpdate(@Valid OffclassVO offclassVO,BindingResult result,HttpSession session) {
		
		logger.info("<<offclassVO - off_num>>"+offclassVO);
		
		if(result.hasErrors()) {
			return "offclassUpdate";
		}
		ArrayList<OffTimetableVO> list  =(ArrayList<OffTimetableVO>)session.getAttribute("list");
		offclassService.updateOffClass(offclassVO,list);
		session.removeAttribute("list");
		
		return "redirect:/offclass/offclassDetail.do?off_num="+offclassVO.getOff_num();
	}
	
}
