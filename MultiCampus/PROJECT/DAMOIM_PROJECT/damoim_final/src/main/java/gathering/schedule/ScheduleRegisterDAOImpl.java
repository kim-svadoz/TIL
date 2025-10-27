package gathering.schedule;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;

@Repository("registerDao")
public class ScheduleRegisterDAOImpl implements ScheduleRegisterDAO {
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insert(ScheduleRegisterVO moimInfo) {
		return sqlSession.insert("gathering.schedule.insert", moimInfo);
	}

	@Override
	public List<ScheduleRegisterVO> moimList(String gath_no) {
		return sqlSession.selectList("gathering.schedule.listall", gath_no);
	}
	
	@Override
	public List<ScheduleRegisterVO> moimListAll(String gath_no) {
		return sqlSession.selectList("gathering.schedule.moimlistall", gath_no);
	}

	/*@Override
	public List<Date> calendar(String gath_no) {
		return sqlSession.selectList("gathering.schedule.calendarList", gath_no);
	}*/

	@Override
	public int moimDelete(String sche_no, String mem_id) {
		Map<String, String> map = new HashMap<>();
		map.put("sche_no", sche_no);
		map.put("mem_id", mem_id);
		return sqlSession.delete("gathering.schedule.moimdelete", map);
	}

	@Override
	public List<ScheduleRegisterVO> moimListAllAjax(String mydate, String gath_no) {
		Map<String, String> ajaxmap = new HashMap<>();
		ajaxmap.put("mydate", mydate);
		ajaxmap.put("gath_no", gath_no);
		return sqlSession.selectList("gathering.schedule.moimListAllAjax", ajaxmap);
	}

	@Override
	public List<MemberVO> moimListAllAjax2(String mydate, String gath_no) {
		Map<String, String> ajaxmap2 = new HashMap<>();
		ajaxmap2.put("mydate", mydate);
		ajaxmap2.put("gath_no", gath_no);
		return sqlSession.selectList("gathering.schedule.moimListAllAjax2", ajaxmap2);
	}
	
	
}
