package kr.spring.kit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.kit.dao.KitMapper;
import kr.spring.kit.vo.KitVO;
import kr.spring.kit.vo.KiteLikeVO;

@Service
public class KitServiceImpl implements KitService{

	@Autowired
	private KitMapper kitMapper;

	
	@Override
	public List<KitVO> selectList(Map<String, Object> map) {
		return  kitMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return kitMapper.selectRowCount(map);
	}

	@Override
	public void insertKit(KitVO kit) {
		kitMapper.insertKit(kit);
		
	}

	@Override
	public KitVO selectKit(Integer kit_num) {
		return kitMapper.selectKit(kit_num);
	}
	
	@Override
	public void updateHit(Integer kit_num) {
		kitMapper.updateHit(kit_num);
		
	}
	
	@Override
	public void updateKit(KitVO kit) {
		kitMapper.updateKit(kit);
		
	}

	@Override
	public void deleteKit(Integer kit_num) {
	    kitMapper.deleteKit(kit_num);
		
	}
	@Override
	public void deleteFile(Integer kit_num) {
		kitMapper.deleteFile(kit_num);
		
	}
	@Override
	public void insertKitLike(KiteLikeVO like) {
		kitMapper.insertKitLike(like);
		
	}

	

	
	
}
