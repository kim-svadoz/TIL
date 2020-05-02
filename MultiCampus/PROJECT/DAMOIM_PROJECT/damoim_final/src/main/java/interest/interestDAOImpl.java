package interest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("interDAO")
public class interestDAOImpl implements interestDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<interest_majorVO> majorlist() {
		return sqlSession.selectList("interest_major.listall");
	}

	@Override
	public List<interest_minorVO> minorlist() {
		return sqlSession.selectList("interest_minor.listall");
	}

	@Override
	public int insert(member_interestVO memberInterest) {
		return sqlSession.insert("member_interest.insert", memberInterest);
	}

	@Override
	public int insert(String id, String[] mem_mjno) {
		int size = mem_mjno.length;
		for(int i=0; i<size; ++i) {
			member_interestVO memberInterest = new member_interestVO(id, mem_mjno[i]);
			System.out.println(memberInterest);
			sqlSession.insert("member_interest.insert", memberInterest);
		}
		return size;
	}

	/*@Override
	public int insert(String id, String[] mem_mjno) {
		//Map<String, String> paraMap = new HashMap<String, String>();
		// 키 삽입
		int size = mem_mjno.length;
		for(int i=0; i<size; ++i) {
			//paraMap.put("list", mem_mjno[i])
			return sqlSession.insert(statement)
		}
	}*/

}
