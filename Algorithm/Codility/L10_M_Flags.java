/**
 * Codility Lesson 10 Medium Flags
 * 이분탐색
 */
import java.util.*;
public class L10_M_Flags {
    class Solution {
        int len;
        List<Integer> list;
        public int solution(int[] A) {
            // write your code in Java SE 8
            len = A.length;
            list = new ArrayList<>();

            for (int i = 1; i < len - 1; i++) {
                if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                    list.add(i);
                    i++;
                }
            }

            int lo = 0;
            int hi = list.size();
            if (hi < 2) return hi;

            int max = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (check(mid) == mid) {
                    lo = mid + 1;
                    max = mid;
                } else {
                    hi = mid - 1;
                }
            }

            return max;
        }

        private int check(int num) {
            int prev = list.get(0);
            int cnt = 1;
            for (int i = 1; i < list.size() && cnt < num; i++) {
                if (list.get(i) - prev >= num) {
                    cnt++;
                    prev = list.get(i);
                }
            }

            return cnt;
        }
    }
}
