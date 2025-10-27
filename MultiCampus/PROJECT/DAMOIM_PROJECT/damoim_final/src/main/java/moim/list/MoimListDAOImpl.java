package moim.list;

import java.util.List;
//mybatis?�� ?��?��?��?��?���? ?��?��?��?�� ?��?��

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;

@Repository("moimDAO")
public class MoimListDAOImpl implements MoimListDAO {
	
	@Autowired
	SqlSession sqlSession;
	public MoimListVO moimList(String sche_no) {
		return sqlSession.selectOne("moim.list.listall", sche_no);
	}
	
	@Override
	public List<MemberVO> moimMember(String sche_no) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("moim.list.listmem", sche_no);
	};
	

	
}
