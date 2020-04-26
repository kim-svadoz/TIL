package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Budget_12982 {

	public static void main(String[] args) {
		int[] d = { 2, 2, 3, 3 };
		int budget = 10;
		solution(d, budget);
	}

	public static int solution(int[] d, int budget) {
		int answer = 0;
		int length = d.length;
		int cnt = 0;
		Arrays.sort(d);
		
		for(int i=0; i<length; i++) {
			cnt += d[i]; // 0 + 2
			if(cnt > budget) break;
			answer = i+1;
		}

		return answer;
	}


}
