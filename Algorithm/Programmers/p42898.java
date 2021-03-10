public class p42898 {
    final static int MOD = 1000000007;
    static int[][] dp;
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {
            {2, 2}
        };
        solution(m, n, puddles);
    }

    public static int solution(int m, int n, int[][] puddles) {
        dp = new int[n + 1][m + 1];

        dp[0][0] = 0;
        dp[1][1] = 1;

        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1]][puddles[i][0]] = -1;
            
        }
    
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(dp[i][j] == - 1) continue;
                if(i - 1 > 0 && dp[i - 1][j] != -1) {
                    dp[i][j] += dp[i - 1][j] % MOD;
                }
                if(j - 1 > 0 && dp[i][j - 1] != -1) {
                    dp[i][j] += dp[i][j - 1] % MOD;
                }
            }
        }
        
        return dp[n][m] % MOD;
    }
}
