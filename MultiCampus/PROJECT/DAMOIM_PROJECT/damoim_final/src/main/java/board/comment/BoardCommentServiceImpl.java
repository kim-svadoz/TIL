package board.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {
	
	@Autowired
	@Qualifier("BoardCommentDAO")
	BoardCommentDAO dao;
	
	@Override
	public List<BoardCommentVO> commentView(String board_no) {
		return dao.commentView(board_no);
	}
	@Override
	public int insert(BoardCommentVO comment) {
		return dao.insert(comment);
	}
	@Override
	public int delete(String board_no) {
		return dao.delete(board_no);
	}
	@Override
	public int deleteone(String b_comm_no) {
		return dao.deleteone(b_comm_no);
	}
	@Override
	public BoardCommentLikeVO likesearch(String comment_like_cno, String comment_like_mno){
		return dao.likesearch(comment_like_cno,comment_like_mno);
	}
	@Override
	public int likedelete(BoardCommentLikeVO like) {
		return dao.likedelete(like);
	}
	@Override
	public int likeinsert(String comment_like_cno, String comment_like_mno) {
		return dao.likeinsert(comment_like_cno, comment_like_mno);
	}
}
