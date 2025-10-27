package damoim.myhome.mylist;

import java.sql.Date;

public class MyListVO {
	
	//조인에 필요한 변수들
	//회원 테이블
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_nickname;
	private int mem_age;
	private Date mem_birth;
	private String mem_gender;
	private String mem_home;
	private String mem_office;
	private String mem_neighbor;
	private int mem_agegroup;
	private String mem_business;
	private String mem_job;
	private String mem_msg;
	private String mem_profile;
	private String mem_count; // 소모임의 일정 참석에서 사용
	
	
	//모임테이블
	private String gath_no;
	private String gath_name;
	private String gath_info;
	private String gath_intro;
	private Date gath_opendate;
	private String gath_major;
	private String gath_minor;
	private String gath_chat;
	
	//게시글테이블
	private String board_no;		//게시글번호
	private String board_title;		//제목
	private String board_content;	//내용
	private String board_gno;		//어떤모임의 게시글인지
	private String board_mno;		//작성자회원번호private String mem_id;
	private Date board_date;		//작성일
	private int board_hit;			//조회수
	private int board_like;			//좋아요개수
	private String board_category;
	
	//스케줄테이블
	private String sche_no;		//모임 일정 번호
	private String sche_gno;	//모임 분류 번호
	private String sche_name;	//모임 별칭
	private Date sche_date;		//일정 날짜
	private String sche_time;	//일정 시간
	private String sche_loc;	//장소 키워드
	private String sche_lat;	//위도
	private String sche_lng;	//경도
	private String sche_fee;	//회비
	private String sche_limit;	//정원
	private String sche_chat;	//카카오톡 오픈채팅 링크
	private String sche_master;	//모임장
	private String sche_context;//모임소개글
	
	//흥미테이블
	private String major_mjno;
	private String major_name;
	
	//흥미와 회원묶음 테이블
	private String mem_mno;
	private String mem_mjno;
	
	//기본 생성자
	public MyListVO() {
		
	}

	public MyListVO(String mem_id, String mem_pass, String mem_name, String mem_nickname, int mem_age, Date mem_birth,
			String mem_gender, String mem_home, String mem_office, String mem_neighbor, int mem_agegroup,
			String mem_business, String mem_job, String mem_msg, String mem_profile, String mem_count, String gath_no,
			String gath_name, String gath_info, String gath_intro, Date gath_opendate, String gath_major,
			String gath_minor, String gath_chat, String board_no, String board_title, String board_content,
			String board_gno, String board_mno, Date board_date, int board_hit, int board_like, String board_category,
			String sche_no, String sche_gno, String sche_name, Date sche_date, String sche_time, String sche_loc,
			String sche_lat, String sche_lng, String sche_fee, String sche_limit, String sche_chat, String sche_master,
			String sche_context, String major_mjno, String major_name, String mem_mno, String mem_mjno) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_nickname = mem_nickname;
		this.mem_age = mem_age;
		this.mem_birth = mem_birth;
		this.mem_gender = mem_gender;
		this.mem_home = mem_home;
		this.mem_office = mem_office;
		this.mem_neighbor = mem_neighbor;
		this.mem_agegroup = mem_agegroup;
		this.mem_business = mem_business;
		this.mem_job = mem_job;
		this.mem_msg = mem_msg;
		this.mem_profile = mem_profile;
		this.mem_count = mem_count;
		this.gath_no = gath_no;
		this.gath_name = gath_name;
		this.gath_info = gath_info;
		this.gath_intro = gath_intro;
		this.gath_opendate = gath_opendate;
		this.gath_major = gath_major;
		this.gath_minor = gath_minor;
		this.gath_chat = gath_chat;
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_gno = board_gno;
		this.board_mno = board_mno;
		this.board_date = board_date;
		this.board_hit = board_hit;
		this.board_like = board_like;
		this.board_category = board_category;
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
		this.major_mjno = major_mjno;
		this.major_name = major_name;
		this.mem_mno = mem_mno;
		this.mem_mjno = mem_mjno;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public int getMem_age() {
		return mem_age;
	}

	public void setMem_age(int mem_age) {
		this.mem_age = mem_age;
	}

	public Date getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(Date mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public String getMem_home() {
		return mem_home;
	}

	public void setMem_home(String mem_home) {
		this.mem_home = mem_home;
	}

	public String getMem_office() {
		return mem_office;
	}

	public void setMem_office(String mem_office) {
		this.mem_office = mem_office;
	}

	public String getMem_neighbor() {
		return mem_neighbor;
	}

	public void setMem_neighbor(String mem_neighbor) {
		this.mem_neighbor = mem_neighbor;
	}

	public int getMem_agegroup() {
		return mem_agegroup;
	}

	public void setMem_agegroup(int mem_agegroup) {
		this.mem_agegroup = mem_agegroup;
	}

	public String getMem_business() {
		return mem_business;
	}

	public void setMem_business(String mem_business) {
		this.mem_business = mem_business;
	}

	public String getMem_job() {
		return mem_job;
	}

	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}

	public String getMem_msg() {
		return mem_msg;
	}

	public void setMem_msg(String mem_msg) {
		this.mem_msg = mem_msg;
	}

	public String getMem_profile() {
		return mem_profile;
	}

	public void setMem_profile(String mem_profile) {
		this.mem_profile = mem_profile;
	}

	public String getMem_count() {
		return mem_count;
	}

	public void setMem_count(String mem_count) {
		this.mem_count = mem_count;
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

	public String getBoard_no() {
		return board_no;
	}

	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_gno() {
		return board_gno;
	}

	public void setBoard_gno(String board_gno) {
		this.board_gno = board_gno;
	}

	public String getBoard_mno() {
		return board_mno;
	}

	public void setBoard_mno(String board_mno) {
		this.board_mno = board_mno;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public int getBoard_hit() {
		return board_hit;
	}

	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}

	public String getBoard_category() {
		return board_category;
	}

	public void setBoard_category(String board_category) {
		this.board_category = board_category;
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

	public String getMem_mno() {
		return mem_mno;
	}

	public void setMem_mno(String mem_mno) {
		this.mem_mno = mem_mno;
	}

	public String getMem_mjno() {
		return mem_mjno;
	}

	public void setMem_mjno(String mem_mjno) {
		this.mem_mjno = mem_mjno;
	}

	

	
	
}