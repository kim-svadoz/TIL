/**
 * 70점 (효율성 통과X)
 */
import java.util.*;

public class BagleCode_2021H_03_bit {
    public static void main(String[] args) {
        int[] mmr = {
            33, 56, 93, 31, 18, 10, 41, 93
        };
        System.out.println(solution(mmr));
    }

    static final int IMPOSSIBLE = -1;
    static int n; // 총 사람 수 max. 200
    static int answer;
    static List<Integer> first;
    static List<Integer> second;

    public static int solution(int[] mmr) {
        init(mmr);
        return pro(mmr);
    }

    private static void init(int[] mmr) {
        answer = IMPOSSIBLE;
        n = mmr.length;
    }

    private static int pro(int[] arr) {
        for (long i = 1; i < (1 << n); i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    cnt++;
                }
            }

            if (cnt != n / 2) continue;

            first = new ArrayList<>();
            second = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    first.add(j);
                } else {
                    second.add(j);
                }
            }

            int firstScore = 0;
            int secondScore = 0;

            for (int score : first) {
                firstScore += arr[score];
            }
            for (int score : second) {
                secondScore += arr[score];
            }

            int diff = Math.abs(firstScore - secondScore);

            if (answer == IMPOSSIBLE || answer > diff) {
                answer = diff;
            }
        }
        return answer;
    }

}
