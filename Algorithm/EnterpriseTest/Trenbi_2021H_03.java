/**
 * 배열의 길이는 1이상 10만이하
 * 원소의 범위는 1이상 10억 이하
 * Nlog(N)으로도 충분할것같다.
 */
import java.util.*;

public class Trenbi_2021H_03 {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 5, 6, 2, 1, 6};
        System.out.println(solution(arr));
    }

    public static final int IMPOSSIBLE = 100100;
    // 누가 당첨됐는지가 아니라, 얼마나 빨리 중복 당첨 됐는지를 판별하는 것
    public static int solution(int[] arr) {
        int min = IMPOSSIBLE;
        HashMap<Integer, Integer> map = new HashMap<>();

        int time = 0;
        for (int k : arr) {
            time++;
            if (map.containsKey(k)) {
                min = Math.min(min, time - map.get(k));
            }

            map.put(k, time);
        }

        return min == IMPOSSIBLE ? -1 : min;
    }
}
