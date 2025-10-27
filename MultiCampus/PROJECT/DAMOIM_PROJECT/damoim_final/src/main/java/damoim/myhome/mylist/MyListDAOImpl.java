package damoim.myhome.mylist;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("myListDao")
public class MyListDAOImpl implements MyListDAO {

	@Autowired
	SqlSession sqlsession;

	@Override
	public List<MyListVO> myList(String mem_id) {
		System.out.println("다오.리스트"+mem_id);
		
		List<MyListVO> list = sqlsession.selectList("damoim.myhome.mylist.mylist", mem_id);

		System.out.println("dao 사이즈: " + list.size());
		return list;
	}
	
	@Override
	public List<MyListVO> myScheduleList(String mem_id){
		System.out.println("다오.스캐줄"+mem_id);
		
		List<MyListVO> list2 = sqlsession.selectList("damoim.myhome.mylist.myschedulelist", mem_id);
		System.out.println("dao sche 사이즈: "+list2.size());
		return list2;
	}
	
	@Override
	public List<MyListVO> recommendList(String mem_id){
		System.out.println("다오.추천"+mem_id);
		return sqlsession.selectList("damoim.myhome.mylist.recommend", mem_id);
	}

}
