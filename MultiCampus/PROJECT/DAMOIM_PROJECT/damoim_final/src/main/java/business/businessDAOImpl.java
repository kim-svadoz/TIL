package business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("busiDAO")
public class businessDAOImpl implements businessDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<businessVO> list() {
		return sqlSession.selectList("business.listall");
	}

}
