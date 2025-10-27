package board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.MemberVO;

@Controller
public class CommentComtroller {
	@Autowired
	BoardCommentService commentservice;

	@RequestMapping(value = "/gathering/article/comment.do", method = RequestMethod.POST)
	public String insert(BoardCommentVO comment, String gath_no, String board_no) {
		int result = commentservice.insert(comment);
		return "redirect:/gathering/article.do?gath_no=" + gath_no + "&board_no=" + board_no + "&pagenum=0";
	}
	@RequestMapping("/gathering/article/commentdelete.do")
	public String delete(String b_comm_no, String gath_no, String board_no) {
		int result = commentservice.deleteone(b_comm_no);
		return "redirect:/gathering/article.do?gath_no=" + gath_no + "&board_no=" + board_no + "&pagenum=0";
	}
	
	@RequestMapping("/gathering/comment/like.do")
	public String like(String gath_no, String board_no, String comment_no, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
		if (vo != null) {
			System.out.println(1);
			BoardCommentLikeVO like = commentservice.likesearch(comment_no, vo.getMem_id());
			if (like == null) {
				commentservice.likeinsert(comment_no, vo.getMem_id());
			} else {
				System.out.println(3);
				commentservice.likedelete(like);
			}
		}
		System.out.println(4);
		
		return "redirect:/gathering/article.do?gath_no="+gath_no+"&board_no="+board_no+"&pagenum=0";
	}
}
