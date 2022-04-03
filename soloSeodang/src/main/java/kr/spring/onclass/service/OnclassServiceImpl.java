package kr.spring.onclass.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.onclass.dao.OnclassMapper;
import kr.spring.onclass.vo.OnclassVO;
import kr.spring.onclass.vo.OnlikeVO;
import kr.spring.onclass.vo.OstarReplyVO;
import kr.spring.onclass.vo.OstarVO;
import kr.spring.onclass.vo.UploadFileVO;
import kr.spring.user.vo.UserVO;


@Service
@Transactional
public class OnclassServiceImpl implements OnclassService{
	
	@Autowired
	OnclassMapper onclassMapper;
	
	@Override
	public void insertOnclass(OnclassVO onclassVO) {
		// TODO Auto-generated method stub

		 //onclassVO.setOn_num(onclassMapper.selectOn_num());
		 onclassMapper.insertOnclass(onclassVO);

	}

	@Override
	public int getOnclassCount() {
		// TODO Auto-generated method stub
		return onclassMapper.getOnclassCount();
	}

	@Override
	public OnclassVO getOnclass(int num) {
		// TODO Auto-generated method stub
		return onclassMapper.getOnclass(num);
	}

	@Override
	public void updateOnclass(OnclassVO onclassVO) {
		// TODO Auto-generated method stub
		onclassMapper.updateOnclass(onclassVO);
	}

	@Override
	public void deleteOnclass(Integer on_num) {
		// TODO Auto-generated method stub
		onclassMapper.deleteOnclass(on_num);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return onclassMapper.selectRowCount(map);
	}

	@Override
	public List<OnclassVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return onclassMapper.selectList(map);
	}

	@Override
	public OnclassVO selectOnclass(Integer on_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectOnclass(on_num);
	}

	@Override
	public OnlikeVO selectLike(Integer user_num,Integer on_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectLike(user_num,on_num);
	}

	@Override
	public void deleteLike(int onlike_num) {
		// TODO Auto-generated method stub
		onclassMapper.deleteLike(onlike_num);
	}

	@Override
	public void insertLike(Integer user_num,Integer on_num) {
		// TODO Auto-generated method stub
		onclassMapper.insertLike(user_num,on_num);
	}

	@Override
	public void updateHit(Integer on_num) {
		// TODO Auto-generated method stub
		onclassMapper.updateHit(on_num);
	}

	@Override
	public List<OnclassVO> hitList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return onclassMapper.hitList(map);
	}

	@Override
	public OstarVO selectQna(Integer user_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectQna(user_num);
	}

	@Override
	public void insertqna(OstarVO ostarvo) {
		// TODO Auto-generated method stub
		onclassMapper.insertqna(ostarvo);
	}

	@Override
	public int avgQna(Integer on_num) {
		// TODO Auto-generated method stub
		return onclassMapper.avgQna(on_num);
	}
	
	//평점 리스트
	@Override
	public List<OstarVO> listALL(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return onclassMapper.listAll(map);
	}
	//글 삭제 할 때 평점남긴것도 같이 삭제
	
	//프로필 정보 출력
	@Override
	public UserVO getProfile(int user_num) {
		// TODO Auto-generated method stub
		return onclassMapper.getProfile(user_num);
	}

	@Override
	public int selectLikeCount(Integer on_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectLikeCount(on_num);
	}

	@Override
	public int selectRowCountRating(int on_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectRowCountRating(on_num);
	}
	
	@Override
	public void updateOstar(OstarVO ostarVO) {
		// TODO Auto-generated method stub
		onclassMapper.updateOstar(ostarVO);
	}

	@Override
	public void deleteOstar(int ostar_num) {
		// TODO Auto-generated method stub
		//댓글 먼저 삭제

		onclassMapper.deleteReplyByQnaNum(ostar_num);
		onclassMapper.deleteOstar(ostar_num);
	}
	@Override
	public List<OstarReplyVO> selectListReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return onclassMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return onclassMapper.selectRowCountReply(map);
	}

	@Override
	public OstarReplyVO selectReply(Integer re_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectReply(re_num);
	}

	@Override
	public void insertReply(OstarReplyVO ostarReply) {
		// TODO Auto-generated method stub
		onclassMapper.insertReply(ostarReply);
	}

	@Override
	public void updateReply(OstarReplyVO ostarReply) {
		// TODO Auto-generated method stub
		onclassMapper.updateReply(ostarReply);
	}

	@Override
	public void deleteReply(Integer re_num) {
		// TODO Auto-generated method stub
		onclassMapper.deleteReply(re_num);
	}

	@Override
	public OstarVO selectOstar(Integer ostar_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectOstar(ostar_num);
	}

	@Override
	public OstarReplyVO selectOstarReply(Integer ostar_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectOstarReply(ostar_num);
	}

	@Override
	public int selectOn_num() {
		// TODO Auto-generated method stub
		return onclassMapper.selectOn_num();
	}

	
	//다중 파일 업로드
	@Override
	public void fileUpload(String originalfileName, String saveFileName, long fileSize, int on_num) {
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    hm.put("originalfileName", originalfileName);
	    hm.put("saveFileName", saveFileName);
	    hm.put("fileSize", fileSize);
	    hm.put("on_num", on_num);
	    
	    
	    onclassMapper.uploadFile(hm);
	}

	@Override
	public int currSelect() {
		// TODO Auto-generated method stub
		return onclassMapper.currSelect();
	}

	@Override
	public List<UploadFileVO> selectFile(int on_num) {
		// TODO Auto-generated method stub
		return onclassMapper.selectFile(on_num);
	}

}
