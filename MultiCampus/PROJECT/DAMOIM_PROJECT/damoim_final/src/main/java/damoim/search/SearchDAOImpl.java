package damoim.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("searchDao")
public class SearchDAOImpl implements SearchDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<SearchVO> searchList(ArrayList<String> major_mjno) {
		System.out.println("서치 다오"+major_mjno);
		
		Map<String, Object> paramMap = 	new HashMap<String, Object>();
		paramMap.put("major_mjno",major_mjno);
		
		List<SearchVO> list = sqlSession.selectList("damoim.search.major_mjno", paramMap);
		System.out.println("dao : " + list.size());
		
		return list;
		
	}

}
