package job;

public class jobVO {
	private String job_no;
	private String job_name;
	
	public jobVO(){
		
	}

	public jobVO(String job_no, String job_name) {
		super();
		this.job_no = job_no;
		this.job_name = job_name;
	}

	public String getJob_no() {
		return job_no;
	}

	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	
	
}
