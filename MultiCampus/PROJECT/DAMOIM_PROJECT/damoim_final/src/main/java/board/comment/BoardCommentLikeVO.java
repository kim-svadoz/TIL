package board.comment;

public class BoardCommentLikeVO {
	private String comment_like_cno;
	private String comment_like_mno;
	
	public BoardCommentLikeVO() {}
	public BoardCommentLikeVO(String comment_like_cno, String comment_like_mno) {
		super();
		this.comment_like_cno = comment_like_cno;
		this.comment_like_mno = comment_like_mno;
	}
	
	@Override
	public String toString() {
		return "BoardCommentLikeVO [comment_like_cno=" + comment_like_cno + ", comment_like_mno=" + comment_like_mno
				+ "]";
	}
	public String getComment_like_cno() {
		return comment_like_cno;
	}
	public void setComment_like_cno(String comment_like_cno) {
		this.comment_like_cno = comment_like_cno;
	}
	public String getComment_like_mno() {
		return comment_like_mno;
	}
	public void setComment_like_mno(String comment_like_mno) {
		this.comment_like_mno = comment_like_mno;
	}
	
}
