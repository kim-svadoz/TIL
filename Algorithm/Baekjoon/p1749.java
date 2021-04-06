import java.io.*;
import java.util.*;

public class p1749 {
    static int n, m, board[][], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + board[i][j];
            }
        }

        // 모든 경우를 다 체크하면서 돌아도 범위가 여유롭기 때문에 시간초과가 안난다 ! -> 브루트포스
        long max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int x = i; x <= n; x++) {
                    for (int y = j; y <= m; y++) {
                        long result = prefixSum(i, j, x, y, dp);
                        max = Math.max(max, result);
                    }
                }
            }
        }

        System.out.println(max);
    }
    static long prefixSum(int x1, int y1, int x2, int y2, int[][] psum) {
        return psum[x2][y2] - psum[x1 - 1][y2] - psum[x2][y1 - 1] + psum[x1 - 1][y1 - 1];
    }
}
