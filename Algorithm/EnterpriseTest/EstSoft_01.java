package Exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class EstSoft_01 {
	public static void main(String[] args) {
		int[] A = {3, 1, 4, 1, 5};
		int[] B = {3, 8, 2, 3, 3, 2};
		solution(B);
	}
	
	public static int solution(int[] A) {
		int cnt;
		ArrayList<Integer> cntList = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		Arrays.sort(A);
		for(int i=0; i<A.length; i++) {
			cnt =1;
			
			for(int j=i+1; j<A.length;j++) {
				if(A[i]==A[j]) {
					cnt++;
					A[j] = -1;
					map.put(cnt, A[i]);
				}
			}
			cntList.add(cnt);
		}
		Collections.sort(cntList);
		System.out.println(cntList); // 1 1 1 1 2
		
		System.out.println(cntList.get(cntList.size()-1));
		System.out.println("key를 통해 map의 value를 받아오기 => "+map.get(2));
		
		System.out.println("map+==="+map.get(cntList.get(cntList.size()-1)));
		
		
		if(cntList.get(cntList.size()-1) == map.get(cntList.get(cntList.size()-1))) {
			System.out.println("return yes");
			return cntList.get(cntList.size()-1);
		}else {
			System.out.println("return no");
			return 0;
		}
		
	}
}
