package kr.spring.onclass.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.onclass.vo.OnclassVO;
import kr.spring.onclass.vo.OnlikeVO;
import kr.spring.onclass.vo.OstarReplyVO;
import kr.spring.onclass.vo.OstarVO;
import kr.spring.onclass.vo.UploadFileVO;
import kr.spring.qna.vo.OqnaReplyVO;
import kr.spring.user.vo.UserVO;

public interface OnclassMapper {
	public List<OnclassVO> selectList(Map<String,Object> map);
	public List<OnclassVO> hitList(Map<String, Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("insert into onclass (on_num, user_num,on_name,category_num,on_price,on_content) "
			+ "values(#{on_num},#{user_num},#{on_name},#{category_num},#{on_price},#{on_content})")
	public void insertOnclass(OnclassVO onclassVO);
	@Select("select * from onclass where on_num = #{on_num}")
	public OnclassVO selectOnclass(Integer on_num);
	@Select("select count(*) from onclass")
	public int getOnclassCount();
	public List<OnclassVO> getOnclassList(Map<String,Object> map);
	@Select("select * from onclass where user_num = #{num}")
	public OnclassVO getOnclass(int num);
	public void updateOnclass(OnclassVO onclassVO);
	@Delete("delete from onclass where on_num = #{num}")
	public void deleteOnclass(Integer on_num);
	@Update("UPDATE onclass SET hit=hit+1 WHERE on_num=#{on_num}")
	public void updateHit(Integer on_num);
	//프로필정보 출력
	@Select("select * from ouser o join ouser_detail z on o.user_num = z.user_num where o.user_num = #{user_num}")
	public UserVO getProfile(int user_num);
	//인서트 온넘 생성
	@Select("select onclass_seq.nextval from dual")
	public int selectOn_num();
	
	
	/////////////////////////찜하기 시작////////////////////////////
	//찜 눌렀는지 확인
	@Select("select * from onlike where user_num = #{user_num} and on_num = #{on_num}")
	public OnlikeVO selectLike(@Param("user_num") Integer user_num,@Param("on_num") Integer on_num);
	//찜취소
	@Delete("delete from onlike where onlike_num = #{onlike_num}")
	public void deleteLike(int onlike_num);
	//찜 하기
	@Insert("insert into onlike (onlike_num,user_num,on_num,olike) values(onlike_seq.nextval,#{user_num},#{on_num},1)")
	public void insertLike(@Param("user_num") Integer user_num,@Param("on_num") Integer on_num);
	//찜한 갯수(모든 이용자)
	@Select("select count(*) from onlike where on_num = #{on_num}")
	public int selectLikeCount(Integer on_num);
	/////////////////////////찜하기 끝////////////////////////////	
	
	//평점 부모글
	@Select("select * from ostar where user_num = #{user_num}") 
	public OstarVO selectQna(Integer user_num);
	@Insert("insert into ostar (ostar_num,user_num,on_num,text,rating) values(ostar_seq.nextval,#{user_num},#{on_num},#{text},#{rating})")
	public void insertqna(OstarVO ostarVO);
	//평점 평균
	@Select("select NVL(avg(rating),0) from ostar where on_num = #{on_num}")
	public int avgQna(Integer on_num);
	//평점 리스트
	public List<OstarVO> listAll(Map<String, Object> map);
	//평점 카운트
	public int selectRowCountRating(int on_num);
	//평점 수정
	@Update("update ostar set text = #{text} ,rating = #{rating} where ostar_num = #{ostar_num}")
	public void updateOstar(OstarVO ostarVO);
	//평점 삭제
	@Delete("delete from ostar where ostar_num = #{ostar_num}")
	public void deleteOstar(int ostar_num);
	//ostar_num에 해당하는 정보출력
	@Select("select * from ostar where ostar_num = #{ostar_num}")
	public OstarVO selectOstar(Integer ostar_num);
	//평점에 해당하는 댓글 가져오기
	@Select("select * from ostar_reply where ostar_num = #{ostar_num}")
	public OstarReplyVO selectOstarReply(Integer ostar_num);
	
	
	
	//평점 댓글
	public List<OstarReplyVO> selectListReply(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM ostar_reply b JOIN ouser o "
			+ "ON b.user_num=o.user_num WHERE b.ostar_num=#{ostar_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Select("select * from ostar_reply where ore_num = #{ore_num}")
	public OstarReplyVO selectReply(Integer re_num);
	@Insert("insert into ostar_reply (ore_num,ore_content,ostar_num,user_num)"
			+ "values(ostar_reply_seq.nextval,#{ore_content},#{ostar_num},#{user_num})")
	public void insertReply(OstarReplyVO ostarReply);
	@Update("update ostar_reply set ore_content = #{ore_content} where ore_num = #{ore_num}")
	public void updateReply(OstarReplyVO ostarReply);
	@Delete("delete from ostar_reply where ore_num = #{ore_num}")
	public void deleteReply(Integer re_num);
	@Delete("delete from ostar_reply where ostar_num = #{ostar_num}")
	public void deleteReplyByQnaNum(Integer ostar_num);

	
	//파일 다중 업로드
	public void uploadFile(HashMap<String, Object> hm);
	//currval 미리 생성
	@Select("select onclass_seq.nextval from dual")
	public int currSelect();
	//파일 불러오기
	@Select("select * from uploadfile where on_num = #{on_num}")
	public List<UploadFileVO> selectFile(int on_num);

}
