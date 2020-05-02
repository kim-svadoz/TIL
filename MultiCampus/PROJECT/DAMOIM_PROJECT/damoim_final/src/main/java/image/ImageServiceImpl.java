package image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	@Qualifier("ImageDAO")
	ImageDAO dao;
	@Override
	public List<ImageVO> searchlistimage(String search, String tag) {
		return dao.searchlistimage(search, tag);
	}
	
	@Override
	public ImageVO searchoneimage(String search, String tag) {
		return dao.searchoneimage(search, tag);
	}	
	
	@Override
	public int insertimage(String name, String image_gno, String image_mno, String image_bno, String tag) {
		return dao.insertimage(name, image_gno, image_mno, image_bno, tag);
	}
}
