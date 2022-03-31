package kr.spring.myclass.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.myclass.dao.MyclassMapper;
import kr.spring.myclass.vo.MyclassVO;
import kr.spring.myclass.vo.PaymentVO;
import kr.spring.onclass.vo.OnclassVO;

@Service
@Transactional
public class MyclassServiceImpl implements MyclassService{
	
	@Autowired
	MyclassMapper myclassMapper;
	
	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return myclassMapper.selectRowCount(map);
	}

	@Override
	public List<MyclassVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return myclassMapper.selectList(map);
	}

	@Override
	public void insertRegister(PaymentVO paymentVO) {
		// TODO Auto-generated method stub
		paymentVO.setOnreg_num(myclassMapper.selectSeq());
		myclassMapper.insertRegister(paymentVO);
		myclassMapper.insertDetailRegister(paymentVO);
	}

	@Override
	public int overlap(int on_num,int user_num) {
		// TODO Auto-generated method stub
		return myclassMapper.overlap(on_num,user_num);
	}

	@Override
	public List<PaymentVO> selectRegisterList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return myclassMapper.selectRegisterList(map);
	}

	@Override
	public int selectRowCount2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return myclassMapper.selectRowCount2(map);
	}

	@Override
	public void deletePayment(int onreg_num, int user_num) {
		// TODO Auto-generated method stub
		myclassMapper.deletePayment(onreg_num, user_num);
		myclassMapper.updateStatusPayment(onreg_num);
	}

	@Override
	public PaymentVO selectPayment(PaymentVO paymentVO) {
		// TODO Auto-generated method stub
		return myclassMapper.selectPayment(paymentVO);
	}



}
