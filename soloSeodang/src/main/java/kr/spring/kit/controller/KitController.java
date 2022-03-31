package kr.spring.kit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import kr.spring.kit.service.KitService;
import kr.spring.kit.vo.KitVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class KitController {
	private static final Logger logger = LoggerFactory.getLogger(KitController.class);
	
	@Autowired
	private KitService kitService;
	
	//자바빈 초기화
	@ModelAttribute
	public KitVO initCommand() {
		return new KitVO();
	};
	//kit 등록
	@GetMapping("/kit/kitWrite.do")
	public String form() {
		return "kitWrite";
	}
	//글 등록 폼에서 전송된 데이터 처리
	@PostMapping("/kit/kitWrite.do")
		public String submit(@Valid KitVO kitVO,
				             BindingResult result,
				             HttpSession session,
				             HttpServletRequest request) {
			logger.info("<<글 저장>> : " + kitVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				return form();
			}
			
			Integer user_num = (Integer)session.getAttribute("session_user_num");
			logger.info("<<회원번호>> : " + user_num);
			//회원 번호 셋팅
			kitVO.setUser_num(user_num);
			
			//글쓰기
			kitService.insertKit(kitVO);
			
			return "redirect:/kit/kitList.do";
		}
	
	    //Kit 목록
		@RequestMapping("/kit/kitList.do")
		public ModelAndView process(
				@RequestParam(value="pageNum",defaultValue="1")
				int currentPage,
				@RequestParam(value="keyfield",defaultValue="")
				String keyfield,
				@RequestParam(value="keyword",defaultValue="")
				String keyword) {
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keyfielld",keyfield);
			map.put("keyword",keyword);
			
			int count = kitService.selectRowCount(map);
			
			PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,20,10,"kitList.do");
			
			map.put("start",page.getStartCount());
			map.put("end",page.getEndCount());
			
			List<KitVO> list = null;
			if(count > 0) {
				list = kitService.selectList(map);
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("kitList");
			mav.addObject("count", count);
			mav.addObject("list",list);
			mav.addObject("pagingHtml",page.getPagingHtml());
			
			return mav;
		}
			
			//kit 상세
			@RequestMapping("/kit/kitDetail.do")
			public ModelAndView process(@RequestParam int kit_num) {
				logger.info("<<게시판 글 상세 - 글 번호>> : " + kit_num);
				
				//해당 글의 조회수 증가
				kitService.updateHit(kit_num);
				
				KitVO kit = kitService.selectKit(kit_num);
				//타이틀 HTML 불허
				kit.setKit_name(StringUtil.useNoHtml(kit.getKit_name()));
				                        //타일스 설정      속성명      속성값
				return new ModelAndView("kitDetail","kit",kit);
			}
			//이미지 출력
			@RequestMapping("/kit/imageView.do")
			public ModelAndView viewImage(@RequestParam int kit_num) {
				KitVO kit = kitService.selectKit(kit_num);
				
				ModelAndView mav = new ModelAndView();
				mav.setViewName("imageView");
				mav.addObject("imageFile",kit.getUploadfile());
				mav.addObject("filename", kit.getFilename());
				return mav;
			}
			//파일 다운로드
			@RequestMapping("/kit/file.do")
			public ModelAndView download(@RequestParam int kit_num) {
				KitVO kit = kitService.selectKit(kit_num);
				
				ModelAndView mav = new ModelAndView();
				mav.setViewName("downloadView");
				mav.addObject("downloadFile", kit.getUploadfile());
				mav.addObject("filename", kit.getFilename());
				
				return mav;
			}
				
			//kit 수정
			@GetMapping("/kit/kitUpdate.do")
			public String formUpdate(@RequestParam int kit_num, Model model) {
				KitVO vo = kitService.selectKit(kit_num);
				model.addAttribute("kitVO",vo);
				return "kitUpdate";
			}
			//수정form 데이터 처리
			@PostMapping("/kit/kitUpdate.do")
			public String submitUpdate(@Valid KitVO kit,BindingResult result,
					                     HttpServletRequest request, Model model) {
				
				logger.info("<<kit 정보 수정>>" + kit);
				
				if(result.hasErrors()) {
					KitVO vo = kitService.selectKit(kit.getKit_num());
					kit.setFilename(vo.getFilename());
					
					return "kitUpdate";
				}
				
				kitService.updateKit(kit);
				
				model.addAttribute("message","글수정완료");
				model.addAttribute("url",request.getContextPath()+"/kit/kitList.do");
			
				return "common/resultView";
			}
			
			//글 삭제
			@RequestMapping("/kit/kitDelete.do")
			public String submitDelete(@RequestParam int kit_num) {
				kitService.deleteKit(kit_num);
				return "redirect:/kit/kitList.do";
			}
			
			//찜하기
			
	}


