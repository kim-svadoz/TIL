/**
 * Codility Lesson3 Easy
 * 구현
 */
public class L3_Easy_FrogJump {
    class Solution {
        public int solution(int X, int Y, int D) {
            int val = Y - X;
            int answer = val / D;
            return val % D == 0 ? answer : answer + 1;
        }
    }
}
