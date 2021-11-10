/**
 * Codility Lesson2 Easy
 * 구현
 */
public class L2_Easy_OddOccurrencesInArray {
    class Solution {
        public int solution(int[] A) {
            // write your code in Java SE 8
            Set<Integer> set = new HashSet<>();

            for (int i : A) {
                if (set.contains(i)) {
                    set.remove(i);
                } else {
                    set.add(i);
                }
            }
            
            return set.iterator().next();
        }
    }
}
