/*
	출근경로
	시간복잡도 : O(wh)
	기본적인 격자문제이므로 dp로 접근 시도
*/
import java.io.*;
import java.util.*;
public class p5569 {
    static final int MOD = 100000;
    static int w, h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        // dp = int[110][110][t][chk]
        // t : 현재 진행방향 (0 -> 동쪽, 1 -> 북쪽)
        // chk : 방향 변경 가능 여부 (0 -> 변경 불가능, 1 -> 변경 가능)
        // 이전경로에서 변경 불가능(chk 0)했다면 현재경로 변경 가능(chk 1), 불 가능(chk 0) 모두 허용된다.
        // 이전경로에서 변경 가능(chk 1)했다면 현재 경로에서는 변경 불가능(chk 0) 이어야 한다.
        int[][][][] dp = new int[110][110][2][2];
        
        for (int i = 2; i <= w; i++) {
            // 세로줄은 아래에서만 올 수 있고, 꺾이지 않았다.
            dp[i][1][0][0] = 1;
        }
        
        for (int i = 2; i <= h; i++) {
            // 가로줄은 왼쪽에서만 올 수 있고, 꺾이지 않았다.
            dp[1][i][1][0] = 1;
        }
        
        for (int i = 2; i <= w; i++) {
            for (int j = 2; j <= h; j++) {
                dp[i][j][0][0] = (dp[i-1][j][0][0] + dp[i-1][j][0][1]) % MOD;
                dp[i][j][1][0] = (dp[i][j-1][1][0] + dp[i][j-1][1][1]) % MOD;
                dp[i][j][0][1] = dp[i-1][j][1][0];
                dp[i][j][1][1] = dp[i][j-1][0][0];
            }
        }
        
        int ans = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ans += dp[w][h][i][j];
            }
        }
        System.out.println(ans % MOD);
        
    }
}