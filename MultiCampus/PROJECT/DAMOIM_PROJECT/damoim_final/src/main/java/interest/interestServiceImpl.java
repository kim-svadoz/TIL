package interest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class interestServiceImpl implements interestService {
	@Autowired
	@Qualifier("interDAO")
	interestDAO dao;
	
	public List<interest_majorVO> majorlist() {
		return dao.majorlist();
	}
	
	public List<interest_minorVO> minorlist() {
		return dao.minorlist();
	}
	
	public int insert(member_interestVO memberInterest) {
		return dao.insert(memberInterest);
	}

	@Override
	public int insert(String id, String[] mem_mjno) {
		return dao.insert(id, mem_mjno);
	}
}
