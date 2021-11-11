import java.util.Arrays;

public class LC45_JumpGame2 {
    class Solution {
        public int jump(int[] nums) {
            int len = nums.length;
            int[] dp = new int[len];
            // dp[i] : i까지 가는데 걸리는 최소 횟수
            
            Arrays.fill(dp, 987654321);
            
            dp[0] = 0;
            for (int i = 0; i < len - 1; i++) {
                for (int j = i; j <= i + nums[i]; j++) {
                    if (j >= len) continue;
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
            }
            return dp[len - 1];
        }
    
    }
}
