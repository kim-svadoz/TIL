package Line;

import java.util.*;

public class line03 {

	public static void main(String[] args) {
		String road = "001100";
		int n = 5;
		solution(road, n);

	}
	public static int solution(String road, int n) {
		int answer=-1;
		int answer2=-1;
		int cnt=0;
		int poss=0;
		int cnt2, poss2;
		String[] input = road.split("");
		List<Integer> zeroCheck = new ArrayList<>();
		for(int i=0; i<input.length; i++) {
			if(input[i].equals("0")) {
				zeroCheck.add(i);
			}
		}
		
		for(int i=0; i<input.length; i++) {
			if(poss==n) break;
			if(input[i].equals("0")) {
				poss++;
			}else {
				cnt++;
			}
			answer = poss+cnt;
		}
		
		for(int i=0; i<zeroCheck.size(); i++) {
			if(zeroCheck.size()==0) return answer;
			
			poss2=0;
			cnt2=0;
			int cur = zeroCheck.get(0);
			for(int j=cur+1; j<input.length; j++) {
				if(input[j].equals("0")) {
					poss2++;
				}else {
					cnt2++;
				}
				if(poss2==n+1) break;
				answer2 = poss2+cnt2;
			}
			if(answer2 > answer) answer = answer2;
		}
		
		return answer;
	}
	

}
