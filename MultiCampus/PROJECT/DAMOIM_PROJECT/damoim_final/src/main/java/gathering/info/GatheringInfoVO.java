package gathering.info;

import java.sql.Date;

public class GatheringInfoVO {
	private String gath_no;
	private String gath_name;
	private String gath_info;
	private String gath_intro;
	private Date gath_opendate;
	private String gath_major;
	private String gath_minor;
	private String gath_chat;
	private String gath_loc;
	private String gath_master;
	public GatheringInfoVO() {}
	
	public GatheringInfoVO(String gath_no, String gath_name, String gath_info, String gath_intro, Date gath_opendate,
			String gath_major, String gath_minor, String gath_chat) {
		super();
		this.gath_no = gath_no;
		this.gath_name = gath_name;
		this.gath_info = gath_info;
		this.gath_intro = gath_intro;
		this.gath_opendate = gath_opendate;
		this.gath_major = gath_major;
		this.gath_minor = gath_minor;
		this.gath_chat = gath_chat;
	}

	
	
	@Override
	public String toString() {
		return "GatheringInfoVO [gath_no=" + gath_no + ", gath_name=" + gath_name + ", gath_info=" + gath_info
				+ ", gath_intro=" + gath_intro + ", gath_opendate=" + gath_opendate + ", gath_major=" + gath_major
				+ ", gath_minor=" + gath_minor + ", gath_chat=" + gath_chat + "]";
	}

	
	public String getGath_loc() {
		return gath_loc;
	}

	public void setGath_loc(String gath_loc) {
		this.gath_loc = gath_loc;
	}

	public String getGath_master() {
		return gath_master;
	}

	public void setGath_master(String gath_master) {
		this.gath_master = gath_master;
	}

	public String getGath_info() {
		return gath_info;
	}

	public void setGath_info(String gath_info) {
		this.gath_info = gath_info;
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

	
}
