package Exam;

public class DevMatch_01 {

	//리팩토링하기.
	public static void main(String[] args) {
		String P = "82195";
		String S = "64723";
		System.out.println(solution(P,S));
	}
	
	public static int solution(String P, String S) {
		int answer =0 ;
		int[] nP = new int[P.length()];
		int[] nS = new int[S.length()];
		
		for(int i=0; i<P.length(); i++) {
			nP[i] = Integer.parseInt(P.split("")[i]);
			nS[i] = Integer.parseInt(S.split("")[i]);
		}
		
		int dif=0;
		for(int i=0; i<P.length(); i++) {
			
			if(Math.abs(nP[i]-nS[i]) > 5) {
				dif = Math.min(Math.abs((nP[i]+10)-nS[i]), Math.abs((nS[i]+10)-nP[i]));
			}else {
				dif = Math.min(Math.abs(nP[i]-nS[i]), Math.abs(nS[i]-nP[i]));
			}
			if(dif == 9) {
				dif = 1;
			}
			answer += dif;
		}
		
		return answer;
	}

}
