package gathering.join;

import java.util.List;

import gathering.schedule.ScheduleRegisterVO;
import member.MemberVO;

public interface ScheduleJoinService {
	int register(String id, String gath_no, String sche_no);
	List<MemberVO> ranklist(String gath_no);
}
