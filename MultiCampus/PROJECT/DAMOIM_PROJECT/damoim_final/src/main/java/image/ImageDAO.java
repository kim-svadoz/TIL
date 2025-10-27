package image;

import java.util.List;

public interface ImageDAO {
	List<ImageVO> searchlistimage(String search, String tag);
	ImageVO searchoneimage(String search, String tag);
	int insertimage(String name, String image_gno, String image_mno, String image_bno, String tag);
	
}
