package board.list;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.comment.BoardCommentService;
import board.comment.BoardCommentVO;
import gathering.info.GatheringInfoService;
import gathering.info.GatheringInfoVO;
import gathering.info.GatheringMemberVO;
import gathering.schedule.member.ScheMemService;
import image.ImageService;
import image.ImageVO;
import member.MemberService;
import member.MemberVO;
@Controller
public class BoardController {
	@Autowired
	BoardListService boardservice;
	
	@Autowired
	GatheringInfoService gatheringservice;
	
	@Autowired
	ScheMemService scheMemService;
	
	@Autowired
	BoardCommentService commentservice;
	
	@Autowired
	ImageService imageservice;
	
	@Autowired
	MemberService memberservice;
	
	@RequestMapping("/gathering/board.do")
	public ModelAndView boardList(String gath_no, String board_category,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		List<BoardListVO> list = boardservice.boardList(gath_no,board_category);
		GatheringInfoVO gathering = gatheringservice.read(gath_no);
		mav.addObject("gathering", gathering);
		mav.addObject("boardlist", list);
		mav.addObject("gath_no",gath_no);
		mav.addObject("board_category",board_category);
		mav.addObject("boardlistcount",list.size());
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
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
		System.out.println(list.size());
		mav.setViewName("gathering/board");
		return mav;
	}

	@RequestMapping("/gathering/article.do")
	public ModelAndView gatheringArticle(String gath_no, String board_no, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		mav.addObject("gath_no",gath_no);
		mav.addObject("board_no",board_no);
		
		boardservice.searchcount(board_no); // 조회수
		
		List<BoardCommentVO> comment = commentservice.commentView(board_no);
		mav.addObject("comment", comment);
		mav.addObject("commentcount",comment.size());
		
		GatheringInfoVO gathering = gatheringservice.read(gath_no);
		mav.addObject("gathering", gathering);
		BoardListVO board = boardservice.detailView(board_no);
		mav.addObject("board",board);
		
		MemberVO member = scheMemService.memberDetail(board.getBoard_mno());
		mav.addObject("member", member);
		
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
		
		List<ImageVO> img = imageservice.searchlistimage(board_no, "board");
		mav.addObject("img", img);
		
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
		mav.setViewName("gathering/article");
		return mav;
	}
	@RequestMapping("/gathering/articledelete.do")
	public String articleDelete(String board_no,String gath_no, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		if(vo!=null){
			commentservice.delete(board_no);
			int result = boardservice.delete(board_no);
		}
		return "redirect:/gathering/board.do?gath_no="+gath_no+"&pagenum=0&board_category=all";
	}
	
	@RequestMapping("/gathering/newarticle.do")
	public ModelAndView gatheringNewArticle(String gath_no, String state, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
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
		GatheringInfoVO gathering = gatheringservice.read(gath_no);
		mav.addObject("gathering", gathering);
		mav.addObject("gath_no",gath_no);
		mav.addObject("state",state);
		mav.setViewName("gathering/newarticle");
		return mav;
	}
	
	
	
	
	//글쓰기
	@RequestMapping(value="/gathering/writearticle.do", method = RequestMethod.POST)
	public ModelAndView insert(BoardListVO board, HttpServletRequest request) throws Exception {
		String gath_no =board.getBoard_gno();
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		int result = boardservice.insert(board);
		MemberVO vo = (MemberVO)session.getAttribute("user");
		System.out.println(board);
	    
		mav.setViewName("redirect:/gathering/board.do?gath_no="+gath_no+"&pagenum=0&board_category=all");
		return mav;
	}
	@RequestMapping(value="/imageupload.do", method = RequestMethod.POST)
	public String imageinsert(HttpServletRequest request, String type, String value, String gath_no) {
		 System.out.println(type);
		 System.out.println(value);
		 System.out.println(gath_no);
		String path="";
		if(type.equals("gathering"))
			path="/WEB-INF/gathering/images";
		else if(type.equals("board"))
			path="/WEB-INF/gathering/boardimages";
		else if(type.equals("member"))
			path="/WEB-INF/member/memimg";
		String uploadPath = request.getServletContext().getRealPath(path);
	    System.out.println("절대경로 : " + uploadPath +"<br/>");
	   
	    int maxSize =1024 *1024 *10;// 한번에 올릴 수 있는 파일 용량 : 10M로 제한
	    String name ="";
	    String subject ="";
	    String fileName1 ="";// 중복처리된 이름
	    String originalName1 ="";// 중복 처리전 실제 원본 이름
	    long fileSize =0;// 파일 사이즈
	    String fileType ="";// 파일 타입
	    MultipartRequest multi =null;
	    try{
	        // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
	        multi =new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
	        // 전송한 전체 파일이름들을 가져옴
	        Enumeration files = multi.getFileNames();
	        while(files.hasMoreElements()){
	            // form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
	            String file1 = (String)files.nextElement();// 파일 input에 지정한 이름을 가져옴
	            // 그에 해당하는 실재 파일 이름을 가져옴
	            originalName1 = multi.getOriginalFileName(file1);
	            // 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
	            // 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
	            fileName1 = multi.getFilesystemName(file1);
	            // 파일 타입 정보를 가져옴
	            fileType = multi.getContentType(file1);
	            // input file name에 해당하는 실재 파일을 가져옴
	            File file = multi.getFile(file1);
	            // 그 파일 객체의 크기를 알아냄
	            fileSize = file.length();
	            if(type.equals("member")) {
	            	imageservice.insertimage(fileName1, "null", value, "null", "member");
	            	memberservice.profileupdate(fileName1, value);
	            }
	            else if(type.equals("board"))
	            	imageservice.insertimage(fileName1, gath_no, "null", value, "board");
	            else if(type.equals("gathering"))
	            	imageservice.insertimage(fileName1, gath_no, "null", "null", "gathering");
	        }
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return "";
	}
	
	//게시글수정
	@RequestMapping("/gathering/updatearticle.do")
	public ModelAndView updateArticle(BoardListVO board, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		int result = boardservice.update(board);
		mav.setViewName("redirect:/gathering/board.do?gath_no="+board.getBoard_gno()+"&pagenum=0&board_category=all");
		return mav;
	}
	
	
	//게시글 검색하기
	@RequestMapping(value="/gathering/boardsearch.do", method=RequestMethod.GET)
	public ModelAndView searchArticle(String gath_no, String date, String tag ,String search, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		List<BoardListVO> list = boardservice.boardsearchList(gath_no, date, tag, search);
		GatheringInfoVO gathering = gatheringservice.read(gath_no);
		mav.addObject("gathering", gathering);
		mav.addObject("boardlist", list);
		mav.addObject("gath_no",gath_no);
		mav.addObject("board_category","all");
		mav.addObject("boardlistcount",list.size());
		List<ImageVO> imgtitle = imageservice.searchlistimage(gath_no, "title");
		mav.addObject("imgtitle", imgtitle);
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
		System.out.println(list.size());
		mav.setViewName("gathering/boardsearch");
		return mav;
	}
}
