import java.io.*;
import java.util.*;
public class p9465 {
    static int n;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            int[][] stick = new int[2][t+1];
            dp = new int[2][t+1];

            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= t; k++) {
                    stick[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = stick[0][1];
            dp[1][1] = stick[1][1];
            for (int j = 2; j <= t; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + stick[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + stick[1][j];
            }
            System.out.println(Math.max(dp[0][t], dp[1][t]));
        }
    }
}