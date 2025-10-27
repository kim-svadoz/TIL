/*
    bfs로 풀면 메모리초과, dfs로 풀면 시간초과가 발생한다.

    솔루션은 ? -> dfs + dp !!
    메모이제이션을 이용한 그래프 탐색
    해당 유형 정확하게 학습해두자.
*/
import java.io.*;
import java.util.*;

public class p1520 {
    static int m, n = 0;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        dp = new int[m][n];
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dfs(0, 0);
        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        // 마지막 블록이라면 + 1
        if (r == m - 1 && c == n - 1) {
            return 1;
        }

        // 이미 방문했었다면 return;
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        // 방문한 블럭은 0으로 초기화
        dp[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (OOB(nr, nc)) continue;
            if (map[r][c] <= map[nr][nc]) continue;

            dp[r][c] += dfs(nr, nc);
        }

        return dp[r][c];
    }
    
    static boolean OOB(int r, int c) {
        return r < 0 || r >= m || c < 0 || c >= n;
    }
}
