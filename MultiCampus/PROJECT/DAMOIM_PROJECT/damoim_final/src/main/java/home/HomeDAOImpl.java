package home;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gathering.info.GatheringInfoVO;
import member.MemberVO;

@Repository("homeDao")
public class HomeDAOImpl implements HomeDAO {
	@Autowired
	SqlSession sqlSession;
	
	/*@Override
	public List<BoardVO> boardList() {
		// select문의 실행결과가 여러 개의 레코드를 반환하는 경우 사용
		// sqlSession.selectList("mapper에 등록한 sql문 id (namespace포함)");
		return sqlSession.selectList("multi.erp.board.listall");
	}*/
	
	@Override
	public List<GatheringInfoVO> topList() {
		
		List<GatheringInfoVO> toplist = sqlSession.selectList("home.topList");
		System.out.println(toplist.size());
		return toplist;
		
	}
	
	// 시험용
	@Override
	public List<GatheringInfoVO> myList() {
		return sqlSession.selectList("home.myList");
	}
	
	@Override
	public List<GatheringInfoVO> loginRecommendList(String mem_home) {
		return sqlSession.selectList("home.loginRecommendList", mem_home);
	}

	@Override
	public List<GatheringInfoVO> recommendList() {
		String[] catelist = { "I24",
				"I11",
				"I3",
				"I13",
				"I14",
				"I9",
				"I19",
				"I19",
				"I22",
				"I21",
				"I10",
				"I1",
				"I4",
				"I5",
				"I20",
				"I2",
				"I5",
				"I8",
				"I23",
				"I12",
				"I16",
				"I7",
				"I18",
				"I23"};


	    // Math.ramdom이용
	    double randomValue = Math.random();
	    int ran = (int)(randomValue * catelist.length);

	    String category = catelist[ran];

		String major = category;
		return sqlSession.selectList("home.recommendList", major);
	}



}
