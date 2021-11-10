/**
 * Codility Lesson4 Easy
 * 구현
 */
public class L4_Easy_PermCheck {
    class Solution {
        public int solution(int[] A) {
            Arrays.sort(A);
    
            for (int i = 1; i <= A.length; i++) {
                if (A[i - 1] != i) return 0;
            }
    
            return 1;
        }
    }
}
