package interest;

public class member_interestVO {
	private String mem_mno;
	private String mem_mjno;
	
	public member_interestVO() {
		
	}

	public member_interestVO(String mem_mno, String mem_mjno) {
		super();
		this.mem_mno = mem_mno;
		this.mem_mjno = mem_mjno;
	}

	@Override
	public String toString() {
		return "member_interestVO [mem_mno=" + mem_mno + ", mem_mjno=" + mem_mjno + "]";
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
