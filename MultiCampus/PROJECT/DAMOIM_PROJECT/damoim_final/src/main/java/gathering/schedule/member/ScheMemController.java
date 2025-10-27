package gathering.schedule.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gathering.info.GatheringInfoService;
import gathering.info.GatheringInfoVO;
import member.MemberService;
import member.MemberVO;
@Controller
public class ScheMemController {
	@Autowired
	MemberService service;
	@Autowired
	GatheringInfoService gatheringService;
	
	@RequestMapping("/gathering/memberDetail.do")
	public ModelAndView memberDetail(String gath_no,String sche_mem_mno) {
		ModelAndView mav = new ModelAndView();
		System.out.println(sche_mem_mno);
		MemberVO mem = service.read(sche_mem_mno);
		GatheringInfoVO gathering = gatheringService.read(gath_no);
		mav.addObject("gathering",gathering);
		mav.addObject("member", mem);
		mav.addObject("gath_no",gath_no);
		mav.setViewName("gathering/memberDetail");
		return mav;
	}
	
}
