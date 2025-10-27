package member;

import java.util.ArrayList;

public interface MemberDAO {
	ArrayList<MemberVO> getTreeEmpList(String deptno);
	int insert(MemberVO user);
	ArrayList<MemberVO> getMemberList();
	int delete(String id);
	MemberVO read(String id);
	ArrayList<MemberVO> search(String column, String search,String pass);
	int update(MemberVO user);
	MemberVO login(MemberVO loginUser);
	boolean passCheck(String pass);
	MemberVO findById(String id);
	boolean idCheck(String id);
	boolean nickCheck(String nickname);
	public MemberVO memread(String id);
	public int profileupdate(String mem_profile, String mem_id);
}














