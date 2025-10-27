/**
 * BOJ 1757 달려달려 : Gold 4
 * DP
 */
import java.io.*;
import java.util.*;

public class p1757 {
    static int n, m;
    static int[] time;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 한계 지침지수

        time = new int[n + 1];
        for (int i = 0; i < n; i++) {
            time[i + 1] = Integer.parseInt(br.readLine());
        }
        // dp[idx][지침지수] : 갈 수 있는 최대 거리
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            solution(i, time[i]);
        }
        System.out.println(dp[n][0]);
    }

    /**
     * idx번째에서의 거리는 사실상 idx-1번째의 상태에 의해서 결정된다.
     */
    static void solution(int idx, int move) {
        // idx번째에 쉬었을 경우
        dp[idx][0] = dp[idx - 1][0];

        // idx번째에 달렸을 경우
        for (int exh = 1; exh <= m; exh++) {
            dp[idx][exh] = dp[idx - 1][exh - 1] + move;
        }

        // 지침지수가 0으로 끝나는 경우의 최댓값을 저장
        for (int exh = 1; exh <= m && idx > exh; exh++) {
            // dp[idx-1][1] : 지침지수가 1로, 한 턴 쉰 애들
            // dp[idx-2][2] : 지침지수가 2로, 두 턴 쉰 애들
            dp[idx][0] = Math.max(dp[idx][0], dp[idx - exh][exh]);
        }
    }
}
