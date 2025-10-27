package image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ImageDAO")
public class ImageDAOImpl implements ImageDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ImageVO> searchlistimage(String search, String tag) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("search", search);
		map.put("tag", tag);
		return sqlSession.selectList("image.searchlist", map);
	}
	@Override
	public ImageVO searchoneimage(String search, String tag) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("search", search);
		map.put("tag", tag);
		return sqlSession.selectOne("image.searchone", map);
	}
	@Override
	public int insertimage(String name, String image_gno, String image_mno, String image_bno, String tag) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("image_gno", image_gno);
		map.put("image_mno", image_mno);
		map.put("image_bno", image_bno);
		map.put("tag", tag);
		return sqlSession.insert("image.insert",map);
	}

}
