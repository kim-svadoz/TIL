package damoim.myhome.mylist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVO;

@Controller
public class MyListController {

	@Autowired
	MyListService service;

	@RequestMapping("/myhome/myhome.do")
	public ModelAndView myList(String gath_name, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		System.out.println("session nullchk " + vo);

		if (vo != null) {
			List<MyListVO> mylist = service.myList(vo.getMem_id());
			List<MyListVO> myschedulelist = service.myScheduleList(vo.getMem_id());
			List<MyListVO> recommendlist = service.recommendList(vo.getMem_id());
			mav.addObject("mylist", mylist);
			mav.addObject("myschedulelist", myschedulelist);
			mav.addObject("recommendlist", recommendlist);
			mav.setViewName("myhome");
		} else {
			mav.addObject("memchk", false);
			mav.setViewName("login");
		}
		return mav;

	}

	/*@RequestMapping(value = "/myhome/ajax_mylist.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody List<MyListVO> myList( String gath_name, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO vo1 = (MemberVO) session.getAttribute("user");
		System.out.println("세션 nullchk " + vo1);
		System.out.println("gath_name " + gath_name);
		List<MyListVO> mylist = service.myList(vo1.getMem_id());
		System.out.println("마이리스트"+mylist);
		return mylist;
	}
*/
}
