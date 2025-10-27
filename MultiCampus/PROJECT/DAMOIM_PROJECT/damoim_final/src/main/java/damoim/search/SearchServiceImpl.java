package damoim.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	@Qualifier("searchDao")
	SearchDAO dao;

	@SuppressWarnings("null")
	@Override
	public List<SearchVO> searchList(String[] major_mjno) {
		System.out.println("서치 서비스" + major_mjno);
		int size = major_mjno.length;
		ArrayList<String> datalist = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			datalist.add(major_mjno[i]);
			System.out.println("서비스 : " + datalist.size());

		}
		 List<SearchVO> list = dao.searchList(datalist);
		 System.out.println("서비스보내야해 컨트럴로 : " + datalist.size());
		 return list;
	}

}
