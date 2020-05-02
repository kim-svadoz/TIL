package gathering.schedule;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.MemberVO;

@Service
public class ScheduleRegisterServiceImpl implements ScheduleRegisterService {
	@Autowired
	ScheduleRegisterDAO dao;
	
	@Override
	public int register(ScheduleRegisterVO moimInfo) {
		int list = dao.insert(moimInfo);
		return list;
	}
	
	@Override
	public List<ScheduleRegisterVO> moimList(String gath_no) {
		List<ScheduleRegisterVO> list=null;
		list = dao.moimList(gath_no);
		return list;
	}

	/*@Override
	public List<Date> calendar(String gath_no) {
		List<Date> result = null;
		result = dao.calendar(gath_no);
		return result;
	}*/

	@Override
	public List<ScheduleRegisterVO> moimListAll(String gath_no) {
		List<ScheduleRegisterVO> list=null;
		list = dao.moimListAll(gath_no);
		return list;
	}

	public int moimDelete(String sche_no, String mem_id) {
		int result = dao.moimDelete(sche_no, mem_id);
		return result;
	}

	@Override
	public List<ScheduleRegisterVO> moimListAllAjax(String mydate, String gath_no) {
		List<ScheduleRegisterVO> list = null;
		list = dao.moimListAllAjax(mydate, gath_no);
		return list;
	}

	@Override
	public List<MemberVO> moimListAllAjax2(String mydate, String gath_no) {
		List<MemberVO> list = null;
		list = dao.moimListAllAjax2(mydate, gath_no);
		return list;
	}

}
