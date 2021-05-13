/*
	동물원
	DP
*/
import java.io.*;
import java.util.*;
public class p1309 {
    static final int MOD = 9901;
    static int n;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n][3];
        
        dp[0][0] = 1; // 0번째 순서에 아무 사자도 안 넣는 경우의 수
        dp[0][1] = 1; // 0번째 순서에 왼쪽우리에 사자를 넣는 경우의 수
        dp[0][2] = 1; // 0번째 순서에 오른쪽우리에 사자를 넣는 경우의 수
        
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
            dp[i][1] = dp[i-1][0] + dp[i-1][2];
            dp[i][2] = dp[i-1][0] + dp[i-1][1];
            dp[i][0] %= MOD;
            dp[i][1] %= MOD;
            dp[i][2] %= MOD;
        }
        
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += dp[n-1][i];
        }
        System.out.println(answer % MOD);
    }
}