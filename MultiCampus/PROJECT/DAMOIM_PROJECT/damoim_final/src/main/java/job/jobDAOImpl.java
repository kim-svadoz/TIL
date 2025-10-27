package job;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("jobDAO")
public class jobDAOImpl implements jobDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<jobVO> list() {
		return sqlSession.selectList("job.listall");
	}

}
