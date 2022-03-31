package kr.spring.myclass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.myclass.service.MyclassService;
import kr.spring.myclass.vo.MyclassVO;
import kr.spring.onclass.vo.OnclassVO;
import kr.spring.util.PagingUtil;

@Controller
public class MyclassController {
	@Autowired
	private MyclassService myclassService;
	
	@GetMapping("/myclass/myclassMain.do")
	public String mainForm() {
		return "myclassMain";
	}
	
	@RequestMapping("/myclass/myclassList.do")
	public ModelAndView classList(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword, HttpSession session
			) {
		
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		
		int count = myclassService.selectRowCount(map);
		
		PagingUtil page = new PagingUtil(keyfield,keyword,
                currentPage,count,4,10,"myclassList.do");
		map.put("user_num", user_num);
		map.put("start",page.getStartCount());
		map.put("end", page.getEndCount());
		
		
		List<MyclassVO> list = null;
			if(count > 0) {
				list = myclassService.selectList(map);
			}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("myclassList");
		mav.addObject("user_num",user_num);
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	
}
