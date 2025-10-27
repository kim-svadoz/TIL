package gathering.schedule.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import member.MemberVO;


@Service
public class ScheMemServiceImpl implements ScheMemService {

	@Autowired
	@Qualifier("ScheMemDAO")
	ScheMemDAO dao;
	
	@Override
	public MemberVO memberDetail(String sche_mem_mno) {
		return null;
	}
}
