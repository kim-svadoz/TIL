import java.io.*;
import java.util.*;
public class TMAX_2021H_02 {
    public static void main(String[] args) throws IOException {
        int r = 2;
        int c = 4;
        solution(r, c);
    }
    static int m, n;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static long[][] dp;
    static public long solution(int r, int c) {
        m = r;
        n = c;
        long answer = 0;
        dp = new long[r + 1][c + 1];
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = -1;
            }
        }

        answer = dfs(1, 1);
        System.out.println(answer);
        return answer;
    }

    static long dfs(int r, int c) {
        if (r == m && c == n) {
            return 1;
        }
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        dp[r][c] = 0;

        for (int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (OOB(nr, nc)) continue;

            dp[r][c] += dfs(nr, nc);
        }
        return dp[r][c];
    }

    static boolean OOB(int r, int c) {
        return r <= 0 || c <= 0 || r > m || c > n;
    }
}
