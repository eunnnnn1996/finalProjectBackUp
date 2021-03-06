package kr.spring.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.main.dao.MainMapper;
import kr.spring.onclass.dao.OnclassMapper;
import kr.spring.onclass.vo.OnclassVO;
@Service
@Transactional
public class MainServiceImpl implements MainService{
	
	@Autowired
	MainMapper mainMapper;
	
	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mainMapper.selectRowCount(map);
	}

	@Override
	public int getMainCount() {
		// TODO Auto-generated method stub
		return mainMapper.getOnclassCount();
	}

	@Override
	public List<OnclassVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mainMapper.selectList(map);
	}

}
