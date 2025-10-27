package moim.list;

import java.util.List;

import member.MemberVO;

public interface MoimListService {
	MoimListVO moimList(String sche_no);
	List<MemberVO> moimMember(String sche_no);
}









