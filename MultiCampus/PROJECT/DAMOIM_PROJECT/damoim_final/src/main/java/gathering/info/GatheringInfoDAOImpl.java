package gathering.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("GatheringInfoDAO")
public class GatheringInfoDAOImpl implements GatheringInfoDAO {
	@Autowired
	SqlSession sqlSession;
	@Override
	public GatheringInfoVO read(String gath_no) {
		GatheringInfoVO vo = sqlSession.selectOne("gathering.info.read", gath_no);
		return vo;
	}
	@Override
	public List<GatheringMemberVO> gatheringmembersearch(String gath_no) {
		List<GatheringMemberVO> list = sqlSession.selectList("gathering.info.membersearch", gath_no);
		return list;
	}
	@Override
	public GatheringMemberVO memberCheckOfGathering(String mem_id, String gath_no) {
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("mem_id", mem_id);
		map.put("gath_no", gath_no);
		return sqlSession.selectOne("gathering.info.checkingathering", map);
	}
	@Override
	public int join(String mem_id, String gath_no) {
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("mem_id", mem_id);
		map.put("gath_no", gath_no);
		return sqlSession.insert("gathering.info.join",map);
	}
}
