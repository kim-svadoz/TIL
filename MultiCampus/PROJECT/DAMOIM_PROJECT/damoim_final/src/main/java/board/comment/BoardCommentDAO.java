package board.comment;

import java.util.List;

public interface BoardCommentDAO {
	List<BoardCommentVO> commentView(String board_no);
	int insert(BoardCommentVO comment);
	int delete(String board_no);
	int deleteone(String b_comm_no);
	BoardCommentLikeVO likesearch(String commnet_like_cno, String comment_like_mno);
	int likeinsert(String commnet_like_cno, String comment_like_mno);
	int likedelete(BoardCommentLikeVO like);
}
