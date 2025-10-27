/**
 * Leetcode 3 Medium
 * 문자열, 구현
 */
import java.util.*;
public class LC3_LongestSubstringWithoutRepeatingCharacters {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int len = s.length();
            if (len == 0) return 0;
    
            Set<Character> set = new HashSet<>();
            int lo = 0, hi = 0;
            int answer = 0;
            while (hi < len) {
                if (set.contains(s.charAt(hi))) {
                    set.remove(s.charAt(lo++));
                } else {
                    set.add(s.charAt(hi++));
                    answer = Math.max(answer, set.size());
                }
            }
            return answer;
        }
    }
}
