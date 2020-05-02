package member;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import business.businessService;
import business.businessVO;
import interest.interestService;
import interest.interest_majorVO;
import interest.interest_minorVO;
import interest.member_interestVO;
import job.jobService;
import job.jobVO;
import location.locationService;
import location.locationVO;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	@Autowired
	locationService loc_service;
	@Autowired
	businessService busi_service;
	@Autowired
	jobService job_service;
	@Autowired
	interestService inter_service; 
	
	// 로그인 페이지를 보기 위한 요청
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public ModelAndView loginView() {
		// fail 초기화
		ModelAndView mav = new ModelAndView();
		mav.addObject("fail", false);
		mav.setViewName("login");
		return mav;
	}
	
	// 로그인 처리를 위한 요청 (submit)
	// - 매개변수가 VO객체 :
	// 스프링MVC 내부의 DispatcherServlet에서 자동으로 입력된 파라미터들을 추출해서
	// VO객체를 만들고 setter메소드의 값으로 전달한다.
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(MemberVO loginUserInfo, HttpServletRequest request) {
		// 1. request
		System.out.println("로그인 하기 위해서 사용자가 입력한 값: "+loginUserInfo);
		ModelAndView mav = new ModelAndView();
		
		/*	user => db연동 후 로그인 된 사용자의 정보
			loginUserInfo => 로그인 하기 위해서 사용자가 입력한 데이터를 vo로 만든 객체	*/
		
		// 2. response
		MemberVO user = service.login(loginUserInfo);
		System.out.println("로그인 성공 후 조회된 레코드로 만들어진 값: "+user);
		// 로그인한 사용자의 정보를 세션에 저장
		// 세션을 사용하기 위해서 매개변수에 HttpServletRequest를 정의
		// 1. 세션을 생성
		//	=> getSession()을 이용해서 세션을 생성
		//	=> 무조건 생성해서 리턴(처음 세션을 만들 때 사용)
		String viewName ="";
		if(user != null) {
			// 로그인 성공시
			HttpSession ses = request.getSession();
			// 2. 세션에 데이터 공유
			ses.setAttribute("user", user);
			// redirect로 해야함
			mav.addObject("fail", false);
			viewName = "redirect:/main/home";
		}else {
			//로그인 실패
			mav.addObject("fail", true);
			viewName = "login";
		}
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		// 로그아웃 처리를 위해서 매개변수로 session을 받도록 처리
		// 사용하던 세션 객체가 있으면 사용하던 세션객체가 전달된다.
		if(session != null) {
			//null이 아니라 세션이 존재하면 세션객체를 해제
			session.invalidate();
		}
		return "redirect:/main/home";
	}
	
	
	// ================= 회원가입================
	@RequestMapping("/member/reg.do")
	public String reg1View() {
		return "reg";
	}
	// 아이디 중복체크
	@RequestMapping(value="/member/idCheck.do", 
			method=RequestMethod.GET,
			produces="application/text; charset=utf-8")
	public @ResponseBody String idCheck(String id) {
		boolean state = service.idCheck(id);
		String result = "";
		if(state) { //이미 사용자가 입력한 아이디가  db에 저장되어 있다는 의미
			result = "이미 존재하는 아이디 입니다.";
		}else {
			result = "사용가능한 아이디";
		}
		return result;
	}
	
	// 닉네임 중복체크
	@RequestMapping(value="/member/nicknameCheck.do", 
			method=RequestMethod.GET,
			produces="application/text; charset=utf-8")
	public @ResponseBody String nickCheck(String nickname) {
		boolean state = service.nickCheck(nickname);
		String result = "";
		if(state) { //이미 사용자가 입력한 닉네임이  db에 저장되어 있다는 의미
			result = "이미 존재하는 닉네임 입니다.";
		}else {
			result = "사용가능한 닉네임";
		}
		return result;
	}
	
	
	@RequestMapping("/member/reg2.do")
	public String reg2View() {
		return "reg2";
	}
	
	@RequestMapping(value="/member/reg3.do", method=RequestMethod.POST)
	public ModelAndView reg3View(String mem_id, String mem_pass, String mem_name,
				String mem_nickname, String year, String month, String day, 
				String mem_phone, HttpServletRequest request) {
		// 1. 요청정보 추출
		ModelAndView mav = new ModelAndView();
		// 2. 비지니스 메소드 호출
		String stringMem_birth = year+"-"+month+"-"+day;
		Date mem_birth = Date.valueOf(stringMem_birth);
		MemberVO user = new MemberVO(mem_id, mem_pass, mem_name,
							mem_nickname, mem_birth, mem_phone);
		//service.insert(user);
		HttpSession ses = request.getSession();
		ses.setAttribute("reg", user);
		System.out.println("회원가입 1: "+user);
		
		// 3. 데이터 공유 
		mav.addObject("user", user);
		
		List<locationVO> location = loc_service.list();
		mav.addObject("location", location);
		List<businessVO> business = busi_service.list();
		mav.addObject("business", business);
		List<jobVO> job = job_service.list();
		mav.addObject("job", job);
		// 관심사
		
		mav.setViewName("reg3");
		
		return mav;
	}
	
	
	// 관심사
	@RequestMapping(value ="/member/inter.do", method=RequestMethod.POST)
	public ModelAndView interestView(String mem_home, 
			String mem_office, String mem_neighbor,
			String mem_business, String mem_job, String mem_gender, 
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		String result = "";
		// null 체크
		if(session != null) {
			MemberVO user = (MemberVO)session.getAttribute("reg"); 
			System.out.println("관심사 페이지: " + user);
    		user.setMem_home(mem_home);
    		user.setMem_office(mem_office);
    		user.setMem_neighbor(mem_neighbor);
    		user.setMem_business(mem_business);
    		user.setMem_job(mem_job);
    		user.setMem_gender(mem_gender);
    	
    		session.setAttribute("reg", user);
    		// 3. 데이터 공유 
    		mav.addObject("user", user);
    	
    		List<interest_majorVO> majorlist = inter_service.majorlist();
    		mav.addObject("majorlist", majorlist);
    	
    		result = "reg-inter";
		}else {
			result = "redirect:/main/home";
		}
		mav.setViewName(result);
		return mav;
	}

	
	@RequestMapping(value ="/member/reg5.do", method=RequestMethod.POST)
	public ModelAndView reg5View(String[] mem_mjno, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		String result = "";
		// null 체크
		if(session != null) {
			MemberVO user = (MemberVO)session.getAttribute("reg"); 
			System.out.println("가입완료: " + user);
			// 회원 db에 insert
			service.insert(user);
			// ("mem_mjno")를 member_interest테이블에 저장
			/*List<Map<String, String>> maplist = new ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			inter_service.insert(id, mem_mjno);*/
			
			int size = mem_mjno.length;
			String id = user.getMem_id();
			inter_service.insert(id, mem_mjno);
			
    		result = "reg5";
		}else {
			result = "redirect:/main/home";
		}
		
		//세션 종료!!
		if(session != null) {
			session.invalidate();
		}
		mav.setViewName(result);
		return mav;
	}
	
	
	
	
	// =================마이페이지========================
		@RequestMapping(value="/member/passCheck.do", method=RequestMethod.POST)
		public ModelAndView passCheck(HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			// 이미 있으면 넣어주고, 없으면 null 리턴
			String pass = request.getParameter("pass");
			HttpSession session = request.getSession(false);
			String result = "";
			// null 체크
			if(session != null) {
				MemberVO user = (MemberVO)session.getAttribute("user");
				mav.addObject("userid", user.getMem_id());
				if(user.getMem_pass().equals(pass)) { 
					mav.addObject("fail", false);
					//입력한 비밀번호가 session의 비밀번호와 같다면 마이페이지 이동
					result = "mypage2";
				}else {
					// 비밀번호 안 맞으면 다시 이동
					mav.addObject("fail", true);
					result = "mypage";
				}
			}else { 
				result = "redirect:/main/home";
			}
			
			mav.setViewName(result);
			return mav;
		}
		
		@RequestMapping(value ="member/mypage", method=RequestMethod.GET)
		public ModelAndView mypage() {
			// fail 초기화
			ModelAndView mav = new ModelAndView();
			mav.addObject("fail", false);
			mav.setViewName("mypage");
			return mav;
		}
		

		@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
		public ModelAndView update(String mem_msg, String mem_nickname,
				String mem_phone, String mem_email, HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			// 이미 있으면 넣어주고, 없으면 null 리턴
			HttpSession session = request.getSession(false);
			String result = "";
			// null 체크
			if(session != null) {
				MemberVO user = (MemberVO)session.getAttribute("user");
				user.setMem_msg(mem_msg);
				user.setMem_nickname(mem_nickname);
				user.setMem_phone(mem_phone);
				user.setMem_email(mem_email);
				service.update(user);
				result = "redirect:/member/mypage2";
			}else { 
				result = "redirect:/main/home";
			}
			mav.setViewName(result);
			return mav;
		}
		
		@RequestMapping("member/mypage2")
		public String mypage2(HttpServletRequest request) {
			return "mypage2";
		}
		
		// =================가이드===================
		@RequestMapping("/member/guide")
		public String guideView() {
			return "guide";
		}
	
}
