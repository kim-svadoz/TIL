package programmers;

import java.util.*;

public class top_42588 {
	public static void main(String[] args) {
		top_42588 m = new top_42588();
		
		int[] h = {6, 9, 5, 7, 4};
		//(output) {0, 0, 2, 2, 4}
		//int[] h = {3, 9, 9, 3, 5, 7, 2};
		//(output) {0, 0, 0, 3, 3, 3, 6}
		//int[] h = {1, 5, 3, 6, 7, 6, 5};
		//(output) {0, 0, 2, 0, 0, 5, 6}
		m.solution(h);
	}
	
	public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];
        
        for(int i=heights.length-1;i>=0;i--) {
        	for(int j=i-1;j>=0;j--) {
	        	if(heights[i] < heights[j]) {
	        		answer[i] = j + 1;
	        		break;
	        	}
        	}
        }
        
        for(int i=0;i<answer.length;i++)
        	System.out.print(answer[i] + " ");
        System.out.println("");
        
        return answer;
    }
}