package home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import gathering.info.GatheringInfoVO;
import member.MemberVO;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	@Qualifier("homeDao")
	HomeDAO dao;
	
	// 인기모임(로그인여부 상관x - 회원수)
	@Override
	public List<GatheringInfoVO> topList() {
		List<GatheringInfoVO> list = null;
		list = dao.topList();
		return list;
	}
	
	
	// 추천모임(로그인 했을때 - 별점순에서 where 지역&관심사)
	//		로그인 안했을때 - 별점순)
	@Override
	public List<GatheringInfoVO> loginRecommendList(String mem_home) {
		
		List<GatheringInfoVO> list = null;
		list = dao.loginRecommendList(mem_home);
		return list;
	}
	
	
	/*@Override
	public List<GatheringInfoVO> loginRecommendList(String mem_home, ) {
		List<GatheringInfoVO> list = null;
		list = dao.loginRecommendList();
		return list;
	}*/
	
	// 추천모임(로그인 안했을때 - 별점순)
	@Override
	public List<GatheringInfoVO> recommendList() {
		List<GatheringInfoVO> list = null;
		list = dao.recommendList();
		return list;
	}
	
	// 내가 가입한 모임 목록(로그인 했을때)
	public List<GatheringInfoVO> myList() {
		List<GatheringInfoVO> list = null;
		list = dao.myList();
		return list;
	}
	

}
