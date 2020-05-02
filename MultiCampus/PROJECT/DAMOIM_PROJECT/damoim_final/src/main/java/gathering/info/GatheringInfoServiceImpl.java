package gathering.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GatheringInfoServiceImpl implements GatheringInfoService {
	@Autowired
	@Qualifier("GatheringInfoDAO")
	GatheringInfoDAO dao;
	@Override
	public GatheringInfoVO read(String gath_no) {
		return dao.read(gath_no);
	}
	@Override
	public List<GatheringMemberVO> gatheringmembersearch(String gath_no) {
		return dao.gatheringmembersearch(gath_no);
	}
	@Override
	public GatheringMemberVO memberCheckOfGathering(String mem_id, String gath_no) {
		return dao.memberCheckOfGathering(mem_id, gath_no);
	}
	@Override
	public int join(String mem_id, String gath_no) {
		return dao.join(mem_id, gath_no);
	}
}
