package kr.spring.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.onclass.vo.OnclassVO;

public interface MainMapper {
	public List<OnclassVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Select("select count(*) from onclass")
	public int getOnclassCount();
}
