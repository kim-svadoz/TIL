package Exam;

import java.util.ArrayList;
import java.util.Collections;

public class EstSoft_02 {
	public static void main(String[] args) {
		int[] A = {51, 32, 43};
		solution(A);
	}
	
	public static int solution(int[] A) {
		int sum;
		int[] sumList = new int[A.length];
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i=0; i<A.length;i++) {
			sum = 0;
			String tmp = Integer.toString(A[i]);
			int my=0;
			int[] list = new int[tmp.length()];
			
			for(int j=0; j<tmp.length();j++) {
				list[j] = tmp.charAt(j)-'0';
				sum += list[j];
			}
			sumList[i] = sum;
		}
		
		for(int i=0; i<sumList.length; i++) {
			for(int j=i+1; j<sumList.length; j++) {
				if(sumList[i]==sumList[j] && sumList!=null) {
					result.add(A[i]+A[j]);
				}
			}
		}
		if(result.size()==0) {
			return -1;
		}
		
		
		Collections.sort(result);
		System.out.println(result.get(result.size()-1));
	
		return result.get(result.size()-1);
	}
}
