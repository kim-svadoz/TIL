/**
 * BOJ 3067 Coins
 * DP, 배낭
 */
import java.io.*;
import java.util.*;

public class p3067 {
    static int t, n, m;
    static int[] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken()); // 동전의 각 금액 ex. 1, 5, 10
            }
            m = Integer.parseInt(br.readLine());

            solution();     
        }
    }

    static void solution() {
        dp = new int[10010];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i]; j <= m; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }
        System.out.println(dp[m]);
    }
}
