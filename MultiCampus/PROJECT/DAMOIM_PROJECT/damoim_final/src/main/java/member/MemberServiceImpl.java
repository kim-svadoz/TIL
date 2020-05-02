package member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	@Qualifier("memDao")
	MemberDAO dao;
		
	@Override
	public MemberVO login(MemberVO loginUser) {
		MemberVO user = dao.login(loginUser);
		return user;
	}
	
	@Override
	public int insert(MemberVO user) {
		return dao.insert(user);
	}

	@Override
	public boolean passCheck(String pass) {
		return dao.passCheck(pass);
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
		return dao.read(id);
	}
	
	public MemberVO memread(String id) {
		return dao.memread(id);
	}

	@Override
	public ArrayList<MemberVO> search(String column, String search, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(MemberVO user) {
		return dao.update(user);
	}

	@Override
	public ArrayList<MemberVO> getTreeEmpList(String deptno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public boolean idCheck(String id) {
		return dao.idCheck(id);
	}
	

	@Override
	public boolean nickCheck(String nickname) {
		return dao.nickCheck(nickname);
	}

	@Override
	public void updateAuthstatus(MemberVO user) {
		
	}
	@Override
	public int profileupdate(String mem_profile, String mem_id) {
		return dao.profileupdate(mem_profile, mem_id);
	}

}
