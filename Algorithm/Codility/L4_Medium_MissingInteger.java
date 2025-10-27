/**
 * Codility Lesson4 Medium
 * 자료구조, 구현
 */
public class L4_Medium_MissingInteger {
    class Solution {
        public int solution(int[] A) {
            Set<Integer> set = new HashSet<>();
            for (int i : A) {
                set.add(i);
            }
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                if (!set.contains(i)) {
                    return i;
                }
            }
            return -1;
        }
    }
}