package kr.spring.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import kr.spring.qna.vo.OnqnaVO;

public interface OnqnaMapper {

	//부모글
	
	//글 목록
	public List<OnqnaVO> getOnqnaList(Map<String,Object> map);
	
	//글 목록 - rowCount
	public int selectOnqnaRowCount(Map<String,Object> map);
	
	/*=============1.글등록==============*/
	@Insert("INSERT INTO onqna(onqna_num, on_num, user_num, title, content, uploadfile, filename) "
			+ "VALUES(onqna_seq.nextval,#{on_num},#{user_num},#{title},#{content},#{uploadfile},#{filename})")
	public void insertOnqna(OnqnaVO Onqna);
	
	/*=============글상세==============*/
	public OnqnaVO selectOnqna(Integer Onqna_num);
	
	/*=============조회수==============*/
	public void updateOnqnaHit(Integer Onqna_num);
	
	/*=============글수정(OnqnaMapper.xml에 명시)==============*/
	public void updateOnqna(OnqnaVO Onqna);
	
	/*=============글삭제==============*/
	public void deleteOnqna(int num);
	
	/*=============파일삭제==============*/
	public void deleteOnqnaFile(Integer Onqna_num);
	
	
	
	//댓글
	
}
