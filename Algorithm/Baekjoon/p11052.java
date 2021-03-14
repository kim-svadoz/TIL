import java.io.*;
import java.util.*;

public class p11052 {
    static int N, cost[];
    static int dp[];
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws  IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(recur(N));
    }

    static int recur(int n) {
        if (n == 1) return cost[n];
        // memo해놓은 값이 있다면 그대로 사용

        if (dp[n] == 0) {
            for (int i = 1; i <= n; i++) {
                dp[n] = Math.max(recur(n - i) + cost[i], dp[n]);
            }
        }
        return dp[n];
    }
}