package kr.spring.kit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.kit.vo.KitVO;
import kr.spring.kit.vo.KiteLikeVO;

public interface KitMapper {
	//키트목록
	public List<KitVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	//키트등록
	@Insert("INSERT INTO okit(kit_num,user_num,kit_name,kit_price,kit_quantity,kit_content,uploadfile,filename) "
			+ "VALUES (okit_seq.nextval,#{user_num},#{kit_name},#{kit_price},#{kit_quantity},#{kit_content},#{uploadfile},#{filename})") 
	public void insertKit(KitVO kit);
	//키트detail
    @Select("SELECT * FROM okit k JOIN ouser u ON k.user_num = u.user_num WHERE k.kit_num=#{kit_num}")
	public KitVO selectKit(Integer kit_num);
	//키트 조회수
	@Update("UPDATE okit SET hit=hit+1 WHERE kit_num=#{kit_num}")
	public void updateHit(Integer kit_num);	
	//키트 수정
	public void updateKit(KitVO kit);
	//키트 삭제
	@Delete("DELETE FROM okit WHERE kit_num=#{kit_num}")
	public void deleteKit(Integer kit_num);
	//파일삭제
	@Update("UPDATE okit SET uploadfile='',filename='' WHERE kit_num=#{kit_num}")
	public void deleteFile(Integer kit_num);
	
	//키트 찜하기
	@Insert("INSERT INTO okitlike (kitlike_num, kit_num, user_num) "
				+ "VALUES (okitlike_seq.NEXTVAL,#{kit_num},#{user_num}")
	public void insertKitLike(KiteLikeVO like);
}
