/**
 * 70점 (효율성 통과X)
 */
import java.util.*;

public class BagleCode_2021H_03 {
    public static void main(String[] args) {
        int[] mmr = {
            33, 56, 93, 31, 18, 10, 41, 93
        };
        System.out.println(solution(mmr));
    }

    static final int IMPOSSIBLE = -1;
    static int totalScore;
    static int len;
    static int answer;
    static int[] arr;

    public static int solution(int[] mmr) {
        init(mmr);
        return pro();
    }

    private static void init(int[] mmr) {
        totalScore = 0;
        answer = IMPOSSIBLE;
        arr = mmr;
        len = arr.length;
        for (int i : arr) {
            totalScore += i;
        }
    }

    private static int pro() {
        recur(0, 0, 0);
        return answer;
    }
    
    private static void recur(int idx, int start, int sum) {
        // base case
        if (idx == len / 2) {
            int cur = totalScore - sum;
            int diff = Math.abs(cur - sum);
            if (answer == IMPOSSIBLE || answer > diff) {
                answer = diff;
            }
            return;
        }
        // process
        for (int i = start; i < len; i++) {
            recur(idx + 1, i + 1, sum + arr[i]);
        }
    }
}
