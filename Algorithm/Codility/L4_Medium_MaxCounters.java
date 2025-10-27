/**
 * Codility Lesson4 Medium
 * 시간복잡도, Counting
 */
public class L4_Medium_MaxCounters {
    class Solution {
        public int[] solution(int N, int[] A) {
            int[] ans = new int[N];
    
            int max = 0;
            int tmpMax = 0;
            for (int i : A) {
                if (i > N) {
                    max = tmpMax;
                    continue;
                }
    
                if (ans[i - 1] < max) {
                    ans[i - 1] = max + 1;
                } else {
                    ans[i - 1]++;
                }
    
                if (tmpMax < ans[i - 1]) {
                    tmpMax = ans[i - 1];
                }
            }
    
            for (int i = 0; i < ans.length; i++) {
                if (ans[i] < max) {
                    ans[i] = max;
                }
            }
    
            return ans;
        }
    }
}
