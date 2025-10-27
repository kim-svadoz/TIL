package board.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BoardCommentDAO")
public class BoardCommentDAOImpl implements BoardCommentDAO {
	@Autowired
	SqlSession sqlSession;
	@Override
	public List<BoardCommentVO> commentView(String board_no) {
		return sqlSession.selectList("board.comment.listAll", board_no);
	}
	@Override
	public int insert(BoardCommentVO comment) {
		return sqlSession.insert("board.comment.insert",comment);
	}
	@Override
	public int delete(String board_no) {
		return sqlSession.delete("board.comment.delete", board_no);
	}
	@Override
	public int deleteone(String b_comm_no) {
		return sqlSession.delete("board.comment.deleteone", b_comm_no);
	}
	@Override
	public BoardCommentLikeVO likesearch(String comment_like_cno, String comment_like_mno) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("comment_like_cno", comment_like_cno);
		map.put("comment_like_mno", comment_like_mno);
		return sqlSession.selectOne("board.comment.likesearch", map);
	}
	@Override
	public int likedelete(BoardCommentLikeVO like) {
		return sqlSession.delete("board.comment.likedelete", like);
	}
	@Override
	public int likeinsert(String comment_like_cno, String comment_like_mno) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("comment_like_cno", comment_like_cno);
		map.put("comment_like_mno", comment_like_mno);
		return sqlSession.insert("board.comment.likedelete", map);
	}
}
