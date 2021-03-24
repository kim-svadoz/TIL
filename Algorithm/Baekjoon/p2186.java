// dp + dfs
import java.io.*;
import java.util.*;

public class p2186 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k, cnt;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int dp[][][];
    static char map[][];
    static boolean visit[][];
    static String target = "";
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cnt = 0;
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        target = br.readLine();
        dp = new int[n][m][target.length()];

        // -1로 싹 다 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int q = 0; q < target.length(); q++) {
                    dp[i][j][q] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cnt += dfs(i, j, 0);
            }
        }

        System.out.println(cnt);
    }

    // r, c 에서 출발하는데 target i번째 문자로 탐색을 했을 때 가능한 경우의 수 를 메모이제이션한다.
    static int dfs(int r, int c, int index) {
        if (index == target.length() - 1) { // 마지막 문자
            return dp[r][c][index] = 1;
        }
        if (dp[r][c][index] != -1) {    // 이미 존재할 때
            return dp[r][c][index];
        }
        if (map[r][c] != target.charAt(index)) {
            return dp[r][c][index] = 0;
        }

        dp[r][c][index] = 0;

        // 사방으로 move 만큼 다음문자 탐색 -> 있으면 dp에 이전 값 만큼 더해준다.
        for (int d = 0; d < 4; d++) {
            for (int move = 1; move <= k; move++) {
                int nr = r + dr[d] * move;
                int nc = c + dc[d] * move;

                if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] != target.charAt(index + 1)) continue;

                dp[r][c][index] += dfs(nr, nc, index + 1);
            }
        }

        return dp[r][c][index];
    }
}

