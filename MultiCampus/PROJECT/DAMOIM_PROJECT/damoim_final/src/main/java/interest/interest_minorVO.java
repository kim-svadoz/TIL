package interest;

public class interest_minorVO {
	private String minor_mjno;
	private String minor_mino;
	private String minor_name;
	
	public interest_minorVO() {
		
	}

	public interest_minorVO(String minor_mjno, String minor_mino, String minor_name) {
		super();
		this.minor_mjno = minor_mjno;
		this.minor_mino = minor_mino;
		this.minor_name = minor_name;
	}

	@Override
	public String toString() {
		return "interest_minorVO [minor_mjno=" + minor_mjno + ", minor_mino=" + minor_mino + ", minor_name="
				+ minor_name + "]";
	}

	public String getMinor_mjno() {
		return minor_mjno;
	}

	public void setMinor_mjno(String minor_mjno) {
		this.minor_mjno = minor_mjno;
	}

	public String getMinor_mino() {
		return minor_mino;
	}

	public void setMinor_mino(String minor_mino) {
		this.minor_mino = minor_mino;
	}

	public String getMinor_name() {
		return minor_name;
	}

	public void setMinor_name(String minor_name) {
		this.minor_name = minor_name;
	}
	
}
