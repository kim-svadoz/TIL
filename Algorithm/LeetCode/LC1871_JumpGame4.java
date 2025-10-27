public class LC1871_JumpGame4 {
    class Solution {
        public boolean canReach(String s, int minJump, int maxJump) {
            int len = s.length(), pre = 0;
            boolean[] dp = new boolean[len];
            dp[0] = true;
            for (int i = 1; i < len; i++) {
                if (i >= minJump && dp[i - minJump]) {
                    pre++;
                }
                if (i > maxJump && dp[i - maxJump - 1]) {
                    pre--;
                }
                dp[i] = pre > 0 && s.charAt(i) == '0';
            }
            return dp[len - 1];
        }
    }
}
