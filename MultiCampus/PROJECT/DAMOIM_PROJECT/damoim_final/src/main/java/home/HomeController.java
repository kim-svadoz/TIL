package home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gathering.info.GatheringInfoVO;
import member.MemberVO;

@Controller
public class HomeController {
	@Autowired
	HomeService service;
	
	@RequestMapping("/main/home")
	//public ModelAndView homeView(HttpServletRequest request) {
	public ModelAndView homeView() {
		ModelAndView mav = new ModelAndView();
		/*HttpSession session = request.getSession(false);
		if(session != null) {
			// 로그인 했으면 로그인추천리스트 (별점순 -지역&관심사)
			MemberVO user = (MemberVO)session.getAttribute("user");
			List<GatheringInfoVO> loginRecommendlist 
					= service.loginRecommendList(user.getMem_home());
			
			System.out.println(toplist);
			System.out.println("==========================");
			System.out.println(loginRecommendlist);
			
			mav.addObject("loginRecommendlist", loginRecommendlist);
		}
		else {
			// 로그인 안 했으면 디폴트추천리스트 (별점순)
			List<GatheringInfoVO> recommendlist 
				= service.recommendList();
			
			mav.addObject("recommendlist", recommendlist);
		}*/
		// 추천모임은 공통으로 출력되는 부분
		List<GatheringInfoVO> toplist = service.topList();
		mav.addObject("toplist", toplist);
		// 내가 가입한 모임 목록은 공통으로 출력되는 부분
		List<GatheringInfoVO> mylist = service.myList();
		mav.addObject("mylist", mylist);
		
		// 테스트용 코드
		List<GatheringInfoVO> recommendlist = service.recommendList();
		mav.addObject("recommendlist", recommendlist);

		// 둘다 같은 뷰jsp 호출
		mav.setViewName("main/home");
		return mav;
	}
	
	
	
	
	
	/*@RequestMapping("/main/home")
	public String homeView() {
		return "main/home";
	}*/
	
	@RequestMapping("/guide")
	public String guideView() {
		return "guide";
	}
	
}

