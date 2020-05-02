package moim.list;

import java.sql.Date;

public class MoimListVO {
	private String sche_no;
	private String sche_gno;
	private String sche_name;
	private Date sche_date;
	private String sche_time;
	private String sche_loc;
	private String sche_fee;
	private String sche_limit;
	private String sche_chat;
	private String sche_master;
	private String sche_mem_mno;
	private String sche_mem_gno;
	private String sche_mem_sno;
	private String sche_lat;
	private String sche_lng;
	public MoimListVO() {
		
	}
	public MoimListVO(String sche_no, String sche_gno, String sche_name, Date sche_date, String sche_time,
			String sche_loc, String sche_fee, String sche_limit, String sche_chat, String sche_master,
			String sche_mem_mno, String sche_mem_gno, String sche_mem_sno, String sche_lat, String sche_lng) {
		super();
		this.sche_no = sche_no;
		this.sche_gno = sche_gno;
		this.sche_name = sche_name;
		this.sche_date = sche_date;
		this.sche_time = sche_time;
		this.sche_loc = sche_loc;
		this.sche_fee = sche_fee;
		this.sche_limit = sche_limit;
		this.sche_chat = sche_chat;
		this.sche_master = sche_master;
		this.sche_mem_mno = sche_mem_mno;
		this.sche_mem_gno = sche_mem_gno;
		this.sche_mem_sno = sche_mem_sno;
		this.sche_lat = sche_lat;
		this.sche_lng = sche_lng;
	}
	public String getSche_no() {
		return sche_no;
	}
	public void setSche_no(String sche_no) {
		this.sche_no = sche_no;
	}
	public String getSche_gno() {
		return sche_gno;
	}
	public void setSche_gno(String sche_gno) {
		this.sche_gno = sche_gno;
	}
	public String getSche_name() {
		return sche_name;
	}
	public void setSche_name(String sche_name) {
		this.sche_name = sche_name;
	}
	public Date getSche_date() {
		return sche_date;
	}
	public void setSche_date(Date sche_date) {
		this.sche_date = sche_date;
	}
	public String getSche_time() {
		return sche_time;
	}
	public void setSche_time(String sche_time) {
		this.sche_time = sche_time;
	}
	public String getSche_loc() {
		return sche_loc;
	}
	public void setSche_loc(String sche_loc) {
		this.sche_loc = sche_loc;
	}
	public String getSche_fee() {
		return sche_fee;
	}
	public void setSche_fee(String sche_fee) {
		this.sche_fee = sche_fee;
	}
	public String getSche_limit() {
		return sche_limit;
	}
	public void setSche_limit(String sche_limit) {
		this.sche_limit = sche_limit;
	}
	public String getSche_chat() {
		return sche_chat;
	}
	public void setSche_chat(String sche_chat) {
		this.sche_chat = sche_chat;
	}
	public String getSche_master() {
		return sche_master;
	}
	public void setSche_master(String sche_master) {
		this.sche_master = sche_master;
	}
	public String getSche_mem_mno() {
		return sche_mem_mno;
	}
	public void setSche_mem_mno(String sche_mem_mno) {
		this.sche_mem_mno = sche_mem_mno;
	}
	public String getSche_mem_gno() {
		return sche_mem_gno;
	}
	public void setSche_mem_gno(String sche_mem_gno) {
		this.sche_mem_gno = sche_mem_gno;
	}
	public String getSche_mem_sno() {
		return sche_mem_sno;
	}
	public void setSche_mem_sno(String sche_mem_sno) {
		this.sche_mem_sno = sche_mem_sno;
	}
	public String getSche_lat() {
		return sche_lat;
	}
	public void setSche_lat(String sche_lat) {
		this.sche_lat = sche_lat;
	}
	public String getSche_lng() {
		return sche_lng;
	}
	public void setSche_lng(String sche_lng) {
		this.sche_lng = sche_lng;
	}
	@Override
	public String toString() {
		return "MoimListVO [sche_no=" + sche_no + ", sche_gno=" + sche_gno + ", sche_name=" + sche_name + ", sche_date="
				+ sche_date + ", sche_time=" + sche_time + ", sche_loc=" + sche_loc + ", sche_fee=" + sche_fee
				+ ", sche_limit=" + sche_limit + ", sche_chat=" + sche_chat + ", sche_master=" + sche_master
				+ ", sche_mem_mno=" + sche_mem_mno + ", sche_mem_gno=" + sche_mem_gno + ", sche_mem_sno=" + sche_mem_sno
				+ ", sche_lat=" + sche_lat + ", sche_lng=" + sche_lng + "]";
	}
	

}
	