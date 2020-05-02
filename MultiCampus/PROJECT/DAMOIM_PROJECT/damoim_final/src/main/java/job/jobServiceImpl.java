package job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class jobServiceImpl implements jobService {
	@Autowired
	@Qualifier("jobDAO")
	jobDAO dao;
	
	public List<jobVO> list() {
		return dao.list();
	}

}
