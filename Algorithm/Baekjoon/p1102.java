/*
	발전소
    DP + DFS + 비트마스킹

	총 N개의 발전소
	dp[n][bit]로 bit는 최대 16개의 발전소가 있으므로 2^16으로 표현가능
*/
import java.io.*;
import java.util.*;
public class p1102 {
    static final int INF = 987654321;
    static int n, k;
    static int[][] dp, cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dp = new int[n + 1][1 << 16];
        cost = new int[n][n];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 고장나지 않은 발전소 리스트
        char[] chars = br.readLine().toCharArray();
        // 적어도 k개의 발전소가 살아있어야 한다.
        k = Integer.parseInt(br.readLine());
        
        int bit = 0;
        int cnt = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'Y') {
                // Y인 비트를 킨다.
                bit |= (1 << i);
                cnt++;
            }
        }
        int ans = solve(cnt, bit);
        System.out.println(ans == INF ? -1 : ans);
    }
    
    static int solve(int cnt, int bit) {
        if (cnt >= k) {
            return 0;
        }
        
        if (dp[cnt][bit] >= 0) {
            return dp[cnt][bit];
        }
        // 최소값을 구하기 위해 초기값을 최대로 셋팅
        dp[cnt][bit] = INF;
        for (int i = 0; i < n; i++) {
            // i의 비트가 켜져있을 때
            if ((bit & (1 << i)) != 0) {
                for (int j = 0; j < n; j++) {
                    // i,j가 같거나, j도 켜져있으면 스킵
                    if (i == j || (bit & (1 << j)) != 0) {
                        continue;
                    }
                    
                    dp[cnt][bit] = Math.min(dp[cnt][bit], solve(cnt + 1, bit | (1 << j)) + cost[i][j]);
                }
            }
        }
        
        return dp[cnt][bit];
    }
}