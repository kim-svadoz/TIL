package moim.list;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gathering.info.GatheringInfoService;
import gathering.info.GatheringInfoVO;
import gathering.info.GatheringMemberVO;
import gathering.schedule.member.ScheMemService;
import image.ImageService;
import image.ImageVO;
import member.MemberVO;

@Controller
public class MoimController {
	@Autowired
	GatheringInfoService gatheringservice;
	@Autowired
	ScheMemService scheMemService;
	@Autowired 
	MoimListService service;
	@Autowired
	ImageService imageservice;
	
	@RequestMapping("/gathering/moim.do")
	public ModelAndView moimList(String gath_no,String sche_no,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		GatheringInfoVO gathering = gatheringservice.read(gath_no);
		mav.addObject("gathering", gathering);
		mav.addObject("gath_no",gath_no);
		if(vo!=null) {
			GatheringMemberVO memcheck = gatheringservice.memberCheckOfGathering(vo.getMem_id(), gath_no);
			if(memcheck!=null) {
				mav.addObject("memchk",true);
			}
			else
				mav.addObject("memchk",false);
		}else {
			mav.addObject("memchk",false);
		}
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
		System.out.println(sche_no);
		MoimListVO moim = service.moimList(sche_no);
		List<MemberVO> list = service.moimMember(sche_no);
		System.out.println(list);
		mav.addObject("moim", moim);
		mav.addObject("list", list);
		mav.setViewName("gathering/moim");
		return mav;
		
	}
	
}
