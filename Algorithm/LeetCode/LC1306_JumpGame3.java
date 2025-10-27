import java.util.*;
public class LC1306_JumpGame3 {
    class Solution {
        private int len, start;
        public boolean canReach(int[] arr, int start) {
            this.start = start;
            len = arr.length;
            
            return solution(arr, start);
        }
        private boolean solution(int[] arr, int idx) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[len];
            q.add(idx);
            visited[idx] = true;
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (arr[cur] == 0) {
                    return true;
                }
                
                int left = cur - arr[cur];
                if (!OOB(left) && !visited[left]) {
                    visited[left] = true;
                    q.add(left);
                }
                
                int right = cur + arr[cur];
                if (!OOB(right) && !visited[right]) {
                    visited[right] = true;
                    q.add(right);
                }
            }
            
            return false;
        }
        private boolean OOB(int n) {
            return n < 0 || n >= len;
        }
    }
}
