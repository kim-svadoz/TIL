package gathering.join;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gathering.info.GatheringInfoService;
import gathering.info.GatheringInfoVO;
import gathering.schedule.ScheduleRegisterVO;
import member.MemberService;
import member.MemberVO;

@Controller
public class ScheduleJoinController {
	@Autowired
	MemberService memberService;
	@Autowired
	GatheringInfoService gatheringService;
	@Autowired
	ScheduleJoinService joinService;
	

	// 참석 클릭 후 DB에 회원정보를 저장하는 메소드
	@RequestMapping(value="/gathering/sch_join.do", method=RequestMethod.POST)
	public String insert(String gath_no, HttpServletRequest request, String sche_no) {
		
		HttpSession session = request.getSession();
		MemberVO vo =(MemberVO)session.getAttribute("user");		
		System.out.println(vo);
		GatheringInfoVO gathering = gatheringService.read(gath_no);
		System.out.println(sche_no);
		int result = joinService.register(vo.getMem_id(), gathering.getGath_no(), sche_no);
		System.out.println("참석찍은 result 몇개?======"+result);
		
		return "redirect:/gathering/moim.do?gath_no="+gath_no+"&sche_no="+sche_no;
	}
}