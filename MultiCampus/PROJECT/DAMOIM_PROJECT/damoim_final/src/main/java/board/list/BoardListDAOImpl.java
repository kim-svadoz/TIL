package board.list;

import java.util.HashMap;
import java.util.List;
//mybatis의 핵심클래스를 이용해서 작성
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardListDAO")
public class BoardListDAOImpl implements BoardListDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<BoardListVO> boardList(String gath_no, String board_category) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gath_no",gath_no);
		map.put("board_category", board_category);
		return sqlSession.selectList("board.list.listAll",map);
	}
	
	@Override
	public List<BoardListVO> boardsearchList(String gath_no, String date, String tag, String search) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gath_no",gath_no);
		map.put("date", date);
		map.put("tag", tag);
		map.put("search", search);
		return sqlSession.selectList("board.list.searchlist",map);
	}
	
	@Override
	public int insert(BoardListVO board) {
		return sqlSession.insert("board.list.insert",board);
	}
	
	@Override
	public List<BoardListVO> category(String gath_no, String board_category) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gath_no",gath_no);
		map.put("board_category",board_category);
		return sqlSession.selectList("board.list.categorySearch",map);
	}
	@Override
	public BoardListVO detailView(String board_no) {
		return sqlSession.selectOne("board.list.detailView", board_no);
	}
	@Override
	public int profileUpload(String gath_no, String board_no) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("gath_no", gath_no);
		map.put("board_no", board_no);
		return sqlSession.insert("",map);
	}
	@Override
	public int delete(String board_no) {
		return sqlSession.delete("board.list.delete", board_no);
	}
	@Override
	public int update(BoardListVO board) {
		return sqlSession.update("board.list.update", board);
	}
	@Override
	public int searchcount(String board_no) {
		return sqlSession.update("board.list.searchcount",board_no);
	}
}
