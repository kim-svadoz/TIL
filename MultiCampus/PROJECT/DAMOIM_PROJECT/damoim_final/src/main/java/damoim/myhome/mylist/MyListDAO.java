package damoim.myhome.mylist;

import java.util.List;

public interface MyListDAO {
	
	List<MyListVO> myList(String mem_id);

	List<MyListVO> myScheduleList(String gath_name);

	List<MyListVO> recommendList(String mem_id);
}
