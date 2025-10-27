package gathering.join;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;

@Repository("ScheduleJoinDAO")
public class ScheduleJoinDAOImpl implements ScheduleJoinDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(String id, String gath_no, String sche_no) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sche_mem_mno", id);
		map.put("sche_mem_gno", gath_no);
		map.put("sche_no", sche_no);
		return sqlSession.insert("gathering.join.insert", map);
	}

	@Override
	public List<MemberVO> ranklist(String gath_no) {
		return sqlSession.selectList("gathering.join.listall", gath_no);
	}

}
