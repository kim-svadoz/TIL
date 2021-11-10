/**
 * Codility Lessons3 Easy
 * 누적합, 구현
 */
public class L3_Easy_TapeEquilibrium {
    class Solution {
        public int solution(int[] A) {
            int len = A.length;
            int[] psum = new int[len + 1];
            int sum = 0;
            for (int i = 1; i <= len; i++) {
                psum[i] += psum[i - 1] + A[i - 1];
            }
            sum = psum[len];
            int min = 100100;
            for (int i = 1; i < len; i++) {
                int diff = Math.abs(psum[i] - (sum - psum[i]));
                min = Math.min(min, diff);
            }

            return min;
        }
    }
}
