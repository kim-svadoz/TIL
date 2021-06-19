import java.util.*;
public class Easy_BinaryGap {   
// you can also use imports, for example:
// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
    class Solution {
        public int solution(int N) {
            // write your code in Java SE 8
            int answer = 0;
            String bs = Integer.toBinaryString(N);
            int num = 0;
            for (char c : bs.toCharArray()) {
                if (c == '1') {
                    answer = Math.max(answer, num);
                    num = 0;
                } else {
                    num++;
                }
            }

            return answer;
        }
    }
}
