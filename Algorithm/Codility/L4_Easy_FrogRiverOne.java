/**
 * Codility Lesson4 Easy
 * 자료구조, 구현
 */
public class L4_Easy_FrogRiverOne {
    class Solution {
        public int solution(int X, int[] A) {
            if (A.length < X) return -1;
    
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= X; i++) {
                set.add(i);
            }
            
            for (int i = 0; i < A.length; i++) {
                if (set.contains(A[i])) {
                    set.remove(A[i]);
                }
    
                if (set.size() == 0) return i;
            }
    
            return -1;
        }
    }
}
