package gathering.schedule;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class ScheduleRegisterVO {
	String sche_no;		//모임 일정 번호
	String sche_gno;	//모임 분류 번호
	@NotEmpty(message="간단한 모임 이름을 설정해주세요.")
	String sche_name;	//모임 별칭
	Date sche_date;		//일정 날짜
	String sche_time;	//일정 시간
	String sche_loc;	//장소 키워드
	String sche_lat;	//위도
	String sche_lng;	//경도
	String sche_fee;	//회비
	@NotEmpty(message="정원은 필수 입력값입니다.")
	String sche_limit;	//정원
	String sche_chat;	//카카오톡 오픈채팅 링크
	String sche_master;	//모임장
	@NotEmpty(message="모임소개는 필수 입력값입니다.")
	String sche_context;//모임소개글
	
	public ScheduleRegisterVO() {
		
	}
	
	@Override
	public String toString() {
		return "ScheduleRegisterVO [sche_no=" + sche_no + ", sche_gno=" + sche_gno + ", sche_name=" + sche_name
				+ ", sche_date=" + sche_date + ", sche_time=" + sche_time + ", sche_loc=" + sche_loc + ", sche_lat="
				+ sche_lat + ", sche_lng=" + sche_lng + ", sche_fee=" + sche_fee + ", sche_limit=" + sche_limit
				+ ", sche_chat=" + sche_chat + ", sche_master=" + sche_master + ", sche_context=" + sche_context + "]";
	}

	public ScheduleRegisterVO(String sche_no, String sche_gno, String sche_name, Date sche_date, String sche_time,
			String sche_loc, String sche_lat, String sche_lng, String sche_fee, String sche_limit, String sche_chat,
			String sche_master, String sche_context) {
		super();
		this.sche_no = sche_no;
		this.sche_gno = sche_gno;
		this.sche_name = sche_name;
		this.sche_date = sche_date;
		this.sche_time = sche_time;
		this.sche_loc = sche_loc;
		this.sche_lat = sche_lat;
		this.sche_lng = sche_lng;
		this.sche_fee = sche_fee;
		this.sche_limit = sche_limit;
		this.sche_chat = sche_chat;
		this.sche_master = sche_master;
		this.sche_context = sche_context;
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

	public String getSche_context() {
		return sche_context;
	}

	public void setSche_context(String sche_context) {
		this.sche_context = sche_context;
	}
	
	
	
}
