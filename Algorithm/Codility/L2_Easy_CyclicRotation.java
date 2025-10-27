/**
 * Codility Lesson2 Easy
 * 자료구조, 구현
 */
public class L2_Easy_CyclicRotation {
    class Solution {
        public int[] solution(int[] A, int K) {
            // write your code in Java SE 8
            if (A.length == 0) return new int[0];
            
            Queue<Integer> q = new LinkedList<>();
            for (int i = A.length - 1; i >= 0; i--) {
                q.add(A[i]);
            }

            while (K-- > 0) {
                q.add(q.poll());
            }

            int[] tmp = new int[A.length];
            
            int idx = A.length - 1;
            while (!q.isEmpty()) {
                tmp[idx--] = q.poll();
            }

            return tmp;
        }
    }

}
