import java.io.*;
import java.util.*;

public class p15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        // 배열의 크기는 넉넉히 할당
        int[] t = new int[n + 10];
        int[] p = new int[n + 10];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 10];
        // dp[i] : i일까지 일했을 때의 최대 이익
        int max = 0;
        for (int i = 1; i <= n + 1; i++) {
            dp[i] = Math.max(dp[i], max);

            // 해당 주석이 없으니까 마지막 99퍼에서 실패됨.
            if (i+t[i] <= n + 1) {
                dp[i+t[i]] = Math.max(dp[i+t[i]], p[i] + dp[i]);
            }

            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
