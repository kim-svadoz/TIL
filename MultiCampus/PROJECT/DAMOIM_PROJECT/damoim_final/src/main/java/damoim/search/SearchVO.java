package damoim.search;

import java.sql.Date;

public class SearchVO {
	private String gath_no;
	private String gath_name;
	private String gath_info;
	private String gath_intro;
	private Date gath_opendate;
	private String gath_major;
	private String gath_minor;
	private String gath_chat;
	
	private String major_mjno;
	private String major_name;
	
	private String major_mjnos;
	
	public SearchVO() {
	}

	public SearchVO(String gath_no, String gath_name, String gath_info, String gath_intro, Date gath_opendate,
			String gath_major, String gath_minor, String gath_chat, String major_mjno, String major_name,
			String major_mjnos) {
		super();
		this.gath_no = gath_no;
		this.gath_name = gath_name;
		this.gath_info = gath_info;
		this.gath_intro = gath_intro;
		this.gath_opendate = gath_opendate;
		this.gath_major = gath_major;
		this.gath_minor = gath_minor;
		this.gath_chat = gath_chat;
		this.major_mjno = major_mjno;
		this.major_name = major_name;
		this.major_mjnos = major_mjnos;
	}

	public SearchVO(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getGath_no() {
		return gath_no;
	}

	public void setGath_no(String gath_no) {
		this.gath_no = gath_no;
	}

	public String getGath_name() {
		return gath_name;
	}

	public void setGath_name(String gath_name) {
		this.gath_name = gath_name;
	}

	public String getGath_info() {
		return gath_info;
	}

	public void setGath_info(String gath_info) {
		this.gath_info = gath_info;
	}

	public String getGath_intro() {
		return gath_intro;
	}

	public void setGath_intro(String gath_intro) {
		this.gath_intro = gath_intro;
	}

	public Date getGath_opendate() {
		return gath_opendate;
	}

	public void setGath_opendate(Date gath_opendate) {
		this.gath_opendate = gath_opendate;
	}

	public String getGath_major() {
		return gath_major;
	}

	public void setGath_major(String gath_major) {
		this.gath_major = gath_major;
	}

	public String getGath_minor() {
		return gath_minor;
	}

	public void setGath_minor(String gath_minor) {
		this.gath_minor = gath_minor;
	}

	public String getGath_chat() {
		return gath_chat;
	}

	public void setGath_chat(String gath_chat) {
		this.gath_chat = gath_chat;
	}

	public String getMajor_mjno() {
		return major_mjno;
	}

	public void setMajor_mjno(String major_mjno) {
		this.major_mjno = major_mjno;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getMajor_mjnos() {
		return major_mjnos;
	}

	public void setMajor_mjnos(String major_mjnos) {
		this.major_mjnos = major_mjnos;
	}

	@Override
	public String toString() {
		return "SearchVO [gath_no=" + gath_no + ", gath_name=" + gath_name + ", gath_info=" + gath_info
				+ ", gath_intro=" + gath_intro + ", gath_opendate=" + gath_opendate + ", gath_major=" + gath_major
				+ ", gath_minor=" + gath_minor + ", gath_chat=" + gath_chat + ", major_mjno=" + major_mjno
				+ ", major_name=" + major_name + ", major_mjnos=" + major_mjnos + "]";
	}
	
	
}
	