public class LC5_LongestPalindromicSubstring {
    public class Solution {
        private int lo, hi;
    
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len < 2)
                return s;
    
            for (int i = 0; i < len-1; i++) {
                extendPalindrome(s, i, i);  // odd case
                extendPalindrome(s, i, i+1); // even case
            }
            return s.substring(lo, lo + hi);
        }
    
        private void extendPalindrome(String s, int j, int k) {
            while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                j--;
                k++;
            }
            // setting lo & maxLen
            if (hi < k - j - 1) {
                lo = j + 1;
                hi = k - j - 1;
            }
        }
    }
}
