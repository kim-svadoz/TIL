/**
 * Codility Lesson3 Easy
 * 구현
 */
public class L3_Easy_PermMissingElem {
    class Solution {
        public int solution(int[] A) {
            if (A.length == 0) return 1;

            Arrays.sort(A);

            for (int i = 0; i < A.length; i++) {
                if (A[i] != i + 1) {
                    return i + 1;
                }
            }

            return A[A.length - 1] + 1;
        }
    }
}
