package kr.spring.myclass.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.myclass.vo.MyclassVO;
import kr.spring.myclass.vo.PaymentVO;

public interface MyclassMapper {
	//내가 올린 강의 (선생님)
	public List<MyclassVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	//구매한 과목 저장(학생)
	//구매목록 시퀀스 생성
	@Select("select onreg_seq.nextval from dual")
	public int selectSeq();
	//onreg 저장
	@Insert("insert into onreg(onreg_num,user_num) values(#{onreg_num},#{user_num})")
	public void insertRegister(PaymentVO paymentVO);
	//onreg_detail 저장
	@Insert("insert into onreg_detail(onreg_num,on_num,on_payment,on_status) "
			+ "values(#{onreg_num},#{on_num},#{on_payment},#{on_status})")
	public void insertDetailRegister(PaymentVO paymentVO);
	//중복된 강의 확인
	@Select("select count(*) from onreg a join onreg_detail z on a.onreg_num = z.onreg_num "
			+ "where z.on_num = #{on_num} and a.user_num = #{user_num}")
	public int overlap(@Param("on_num") int on_num,@Param("user_num") int user_num);
	//구매 목록
	public List<PaymentVO> selectRegisterList(Map<String,Object> map);
	public int selectRowCount2(Map<String,Object> map);
	//구매 취소
	@Update("update onreg set on_moregdate = SYSDATE where onreg_num=#{onreg_num} and user_num = #{user_num}")
	public void deletePayment(@Param("onreg_num") int onreg_num,@Param("user_num") int user_num);
	@Update("update onreg_detail set on_status = '2' where onreg_num=#{onreg_num}")
	public void updateStatusPayment(@Param("onreg_num") int onreg_num);
	//내 구매목록 조회
	@Select("select * from onreg a join onreg_detail b on a.onreg_num = b.onreg_num "
			+ "where a.user_num = #{user_num} and a.onreg_num = #{onreg_num}")
	public PaymentVO selectPayment(PaymentVO paymentVO);
}
