package home;

import java.util.List;

import gathering.info.GatheringInfoVO;
import member.MemberVO;

public interface HomeDAO {

	List<GatheringInfoVO> topList();

	List<GatheringInfoVO> loginRecommendList(String mem_home);

	List<GatheringInfoVO> recommendList();

	List<GatheringInfoVO> myList();


}
