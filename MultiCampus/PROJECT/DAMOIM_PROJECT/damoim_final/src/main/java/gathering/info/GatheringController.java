package gathering.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.list.BoardListService;
import board.list.BoardListVO;
import image.ImageService;
import image.ImageVO;
import member.MemberVO;

@Controller
public class GatheringController {	
	@Autowired
	GatheringInfoService service;
	@Autowired
	BoardListService boardservice;
	@Autowired
	ImageService imageservice;
	
	@RequestMapping("/gathering/home.do")
	public ModelAndView gatheringHome(String gath_no, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		GatheringInfoVO gathering = service.read(gath_no);
		List<BoardListVO> boardlist = boardservice.boardList(gathering.getGath_no(), "all");
		//List<GatheringMemberVO> memberlist = service.gatheringmembersearch(gath_no);
		List<ImageVO> imglist = imageservice.searchlistimage(gath_no, "gathering");
		mav.addObject("imglist",imglist);
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
		System.out.println(imglist);
		if(vo!=null) {
			GatheringMemberVO memcheck = service.memberCheckOfGathering(vo.getMem_id(), gath_no);
			if(memcheck!=null) {
				mav.addObject("memchk",true);
			}
			else
				mav.addObject("memchk",false);
		}else {
			mav.addObject("memchk",false);
		}
		mav.addObject("gathering", gathering);
		mav.addObject("boardlist", boardlist);
		mav.setViewName("gathering/home");
		return mav;
	}
	@RequestMapping("/gathering/newgathering.do")
	public ModelAndView newgathering(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		mav.setViewName("gathering/newgathering");
		return mav;
	}
	
	@RequestMapping(value="/gathering/makegathering.do", method=RequestMethod.POST)
	public ModelAndView makegathering(GatheringInfoVO gathering, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		System.out.println(gathering);
		mav.setViewName("redirect:/gathering/newgathering.do");
		return mav;
	}
	@RequestMapping("/gathering/join.do")
	public ModelAndView join(String gath_no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		if(vo!=null) {
			GatheringMemberVO memcheck = service.memberCheckOfGathering(vo.getMem_id(), gath_no);
			if(memcheck!=null) {
				mav.addObject("memchk",true);
			}
			else
				mav.addObject("memchk",false);
				service.join(vo.getMem_id(), gath_no);
		}else {
			mav.addObject("memchk",false);
		}
		mav.setViewName("redirect:/gathering/home.do?gath_no="+gath_no);
		return mav;
	}
}
