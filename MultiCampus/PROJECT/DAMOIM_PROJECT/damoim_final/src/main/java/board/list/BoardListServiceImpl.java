package board.list;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardListServiceImpl implements BoardListService {

	@Autowired
	@Qualifier("boardListDAO")
	BoardListDAO dao;

	@Override
	public List<BoardListVO> boardList(String gath_no, String board_category) {
		List<BoardListVO> list = null;
		if (board_category != null) {
			if (board_category.equals("all")) {
				list = dao.boardList(gath_no, board_category);
			} else {
				list= dao.category(gath_no,board_category);
			}
		}
		return list;
	}
	@Override
	public List<BoardListVO> boardsearchList(String gath_no, String date, String tag, String search) {
		return dao.boardsearchList(gath_no, date, tag, search);
	}
	
	
	
	@Override
	public int insert(BoardListVO board) {
		return dao.insert(board);
	}
	@Override
	public BoardListVO detailView(String board_no) {
		return dao.detailView(board_no);
	}
	@Override
	public int profileUpload(String gath_no, String board_no, MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String realFolder = request.getSession().getServletContext().getRealPath("profileUpload");
		UUID uuid = UUID.randomUUID();
		String org_filename = file.getOriginalFilename();
		String str_filename = uuid.toString() + org_filename;
		
		System.out.println("원본 파일명 : " + org_filename);
		System.out.println("저장할 파일명 : " + str_filename);
		String filepath = realFolder + "/" + gath_no + "/" + str_filename;
		System.out.println("파일경로 : " + filepath);
		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}
		file.transferTo(f);
		out.println("profileUpload/"+gath_no+"/"+str_filename);
		out.close();
		//return dao.profileUpload(gath_no, board_no);
		return 0;
	}
	@Override
	public int delete(String board_no) {
		return dao.delete(board_no);
	}
	@Override
	public int update(BoardListVO board) {
		return dao.update(board);
	}
	@Override
	public int searchcount(String board_no) {
		return dao.searchcount(board_no);
	}
}
