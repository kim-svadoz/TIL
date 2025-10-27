package moim.list;

import java.util.List;

import member.MemberVO;



public interface MoimListDAO {
	public MoimListVO moimList(String sche_no);
	public List<MemberVO> moimMember(String sche_no);
	
	
}
