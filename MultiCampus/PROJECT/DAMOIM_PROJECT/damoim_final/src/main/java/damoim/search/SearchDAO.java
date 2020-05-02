package damoim.search;

import java.util.ArrayList;
import java.util.List;


public interface SearchDAO {

	List<SearchVO> searchList(ArrayList<String> datalist);

}
