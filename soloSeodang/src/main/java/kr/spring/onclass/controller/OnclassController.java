package kr.spring.onclass.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.onclass.service.OnclassService;
import kr.spring.onclass.vo.OnclassVO;
import kr.spring.onclass.vo.OstarVO;
import kr.spring.onclass.vo.UploadFileVO;
import kr.spring.user.controller.UserController;
import kr.spring.user.service.UserService;
import kr.spring.user.vo.UserVO;
import kr.spring.util.PagingUtil;

@Controller
public class OnclassController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private OnclassService onclassService;
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public OnclassVO initCommand() {
		return new OnclassVO();
	}
	
	//온라인 클래스 목록
	@RequestMapping("/onclass/onclassList.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword, @RequestParam(value="category",defaultValue="0") int category
			) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("category", category);
		
		int count = onclassService.selectRowCount(map);
		
		PagingUtil page = new PagingUtil(keyfield,keyword,
                currentPage,count,6,10,"onclassList.do");

		map.put("start",page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<OnclassVO> list = null;
			if(count > 0) {
				list = onclassService.selectList(map);
			}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("onclassList");
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	
	//수업 등록
	@GetMapping("/onclass/onclassInsert.do")
	public String insertForm() {
		return "onclassInsert";
	}
	
	@PostMapping("/onclass/onclassInsert.do")
	public String insert(@Valid OnclassVO onclassVO,BindingResult result,HttpSession session,
			MultipartHttpServletRequest mhsq) throws IllegalStateException, IOException{

		Integer user_num = (Integer)session.getAttribute("session_user_num");
		onclassVO.setUser_num(user_num);
		
		int on_num = onclassService.currSelect();
		System.out.println("온넘 찍기 : "+on_num);
		
		String realFolder = "C:/javaWork/image/";
        File dir = new File(realFolder);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        
     // 넘어온 파일을 리스트로 저장
        List<MultipartFile> mf = mhsq.getFiles("uploadFile");
        if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
             
        } else {
            for (int i = 0; i < mf.size(); i++) {
            	
                // 파일 중복명 처리
                String genId = UUID.randomUUID().toString();
                // 본래 파일명
                String originalfileName = mf.get(i).getOriginalFilename();
                
                String extension = StringUtils.getFilenameExtension(originalfileName);
                 
                String saveFileName = genId + "." + extension;
                // 저장되는 파일 이름
 
                String savePath = realFolder + saveFileName; // 저장 될 파일 경로
 
                long fileSize = mf.get(i).getSize(); // 파일 사이즈
 
                mf.get(i).transferTo(new File(savePath)); // 파일 저장
                
                onclassService.fileUpload(originalfileName, saveFileName, fileSize, on_num);
            }
        }
		
		
		if(result.hasErrors()) {
			return insertForm();
		}
		
		onclassVO.setOn_num(on_num);
		System.out.println("VO에 넣은 온넘 : " + onclassVO.getOn_num());
		onclassService.insertOnclass(onclassVO);
		
		return "redirect:/onclass/onclassList.do";
	}



	@GetMapping("/onclass/onclassModify.do")
	public String updateForm(@RequestParam int on_num, Model model) {
		OnclassVO onclassVO = onclassService.selectOnclass(on_num);
		model.addAttribute("onclassVO",onclassVO);
		
		return "onclassModify";		
	}
	@PostMapping("/onclass/onclassModify.do")
	public String update(@Valid OnclassVO onclassVO,BindingResult result,HttpServletRequest request,Model model) {
		
		logger.info("!!온넘확인!! : " + onclassVO);
		
		if(result.hasErrors()) {
			return "onclassModify";
		}
		
		onclassService.updateOnclass(onclassVO);

		model.addAttribute("message", "글 수정 완료!!");
		model.addAttribute("url", request.getContextPath() + "/onclass/onclassList.do");
		
		return "common/resultView";
	}
	
	@GetMapping("/onclass/onclassDelete.do")
	public String delete(int on_num,OnclassVO onV) {
		
		logger.info("!!온넘확인!! : " + onV);
		
		onV.setOn_num(on_num);
		return "onclassDelete";
	}
	
	@PostMapping("/onclass/onclassDelete.do")
	public String deleteCheck(UserVO uvo,OnclassVO vo,String deletePasswd,HttpSession session,HttpServletRequest request,Model model) {
		
		logger.info("!!온넘확인!! : " + vo);
		
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		int on_num = vo.getOn_num();
		UserVO zvo = userService.selectUser(user_num);
		if(deletePasswd.equals(zvo.getPasswd())) {
			onclassService.deleteOnclass(on_num);
			model.addAttribute("message", "게시물 삭제 완료");
			model.addAttribute("url", request.getContextPath() + "/onclass/onclassList.do");
		}else {
			model.addAttribute("message", "게시물 삭제 실패");
			model.addAttribute("url", request.getContextPath() + "/onclass/onclassList.do");
		}
		return "common/resultView";
	}
	
	@GetMapping("/onclass/onclassDetail.do")
	public ModelAndView detailForm(@RequestParam(value="pageNum",defaultValue="1")
									int currentPage,HttpSession session, 
									@ModelAttribute("ostarVO") OstarVO ostarVO) {
		
		int on_num = ostarVO.getOn_num();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		int count = onclassService.selectRowCountRating(on_num);

		PagingUtil page = new PagingUtil(currentPage,count,6,10,"onclassDetail.do","&on_num="+on_num);

		
		map.put("start",page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("on_num", on_num);
		
		List<OstarVO> list = null;
		if(count > 0) {
			list = onclassService.listALL(map);
		}

		
		onclassService.updateHit(on_num); //조회수
		
		//다중업로드 파일 가져오기 시작
		List<UploadFileVO> uplist = onclassService.selectFile(on_num);
		//다중업로드 파일 가져오기 끝
		
		OnclassVO oVO = onclassService.selectOnclass(on_num);
		/* oVO.setAvgqna(onclassService.avgQna(on_num)); */
	
		ModelAndView mav = new ModelAndView();

		//프로필 뿌림
		mav.setViewName("onclassDetail");
		
		mav.addObject("onclass",oVO);		
		mav.addObject("count", count);
		mav.addObject("list",list);
		//다중 업로드
		mav.addObject("uplist",uplist);
		
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	@PostMapping("/onclass/ratingInsert.do")
	public String qna(@ModelAttribute("ostarVO") OstarVO ostarVO,int on_num,HttpSession session,Model model) {
		
		logger.info("!!온넘확인!! : " + ostarVO);
		
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		ostarVO.setUser_num(user_num);
		onclassService.insertqna(ostarVO);

		return "redirect:/onclass/onclassDetail.do?on_num="+on_num;
	}
	@PostMapping("/onclass/updateOstar.do")
	public String updateOstar(OstarVO ostarVO,int ostar_num) {
		
		logger.info("!!온넘확인!! : " + ostarVO);
		
		String rate = ostarVO.getRating();
		String text = ostarVO.getText();
		int on_num = ostarVO.getOn_num();
		
		ostarVO.setRating(rate);
		ostarVO.setText(text);
		onclassService.updateOstar(ostarVO);
		
		return "redirect:/onclass/onclassDetail.do?on_num="+on_num;
	}
	@GetMapping("/onclass/deleteOstar.do")
	public String deleteOstar(Integer ostar_num) {

		onclassService.deleteOstar(ostar_num);

		
		return "redirect:/onclass/onclassList.do"; 
	}
	
	@GetMapping("/onclass/ratingWrite.do")
	public String rateWrite(OstarVO ostarVO,Model model) {
		int ostar_num = ostarVO.getOstar_num();
		ostarVO = onclassService.selectOstar(ostar_num);
		
		model.addAttribute("ostar", ostarVO);
		
		return "ratingWrite";
	}
	
	
	
	@RequestMapping("/onclass/imageView.do")
	public ModelAndView viewImage(@RequestParam int on_num) {
		OnclassVO onclass = onclassService.selectOnclass(on_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",onclass.getUploadfile());
		mav.addObject("filename", onclass.getFilename());
		return mav;
	}

}

















