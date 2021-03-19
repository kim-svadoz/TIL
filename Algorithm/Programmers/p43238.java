import java.util.*;

public class p43238 {
    static int tick[];
    static boolean check[];
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        solution(n, times);
    }

    static public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        long left = 0;
        long right = 0;
        long mid;

        for (int time : times) {
            if (time > right) {
                right = time;
            }
        }

        right = right * n;

        while (right >= left) {
            long done = 0;
            mid = (left + right) / 2;

            for (int time : times) {
                done += mid / time;
            }

            // 해당 시간 동안 심사를 못한 경우
            if (done < n) {
                left = mid + 1;
            } else { // 시간이 여유 있거나 딱 맞는 경우
                if (mid < answer) {
                    answer = mid;
                }
                right = mid - 1;
            }
        }

        return answer;
    }
}
