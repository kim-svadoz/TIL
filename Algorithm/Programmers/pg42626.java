import java.util.*;

public class pg42626 {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 2, 3, 9, 10, 12}, 7));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i : scoville) {
            pq.offer(i);
        }

        while (pq.peek() <= K) {
            if (pq.size() == 1) {
                return -1;
            }
            int a = pq.poll();
            int b = pq.poll();
            int result = a + (2 * b);

            pq.offer(result);
            answer++;
        }

        return answer;
    }
}
