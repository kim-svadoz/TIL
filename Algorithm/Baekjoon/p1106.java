import java.io.*;
import java.util.*;

public class p1106 {
    static final int INF = 987654321;
    static int c, n;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken()); // 최소 고객
        n = Integer.parseInt(st.nextToken()); // 도시 개수
        
        dp = new int[c + 101]; // 달성 고객 + 최대 도시 고객
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken()); // 비용
            int customer = Integer.parseInt(st.nextToken()); // 고객
            for (int j = customer; j < c + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - customer]);
            }
        }
        
        int ans = INF;
        for (int i = c; i < c + 101; i++) {
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
}