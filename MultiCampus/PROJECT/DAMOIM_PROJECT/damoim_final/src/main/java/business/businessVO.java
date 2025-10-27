package business;

public class businessVO {
	private String busi_no;
	private String busi_name;
	
	public businessVO() {
	
	}
			
	public businessVO(String busi_no, String busi_name) {
		super();
		this.busi_no = busi_no;
		this.busi_name = busi_name;
	}

	@Override
	public String toString() {
		return "businessVO [busi_no=" + busi_no + ", busi_name=" + busi_name + "]";
	}

	public String getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}

	public String getBusi_name() {
		return busi_name;
	}

	public void setBusi_name(String busi_name) {
		this.busi_name = busi_name;
	} 
	
	
}