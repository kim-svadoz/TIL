package damoim.myhome.mylist;

import java.util.List;

public interface MyListService {

	List<MyListVO> myList(String mem_id);

	List<MyListVO> myScheduleList(String mem_id);

	List<MyListVO> recommendList(String mem_id);




}
