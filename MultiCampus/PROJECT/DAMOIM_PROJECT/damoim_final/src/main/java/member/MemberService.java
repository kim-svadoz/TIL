package member;

import java.util.ArrayList;

public interface MemberService {
	ArrayList<MemberVO> getTreeEmpList(String deptno);
	public boolean passCheck(String pass);
	ArrayList<MemberVO> getMemberList();
	int delete(String id);
	MemberVO read(String id);
	ArrayList<MemberVO> search(String column, String search
					,String pass);
	int update(MemberVO user);
	MemberVO login(MemberVO loginUser);
	boolean idCheck(String id);
	boolean nickCheck(String nickname);
	void updateAuthstatus(MemberVO user);
	int insert(MemberVO user);
	public int profileupdate(String mem_profile, String mem_id);
}
