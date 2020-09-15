package programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class Printer_42587 {

	public static void main(String[] args) {

	}

	public static int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int num : priorities){
            pq.add(num);
        }
		
        while(!pq.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(pq.peek() == priorities[i]){
                    pq.poll();
                    answer++;
                    if(location==i){
                        pq.clear();
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}
