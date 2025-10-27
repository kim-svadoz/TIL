/*
    가장 큰 정사각형
    DP

*/
import java.io.*;
import java.util.*;
public class p1915 {
    static int n, m;
    static long max = 0;
    static int[][] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                int temp = line.charAt(j-1)-'0';
                // 좌표가 1인 정사각형만 모아야 하므로 0일 때는 구간합을 수행하지 않는다.
                if (temp == 1) {
                    dp[i][j] = Math.min(dp[i-1][j-1] , Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max * max);

    }
}