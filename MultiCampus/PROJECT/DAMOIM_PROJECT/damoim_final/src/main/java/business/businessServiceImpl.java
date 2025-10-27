package business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class businessServiceImpl implements businessService {
	@Autowired
	@Qualifier("busiDAO")
	businessDAO dao;

	@Override
	public List<businessVO> list() {
		return dao.list();
	}
	

}
