public class LC55_JumpGame1 {
    class Solution {
        public boolean canJump(int[] nums) {
            int len = nums.length;
            
            int max = 0;
            for (int i = 0; i < len; i++) {
                if (i > max) return false;
                max = Math.max(max, i + nums[i]);
            }
            return true;
        }
    }
}
