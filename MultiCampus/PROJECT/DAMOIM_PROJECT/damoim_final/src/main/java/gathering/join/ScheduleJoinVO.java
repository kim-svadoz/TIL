package gathering.join;

public class ScheduleJoinVO {
	String sche_mem_mno;	//참석버튼을 누른 멤버 번호
	String sche_mem_gno;	//어떤 모임을 참석할지, 해당 모임의 번호(ex. A, B, C, D)
	String sche_mem_sno;	//일정번호. 모임생성될 때마다 1씩 증가
	
	public ScheduleJoinVO() {
		
		
	}
	public ScheduleJoinVO(String sche_mem_mno, String sche_mem_gno, String sche_mem_sno) {
		super();
		this.sche_mem_mno = sche_mem_mno;
		this.sche_mem_gno = sche_mem_gno;
		this.sche_mem_sno = sche_mem_sno;
	}

	@Override
	public String toString() {
		return "ScheduleJoinVO [sche_mem_mno=" + sche_mem_mno + ", sche_mem_gno=" + sche_mem_gno + ", sche_mem_sno="
				+ sche_mem_sno + "]";
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
	
	
}
