/*
    DP문제이나 감을 못잡겟다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14501 {
    static int n, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        // 마지막 + 5일과 같을 때 배열 에러가 날 수 있으므로 넉넉히 할당한다.
        int[] t = new int[n + 10];
        int[] p = new int[n + 10];
        int[] dp = new int[n + 10];
        max = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n + 1; i++) {
            // 이전까지의 최대 수입을 비교해서 최대 수입을 현재에 저장
            dp[i] = Math.max(dp[i], max);

            // 이전에 저장된 최대수익 vs 이번에 움직임으로 생긴 최대 수익
            dp[t[i] + i] = Math.max(dp[t[i] + i], p[i] + dp[i]);

            // 출력될 최대 수입
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

}
