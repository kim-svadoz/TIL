package image;

import java.sql.Date;

public class ImageVO {
	private String image_name;
	private String image_gno;
	private String image_mno;
	private String image_bno;
	private String tag;
	private Date image_date;
	public ImageVO() {}
	
	
	public ImageVO(String image_name, String image_gno, String image_mno, String image_bno, String tag,
			Date image_date) {
		super();
		this.image_name = image_name;
		this.image_gno = image_gno;
		this.image_mno = image_mno;
		this.image_bno = image_bno;
		this.tag = tag;
		this.image_date = image_date;
	}
	@Override
	public String toString() {
		return "ImageVO [image_name=" + image_name + ", image_gno=" + image_gno + ", image_mno=" + image_mno
				+ ", image_bno=" + image_bno + ", tag=" + tag + ", image_date=" + image_date + "]";
	}
	public Date getImage_date() {
		return image_date;
	}
	public void setImage_date(Date image_date) {
		this.image_date = image_date;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_gno() {
		return image_gno;
	}
	public void setImage_gno(String image_gno) {
		this.image_gno = image_gno;
	}
	public String getImage_mno() {
		return image_mno;
	}
	public void setImage_mno(String image_mno) {
		this.image_mno = image_mno;
	}
	public String getImage_bno() {
		return image_bno;
	}
	public void setImage_bno(String image_bno) {
		this.image_bno = image_bno;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
