/**
 * BOJ 9461 파도반 수열 : Silver 3
 * DP
 * 
 * 규칙찾는데 너무 오래걸렸다.... 그림으로만 이해하려니 오래 걸린듯하다.
 */
import java.io.*;

public class p9461 {
    static int t, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
       
        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            System.out.println(dp[n]);
        }
    }
}
