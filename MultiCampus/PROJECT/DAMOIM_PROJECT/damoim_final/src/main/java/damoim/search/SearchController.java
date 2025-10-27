package damoim.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import interest.interestService;
import interest.interest_majorVO;

@Controller
public class SearchController {
	
	@Autowired
	SearchService service;
	@Autowired
	interestService inter_service; 
	
	@RequestMapping(value="/search/searchchk.do", method=RequestMethod.POST)
	public ModelAndView searchPage(String major_mjno, String major_name ) {
		ModelAndView mav = new ModelAndView();
		List<interest_majorVO> majorlist = inter_service.majorlist();
		mav.addObject("majorlist", majorlist);
		mav.setViewName("searchchk");
		return mav;
	
	}	
		
		
	@RequestMapping(value="/search/search.do", method=RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request) {
		String[] major_mjno = request.getParameterValues("mem_mjno");
		System.out.println("값"+major_mjno);
		ModelAndView mav = new ModelAndView();
		List<SearchVO> list = service.searchList(major_mjno);
		mav.addObject("searchList", list);
		mav.setViewName("search");
		return mav;
	}
}
