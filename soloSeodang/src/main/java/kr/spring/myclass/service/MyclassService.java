package kr.spring.myclass.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import kr.spring.myclass.vo.MyclassVO;
import kr.spring.myclass.vo.PaymentVO;

public interface MyclassService {
	//내가 올린 강의(선생님)
	public int selectRowCount(Map<String,Object> map);
	public List<MyclassVO> selectList(Map<String,Object> map);
	//사용자가 구매한 강의
	public void insertRegister(PaymentVO paymentVO);	
	//중복 구매 제한 
	public int overlap(int on_num,int user_num);
	
	//구매한 강의(학생)
	public List<PaymentVO> selectRegisterList(Map<String,Object> map);
	public int selectRowCount2(Map<String,Object> map);
	//구매 취소
	public void deletePayment(int onreg_num, int user_num);
	//내 구매 목록 정보 출력
	public PaymentVO selectPayment(PaymentVO paymentVO);
}
