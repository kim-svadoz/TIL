package location;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("locDAO")
public class locationDAOImpl implements locationDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<locationVO> list() {
		return sqlSession.selectList("location.listall");
	}

}
