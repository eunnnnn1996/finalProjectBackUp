package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.main.service.MainService;
import kr.spring.onclass.service.OnclassService;
import kr.spring.onclass.vo.OnclassVO;
import kr.spring.util.PagingUtil;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	/*@RequestMapping("/main/main.do")
	public String main() {
		
		//타일스 설정
		return "main";
	}*/
	
		//온라인 클래스 목록
		@RequestMapping("/main/main.do")
		@ResponseBody
		public ModelAndView process(
				@RequestParam(value="pageNum",defaultValue="1")
				int currentPage,
				@RequestParam(value="keyfield",defaultValue="")
				String keyfield,
				@RequestParam(value="keyword",defaultValue="")
				String keyword,
				@RequestParam(value="category_num",defaultValue="0") int category_num) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("category_num", category_num);
			
			int count = mainService.selectRowCount(map);
			
			PagingUtil page = new PagingUtil(currentPage,count,6,10,"main.do");

			map.put("start",page.getStartCount());
			map.put("end", page.getEndCount());
			
			//카테고리 로그
			System.out.println(category_num);
			
			List<OnclassVO> list = null;
				if(count > 0) {
					list = mainService.selectList(map);
				}

			ModelAndView mav = new ModelAndView();
			mav.setViewName("main");
			mav.addObject("count", count);
			mav.addObject("list",list);
			mav.addObject("pagingHtml", page.getPagingHtml());

			return mav;
		}
}
