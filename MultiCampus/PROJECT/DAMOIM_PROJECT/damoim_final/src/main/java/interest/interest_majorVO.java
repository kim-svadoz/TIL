package interest;

public class interest_majorVO {
	private String major_mjno;
	private String major_name;
	
	public interest_majorVO(){
		
	}

	public interest_majorVO(String major_mjno, String major_name) {
		super();
		this.major_mjno = major_mjno;
		this.major_name = major_name;
	}

	@Override
	public String toString() {
		return "interest_majorVO [major_mjno=" + major_mjno + ", major_name=" + major_name + "]";
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

	
}