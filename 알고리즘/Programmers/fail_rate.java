package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class fail_rate {
	public static void main(String[] args) {
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3 };
		
		solution(5, stages);
	}
	
	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N+1];
		int count; // 계산
		int count2;
		double mom[] = new double[N+1]; // 분모
		double child[] = new double[N+1]; //분자
		double fr[] = new double[N+1] ; // 실패율
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		for(int i=0; i<N+1;i++) {
			count = 0;
			count2 = 0;
			for(int j=0; j<stages.length;j++) {
				if(i < stages[j] && stages[j] < i+2) {
					count ++;
				}
				if(stages[j] > i) {
					count2 ++;
				}
			}
			
			child[i] = count;
			mom[i] = count2;
			fr[i] = child[i] / mom[i];
			res.put(i+1, fr[i]);
		}
	
		List<Integer> valueList = new ArrayList<>(res.keySet()); //실패율 내림차순
		Collections.sort(valueList, (o1, o2) ->(res.get(o2).compareTo(res.get(o1))));
		
		for(int i=0; i<valueList.size()-1;i++) {
			answer[i] = valueList.get(i);
			System.out.print(answer[i] + " ");
		}
		return answer;
	}
	
}

