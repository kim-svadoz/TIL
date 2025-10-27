package gathering.info;

import java.util.List;

public interface GatheringInfoService {
	GatheringInfoVO read(String gath_no);
	List<GatheringMemberVO> gatheringmembersearch(String gath_no);
	GatheringMemberVO memberCheckOfGathering(String mem_id, String gath_no);
	int join(String mem_id, String gath_no);
}
