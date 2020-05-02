package gathering.schedule;

import java.sql.Date;
import java.util.List;

import member.MemberVO;

public interface ScheduleRegisterService {
	int register(ScheduleRegisterVO moimInfo);
	List<ScheduleRegisterVO> moimList(String gath_no);
	List<ScheduleRegisterVO> moimListAll(String gath_no);
	/*List<Date> calendar(String gath_no);*/
	int moimDelete(String sche_no, String mem_id);
	
	List<ScheduleRegisterVO> moimListAllAjax(String mydate, String gath_no);
	List<MemberVO> moimListAllAjax2(String mydate, String gath_no);
}
