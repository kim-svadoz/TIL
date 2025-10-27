package interest;

import java.util.List;

public interface interestService {
	public List<interest_majorVO> majorlist();
	public List<interest_minorVO> minorlist();
	public int insert(String id, String[] mem_mjno);
	public int insert(member_interestVO memberInterest);
}
