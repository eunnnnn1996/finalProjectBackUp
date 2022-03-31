package kr.spring.offclass.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.offclass.dao.OffclassMapper;
import kr.spring.offclass.vo.OffTimetableVO;
import kr.spring.offclass.vo.OffclassVO;
import kr.spring.offclass.vo.OfflikeVO;

@Service
@Transactional
public class OffclassServiceImpl implements OffclassService{
	
	@Autowired
	private OffclassMapper offclassMapper;

	@Override
	public List<OffclassVO> selectListOffClass(Map<String, Object> map) {
		return offclassMapper.selectListOffClass(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return offclassMapper.selectRowCount(map);
	}

	@Override
	public void insertOffClass(OffclassVO offclass,List<OffTimetableVO> list) {
		int num= offclassMapper.selectOff_num();
		offclass.setOff_num(num);
		offclassMapper.insertOffClass(offclass);
		System.out.println("-----------------------"+num);
		for(int i=0;i<list.size();i++) {
			OffTimetableVO offTimetableVO=list.get(i);
			offTimetableVO.setOff_num(num);
			System.out.println("offTimetableVO"+list.get(i));
		}
		offclassMapper.insertListOffTime(list);
	}

	@Override
	public OffclassVO selectOffClass(Integer off_num) {
		return offclassMapper.selectOffClass(off_num);
	}

	@Override
	public List<OffTimetableVO> selectListOffTimetable(int off_num) {
		return offclassMapper.selectListOffTimetable(off_num);
	}

	@Override
	public void updateOffClass(OffclassVO offclass,List<OffTimetableVO> list) {
		offclassMapper.updateOffClass(offclass);
		offclassMapper.deleteOffTimetable(offclass.getOff_num());
		for(int i=0;i<list.size();i++) {
			OffTimetableVO offTimetableVO=list.get(i);
			offTimetableVO.setOff_num(offclass.getOff_num());
		}
		offclassMapper.insertListOffTime(list);
	}

	@Override
	public void deleteOffClass(Integer off_num) {
		// TODO Auto-generated method stub
		
	}


	//찜 기능
	@Override
	public void insertLike(Integer user_num, Integer off_num) {
		offclassMapper.insertLike(user_num, off_num);
	}

	@Override
	public void deleteLike(Integer offlike_num) {
		offclassMapper.deleteLike(offlike_num);
	}

	@Override
	public OfflikeVO selectLike(Integer user_num, Integer off_num) {
		return offclassMapper.selectLike(user_num, off_num);
	}

	@Override
	public int selectLikeCount(Integer off_num) {
		return offclassMapper.selectLikeCount(off_num);
	}



}
