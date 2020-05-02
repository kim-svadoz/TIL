package board.comment;

import java.util.List;

public interface BoardCommentService {
	List<BoardCommentVO> commentView(String board_no);
	int insert(BoardCommentVO comment);
	int delete(String board_no);
	int deleteone(String b_comm_no);
	BoardCommentLikeVO likesearch(String comment_like_cno, String comment_like_mno);
	int likeinsert(String comment_like_cno, String comment_like_mno);
	int likedelete(BoardCommentLikeVO like);
}
