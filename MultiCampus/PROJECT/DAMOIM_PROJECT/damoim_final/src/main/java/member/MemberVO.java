package member;

import java.sql.Date;

public class MemberVO {
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
	private String mem_count;
	private String mem_email;
	private String mem_phone;
	private String mem_authkey;
	private int mem_authstatus;
	
	public MemberVO() {
		
	}
	
	// insert용 생성자(회원가입 페이지1)
	public MemberVO(String mem_id, String mem_pass, String mem_name, String mem_nickname, Date mem_birth,
			 String mem_phone) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_nickname = mem_nickname;
		this.mem_birth = mem_birth;
		this.mem_phone = mem_phone;
	}
	

	public MemberVO(String mem_id, String mem_pass, String mem_name, String mem_nickname, int mem_age, Date mem_birth,
			String mem_gender, String mem_home, String mem_office, String mem_neighbor, int mem_agegroup,
			String mem_business, String mem_job, String mem_msg, String mem_profile, String mem_count, String mem_email,
			String mem_phone, String mem_authkey, int mem_authstatus) {
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
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
		this.mem_authkey = mem_authkey;
		this.mem_authstatus = mem_authstatus;
	}


	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_pass=" + mem_pass + ", mem_name=" + mem_name + ", mem_nickname="
				+ mem_nickname + ", mem_age=" + mem_age + ", mem_birth=" + mem_birth + ", mem_gender=" + mem_gender
				+ ", mem_home=" + mem_home + ", mem_office=" + mem_office + ", mem_neighbor=" + mem_neighbor
				+ ", mem_agegroup=" + mem_agegroup + ", mem_business=" + mem_business + ", mem_job=" + mem_job
				+ ", mem_msg=" + mem_msg + ", mem_profile=" + mem_profile + ", mem_count=" + mem_count + ", mem_email="
				+ mem_email + ", mem_phone=" + mem_phone + ", mem_authkey=" + mem_authkey + ", mem_authstatus="
				+ mem_authstatus + "]";
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

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_authkey() {
		return mem_authkey;
	}

	public void setMem_authkey(String mem_authkey) {
		this.mem_authkey = mem_authkey;
	}

	public int getMem_authstatus() {
		return mem_authstatus;
	}

	public void setMem_authstatus(int mem_authstatus) {
		this.mem_authstatus = mem_authstatus;
	}
	
}
