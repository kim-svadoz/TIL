package location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class locationServiceImpl implements locationService {
	@Autowired
	@Qualifier("locDAO")
	locationDAO dao;
	
	public List<locationVO> list() {
		return dao.list();
	}

}
