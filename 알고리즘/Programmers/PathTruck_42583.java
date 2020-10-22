package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class PathTruck_42583 {

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7, 4, 5, 6};
		solution(bridge_length, weight, truck_weights);

	}
	/*
	 *  1. queue가 비어있으면 트럭의 무게를 넣는다.
	 *  2. 트럭이 다리를 다 지났으면 queue 크기는 다리 길이와 동일하므로 queue에서 꺼내 최대 무게에서 빼준다
	 *  3. queue가 비어있지 않고, 무게가 초과될 때 0을 넣는다.
	 *  4. queue가 비어있지 않고, 무게가 충분할 때 트럭의 무게를 넣는다. 
	 */
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        
        int max = 0;
        for(int w : truck_weights) {
            while(true) {
                if(q.isEmpty()){
                    q.offer(w);
                    max += w;
                    answer ++;
                    break;
                } else if(q.size() == bridge_length) {
                    max -= q.poll();
                } else {
                    if(max+w > weight){
                        q.offer(0);
                        answer++;
                    } else {
                        q.offer(w);
                        max += w;
                        answer ++;
                        break;
                    }
                }
            }
        }
        return answer + bridge_length;
    }
}
