package member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("memDao")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public MemberVO login(MemberVO loginUser) {
		return sqlSession.selectOne("member.login", loginUser);
	}
	
	@Override
	public ArrayList<MemberVO> getTreeEmpList(String deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MemberVO user) {
		return sqlSession.insert("member.insert", user);
	}

	@Override
	public ArrayList<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO read(String id) {
		return sqlSession.selectOne("gathering.join.memberDetail",id);
	}
	
	public MemberVO memread(String id) {
		return sqlSession.selectOne("member.read", id);
	}

	@Override
	public ArrayList<MemberVO> search(String column, String search, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(MemberVO user) {
		System.out.println("업데이트");
		return sqlSession.update("member.update", user);
	}


	@Override
	public boolean passCheck(String pass) {
		boolean result = false;
		MemberVO user=  sqlSession.selectOne("member.passcheck",pass);
		if(user!=null) {
			result = true;
		}
		return result;
	}

	@Override
	public MemberVO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean idCheck(String id) {
		boolean result = false;
		MemberVO user=  sqlSession.selectOne("member.idcheck",id);
		if(user!=null) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean nickCheck(String nickname) {
		boolean result = false;
		MemberVO user=  sqlSession.selectOne("member.nickcheck",nickname);
		if(user!=null) {
			result = true;
		}
		return result;
	}
	@Override
	public int profileupdate(String mem_profile, String mem_id) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("mem_profile", mem_profile);
		map.put("mem_id", mem_id);
		return sqlSession.update("member.profileupdate", map);
	}
		


}
