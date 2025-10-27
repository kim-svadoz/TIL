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
	 *  1. queue�� ��������� Ʈ���� ���Ը� �ִ´�.
	 *  2. Ʈ���� �ٸ��� �� �������� queue ũ��� �ٸ� ���̿� �����ϹǷ� queue���� ���� �ִ� ���Կ��� ���ش�
	 *  3. queue�� ������� �ʰ�, ���԰� �ʰ��� �� 0�� �ִ´�.
	 *  4. queue�� ������� �ʰ�, ���԰� ����� �� Ʈ���� ���Ը� �ִ´�. 
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
