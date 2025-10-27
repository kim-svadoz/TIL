package gathering.schedule;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gathering.info.GatheringInfoService;
import gathering.info.GatheringInfoVO;
import gathering.info.GatheringMemberVO;
import gathering.join.ScheduleJoinService;
import image.ImageService;
import image.ImageVO;
import member.MemberService;
import member.MemberVO;

@Controller
public class ScheduleRegisterController {
	@Autowired
	ScheduleRegisterService registerService;
	@Autowired
	MemberService memberService;
	@Autowired
	GatheringInfoService gatheringService;
	@Autowired
	ScheduleJoinService joinService;
	@Autowired
	ImageService imageservice;
	
	// 모임생성을 작성 페이지를 띄우기 위한 뷰
	@RequestMapping(value = "/gathering/sch_create.do", method = RequestMethod.GET)
	public ModelAndView createView(@Valid ScheduleRegisterVO scheduleRegisterVO, Errors error, String gath_no,
			HttpServletRequest request) throws Exception {

		// 세션 가져오는 폼
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		System.out.println(vo);
		String sche_mem_mno = vo.getMem_id();

		MemberVO member = memberService.read(sche_mem_mno);
		GatheringInfoVO gathering = gatheringService.read(gath_no);
		System.out.println("create에서 만든 sche_mem_mno==============" + sche_mem_mno);
		System.out.println("create에서 만든 gath_no==============" + gath_no);
		mav.addObject("member", member); // 메인에서 쓰기위해!
		mav.addObject("gathering", gathering);
		mav.setViewName("gathering/sch_create");
		if (error.hasErrors()) {
			mav.setViewName("gathering/sch_create");
			return mav;
		}

		return mav; // tiles에 등록한 뷰 이름
	}

	// 모임생성 완료페이지를 작성하기 위한 뷰
	@RequestMapping(value = "/gathering/sch_createFinish.do", method = RequestMethod.GET)
	public String createFinishView() {
		System.out.println("모임생성완료페이지 ==================");
		return "gathering/sch_createFinish"; // tiles에 등록한 뷰 이름
	}

	// 입력한 모임 정보를 실제 DB에 저장하는 메소드
	@RequestMapping(value = "/gathering/sch_create.do", method = RequestMethod.POST)
	public ModelAndView create(ScheduleRegisterVO moimInfo, HttpServletRequest request) {
		System.out.println(moimInfo.sche_date);
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		
		String sche_mem_mno = vo.getMem_id();
		MemberVO member = memberService.read(sche_mem_mno);
		GatheringInfoVO gathering = gatheringService.read(moimInfo.getSche_gno());
		mav.addObject("member", member); // create에서 쓰기위해!
		mav.addObject("gathering", gathering);
		int result = registerService.register(moimInfo);
		System.out.println("모임생성 완료된 moim의 정보 " + moimInfo);
		System.out.println("result의 갯수: " + result);
		mav.setViewName("gathering/sch_createFinish");
		return mav;
	}

	// 1
	@RequestMapping(value = "/gathering/sch_main.do")
	public ModelAndView MyScheMoim(String gath_no, HttpServletRequest request) {
		System.out.println("gath_no==========" + gath_no);
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		System.out.println(vo);
		if (vo != null) {
			String sche_mem_mno = vo.getMem_id();
			MemberVO member = memberService.read(sche_mem_mno);

			System.out.println("1===========" + sche_mem_mno);
			System.out.println("mem_count===============" + member.getMem_count());
			// 참석클릭하면
			/*joinService.register(member.getMem_id(), gath_no, );*/
			mav.addObject("member", member); // 메인에서 쓰기위해!
		}
		if(vo!=null) {
			GatheringMemberVO memcheck = gatheringService.memberCheckOfGathering(vo.getMem_id(), gath_no);
			if(memcheck!=null) {
				mav.addObject("memchk",true);
			}
			else
				mav.addObject("memchk",false);
		}else {
			mav.addObject("memchk",false);
		}
		GatheringInfoVO gathering = gatheringService.read(gath_no);
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
		System.out.println("2===========" + gath_no);
		List<ScheduleRegisterVO> list = registerService.moimList(gath_no);

		System.out.println("3===========" + list);
		mav.addObject("moim", list); // 생성된 모임리스트를 moim에저장

		// moim => DB연동 후 생성된 사용자의 정보
		// moimInfo => 로그인 하기 위해서 사용자가 입력한 데이터를 VO로 만든 객체
		mav.addObject("gathering", gathering); // 메인에서 쓰기위해!

		mav.setViewName("gathering/sch_main");
		// 랭킹 메소드 main에 뿌려주기
		List<MemberVO> rank = joinService.ranklist(gath_no);
		System.out.println("4=============" + rank);
		mav.addObject("list", rank); // 생성된 랭크리스트를 list에 저장

		return mav;
	}

	// 생성된 모든 모임을 조회하기 위한 메소드
	@RequestMapping(value = "/gathering/sch_moimAll.do", method = RequestMethod.GET)
	public ModelAndView moimAll(String gath_no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		if(vo!=null) {
		String sche_mem_mno = vo.getMem_id();
		MemberVO member = memberService.read(sche_mem_mno);
		mav.addObject("member", member); // 메인에서 쓰기위해!
		}
		GatheringInfoVO gathering = gatheringService.read(gath_no);
		mav.addObject("gathering", gathering);
		
		List<ScheduleRegisterVO> moimAllList = registerService.moimListAll(gath_no);
		mav.addObject("moimAllList", moimAllList);
		mav.setViewName("gathering/sch_moimAll");
		return mav;
	}

	// master가 생성된 모임을 삭제하기 위한 메소드
	@RequestMapping(value = "/gathering/sch_moimDelete.do", method = RequestMethod.GET)
	public ModelAndView moimDelete(String gath_no, String sche_no, HttpServletRequest request) {
		System.out.println("sche_no==================" + sche_no);
		System.out.println("sche_no==================" + gath_no);
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		
		GatheringInfoVO gathering = gatheringService.read(gath_no);
		mav.addObject("vo", vo);
		mav.addObject("gathering", gathering);

		int result = registerService.moimDelete(sche_no, vo.getMem_id());
		System.out.println("삭제한 모임의 갯수==========" + result);
		mav.setViewName("gathering/sch_deleteFinish");
		return mav;
		/*"redirect:/gathering/sch_main.do?gath_no="+gath_no
*/	}

	// 달력 가져오기 위한 메소드
	@RequestMapping(value = "/gathering/sch_calendar.do", method = RequestMethod.GET)
	public ModelAndView Calendar(String gath_no, HttpServletRequest request) {
		System.out.println("Calendar에서 만든 gath_no==============" + gath_no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("gath_no", gath_no);
		mav.setViewName("gathering/sch_calendar");
		
		return mav;
	}
	
	@RequestMapping(value="/gathering/ajax_gatheringlist.do", method= RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody ArrayList<ScheduleRegisterVO> gatheringList(String mydate, String gath_no){
		System.out.println("ajax1================mydate======="+mydate);
		System.out.println("ajax2================gath_no==========="+gath_no);
		/*HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		String sche_mem_mno = vo.getMem_id();*/
		
		/*SimpleDateFormat mydate = new SimpleDateFormat("yyyy-MM-dd");*/
		
		ArrayList<ScheduleRegisterVO> gatheringlist = (ArrayList<ScheduleRegisterVO>)registerService.moimListAllAjax(mydate, gath_no);
		System.out.println("gatheringlist의 정보============="+gatheringlist);
		System.out.println("ajax통신:"+gatheringlist.size());
		return gatheringlist;
	}
	
	@RequestMapping(value="/gathering/ajax_gatheringlist2.do", method= RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody ArrayList<MemberVO> gatheringList2(String mydate, String gath_no){
		System.out.println("ajx2======================mydate2======="+mydate);
		System.out.println("ajax2================gath_no2==========="+gath_no);
		
		ArrayList<MemberVO> gatheringlist2 = (ArrayList<MemberVO>)registerService.moimListAllAjax2(mydate, gath_no);
		System.out.println("ajax통신:"+gatheringlist2.size());
		return gatheringlist2;
	}
	
	@RequestMapping(value="/gathering/sch_calclacal.do", method=RequestMethod.POST)
	public ModelAndView fuckfuckfuck(String gath_no) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gathering/sch_calendar");
		return mav;
	}
	
	// 모임생성 칸에서 카카오지도 가져오기 위한 메소드
	@RequestMapping(value = "/gathering/kakaomap.do", method = RequestMethod.GET)
	public ModelAndView kakaomapView(String sche_loc) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gathering/kakaomap");
		mav.addObject("sche_loc", sche_loc);
		return mav;
	}
	
	//kakaomap에서 지도 정보를 넘겨주는 메소드
	@RequestMapping(value = "/gathering/kakaomap.do", method = RequestMethod.POST)
	public ModelAndView kakaomap(String sche_loc, String sche_lat, String sche_lng) {
		System.out.println("sche_loc===="+sche_loc);
		System.out.println("sche_lat===="+sche_lat);
		System.out.println("sche_lng===="+sche_lng);
		ModelAndView mav = new ModelAndView();
		mav.addObject("sche_loc", sche_loc);
		mav.addObject("sche_lat", sche_lat);
		mav.addObject("sche_lng", sche_lng);
		mav.setViewName("gathering/kakaomap");
		return mav;
	}
}
