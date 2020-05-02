package board.comment;

import java.sql.Date;

public class BoardCommentVO {
	private String b_comm_no;		//댓글번호
	private String b_comm_content;	//내용
	private String b_comm_mno;		//작성회원
	private Date b_comm_date;		//날짜
	private String b_comm_bno;		//게시글번호
	private int b_comm_depth;		//뎁스
	private String b_comm_parent;	//부모댓글번호
	private String b_comm_nickname;
	public BoardCommentVO() {}
	
	
	

	public String getB_comm_nickname() {
		return b_comm_nickname;
	}
	public void setB_comm_nickname(String b_comm_nickname) {
		this.b_comm_nickname = b_comm_nickname;
	}
	public Date getB_comm_date() {
		return b_comm_date;
	}
	public void setB_comm_date(Date b_comm_date) {
		this.b_comm_date = b_comm_date;
	}
	public String getB_comm_no() {
		return b_comm_no;
	}
	public void setB_comm_no(String b_comm_no) {
		this.b_comm_no = b_comm_no;
	}
	public String getB_comm_content() {
		return b_comm_content;
	}
	public void setB_comm_content(String b_comm_content) {
		this.b_comm_content = b_comm_content;
	}
	public String getB_comm_mno() {
		return b_comm_mno;
	}
	public void setB_comm_mno(String b_comm_mno) {
		this.b_comm_mno = b_comm_mno;
	}
	public String getB_comm_bno() {
		return b_comm_bno;
	}
	public void setB_comm_bno(String b_comm_bno) {
		this.b_comm_bno = b_comm_bno;
	}
	public int getB_comm_depth() {
		return b_comm_depth;
	}
	public void setB_comm_depth(int b_comm_depth) {
		this.b_comm_depth = b_comm_depth;
	}
	public String getB_comm_parent() {
		return b_comm_parent;
	}
	public void setB_comm_parent(String b_comm_parent) {
		this.b_comm_parent = b_comm_parent;
	}
	
	
}
