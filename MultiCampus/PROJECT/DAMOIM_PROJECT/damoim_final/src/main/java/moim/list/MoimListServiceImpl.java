package moim.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import member.MemberVO;

@Service
public class MoimListServiceImpl implements MoimListService {
	
	@Autowired
	@Qualifier("moimDAO")
	MoimListDAO dao;

	@Override
	public MoimListVO moimList(String sche_no) {
		
		return dao.moimList(sche_no);
	}
	@Override
	public List<MemberVO> moimMember(String sche_no) {
		
		return dao.moimMember(sche_no);
	}
}
