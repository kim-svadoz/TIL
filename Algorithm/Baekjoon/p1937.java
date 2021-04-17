/*
    dp + dfs
    메모이젼을 잘 활용하자.
*/
import java.io.*;
import java.util.*;

public class p1937 {
    static int n, max = 0;
    static int[][] map, dp;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        
        dp = new int[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 매 탐색마다 dp값을 초기화 했어서 틀렸는데, 그러지 않고 dp 테이블은 계속 유지한채로 탐색해야 한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(dfs(i, j), max);
            }
        }
        System.out.println(max);
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        dp[r][c] = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (OOB(nr, nc)) continue;
            if (map[r][c] >= map[nr][nc]) continue;

            dp[r][c] = Math.max(dfs(nr, nc) + 1, dp[r][c]);
        }

        return dp[r][c];
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= n;
    }
}
