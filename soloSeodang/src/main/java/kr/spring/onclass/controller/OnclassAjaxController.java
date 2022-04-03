package kr.spring.onclass.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.onclass.service.OnclassService;
import kr.spring.onclass.vo.OnlikeVO;
import kr.spring.onclass.vo.OstarReplyVO;
import kr.spring.user.controller.UserController;
import kr.spring.util.PagingUtil;



@Controller
public class OnclassAjaxController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private int rowCount = 10;
	
	@Autowired
	private OnclassService onclassService;
	
	@RequestMapping("/onclass/like.do")
	@ResponseBody	
	public Map<String, Object> likeForm(int on_num,HttpSession session) {
		Integer user_num = (Integer)session.getAttribute("session_user_num");
	
		Map<String,Object> map = new HashMap<String,Object>();

		if(user_num == null) {//로그인 안됨
			map.put("result", "logout");
		}else {
			OnlikeVO vo = onclassService.selectLike(user_num,on_num);	
		if(vo!=null) { //이미 추천을 한 경우
			onclassService.deleteLike(vo.getOnlike_num());
			map.put("result", "success");
			map.put("status", "noFav");
			map.put("count",onclassService.selectLikeCount(on_num));
		}else{
			onclassService.insertLike(user_num,on_num);
			map.put("result", "success");
			map.put("status", "yesFav");
			map.put("count",onclassService.selectLikeCount(on_num));
			}
		}
		return map; 
	}
	
	//게시물 들어갔을때 하트 빈하트 여부
	@RequestMapping("/onclass/getFav.do")
	@ResponseBody	
	public Map<String, Object> execute(int on_num,HttpSession session,OnlikeVO onlikeVO) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		
		if(user_num == null) {//로그인 안됨
			map.put("result", "success"); //좋아요 표시
			map.put("status", "noFav"); //빈 하트 표시
			map.put("count", onclassService.selectLikeCount(on_num)); //좋아요 총 개수 표시
		}else {	
			onlikeVO = onclassService.selectLike(user_num, on_num);
			
			if(onlikeVO!=null) { //vo가 null이 아니면 추천을 이미 한 경우
				map.put("result", "success");
				map.put("status", "yesFav"); // 추천하트로 표시
				map.put("count", onclassService.selectLikeCount(on_num)); //좋아요 총 개수 표시
			}else{ // 추천을 안한 경우
				map.put("result", "success");
				map.put("status", "noFav"); //빈 하트 표시 
				map.put("count", onclassService.selectLikeCount(on_num)); //좋아요 총 개수 표시
			}
		}
		return map;
	}
	
	//댓글 구현
	@RequestMapping("/onclass/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(OstarReplyVO ostarReplyVO,HttpSession session,
																HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			map.put("result","logout");
		}else {//로그인 된 경우
			//회원번호 등록
			ostarReplyVO.setUser_num(user_num);
			//댓글 등록
			onclassService.insertReply(ostarReplyVO);
			map.put("result", "success");
		}
		return map;
	}
	
	@RequestMapping("/onclass/ostarListReply.do")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
											@RequestParam int ostar_num,HttpSession session){
		
		logger.info("!!댓글 currentPage : "+ currentPage);
		logger.info("!!댓글 : " + ostar_num);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ostar_num", ostar_num);
		
		int count = onclassService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<OstarReplyVO> list = null;
		if(count > 0) {
			list = onclassService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		Integer user_num = (Integer)session.getAttribute("session_user_num");
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		mapJson.put("user_num", user_num);
		
		return mapJson;	
	}
	
		//댓글 수정
		@RequestMapping("/onclass/updateReply.do")
		@ResponseBody
		public Map<String,String> modifyReply(OstarReplyVO ostarReplyVO,
				                     HttpSession session,
				                     HttpServletRequest request){
			logger.info("<<댓글 수정>> : " + ostarReplyVO);
			
			Map<String,String> map = new HashMap<String,String>();
			
			Integer user_num = (Integer)session.getAttribute("session_user_num");
			OstarReplyVO db_reply = onclassService.selectReply(ostarReplyVO.getOre_num());

			if(user_num==null) {
				//로그인 안 되어 있는 경우
				map.put("result", "logout");
			}else if(user_num !=null && user_num == db_reply.getUser_num()) {
				//로그인 회원번호와 작성자 회원번호 일치
				
				//댓글 수정
				onclassService.updateReply(ostarReplyVO);
				map.put("result", "success");
			}else {
				//로그인 회원번호와 작성자 회원번호 불일치
				map.put("result","wrongAccess");
			}
			return map;
		}
	
		//댓글 삭제
		@RequestMapping("/onclass/deleteReply.do")
		@ResponseBody
		public Map<String,String> deleteReply(
				@RequestParam int ore_num, HttpSession session){
			
			Map<String,String> map = new HashMap<String,String>();
			
			Integer user_num = (Integer)session.getAttribute("session_user_num");
			
			
			OstarReplyVO db_reply = onclassService.selectReply(ore_num); 
			
			if(user_num==null) {//로그인이 되지 않은 경우
				map.put("result", "logout");
			}else if(user_num!=null && user_num == db_reply.getUser_num()) {
				//로그인 되어 있고 로그인한 회원번호와 작성자 회원번호 일치
				onclassService.deleteReply(ore_num);
				map.put("result", "success");
			}else {
				//로그인한 회원번호와 작성자 회원번호 불일치
				map.put("result","wrongAccess");
			}
			
			return map;
		}
		
}
