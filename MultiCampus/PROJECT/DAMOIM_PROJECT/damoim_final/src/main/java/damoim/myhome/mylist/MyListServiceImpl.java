package damoim.myhome.mylist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyListServiceImpl implements MyListService {
	
	@Autowired
	@Qualifier("myListDao")
	MyListDAO dao;

	@Override
	public List<MyListVO> myList(String mem_id) {
		System.out.println("서비스.마이리스트"+mem_id);
		List<MyListVO> ll = dao.myList(mem_id);
		System.out.println("mylist lll 왜 안나와"+ll);
		return ll;
	}
	
	@Override
	public List<MyListVO> myScheduleList(String mem_id){
		System.out.println("서비스.스캐줄리스트"+mem_id);
		return dao.myScheduleList(mem_id);
	}
	
	@Override
	public List<MyListVO> recommendList(String mem_id){
		System.out.println("서비스.추천리스트"+mem_id);
		return dao.recommendList(mem_id);
	}




}
