package gathering.schedule.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;

@Repository("ScheMemDAO")
public class ScheMemDAOImpl implements ScheMemDAO {

	@Autowired
	SqlSession sqlSession;
	

}
