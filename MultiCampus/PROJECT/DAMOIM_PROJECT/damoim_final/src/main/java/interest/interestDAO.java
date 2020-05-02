package interest;

import java.util.List;

public interface interestDAO {

	List<interest_majorVO> majorlist();

	List<interest_minorVO> minorlist();

	int insert(member_interestVO memberInterest);

	int insert(String id, String[] mem_mjno);

}
