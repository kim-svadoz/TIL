/**
 * N은 1이상 10억이하 자연수
 */
import java.util.*;

public class Trenbi_2021H_01 {
    public static void main(String[] args) {
        int N = 2613;
        solution(N);
    }

    public static int solution(int N) {
        String s = N+"";
        int len = s.length();

        int[] cnt = new int[10]; // 들어갈 위치

        for (int i = 0; i < len; i++) {
            int num = Integer.parseInt(s.substring(i, i+1));
            cnt[num]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) { // 내림차순
            int n = cnt[i]; // n개의 i가 존재한다.
            
            while (n-- > 0) {
                sb.append(i);
            }
        }
        int answer = Integer.parseInt(sb.toString()) + Integer.parseInt(sb.reverse().toString());
        System.out.println(answer);
        return answer;
    }
}
