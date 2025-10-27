// 가능한 씨앗의 배치를 모드 탐색하는 문제이다.
import java.io.*;
import java.util.*;

public class p14620 {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, min = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n + 1][n + 1];
        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int depth, int sum) {
        if (depth == 3) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!isPossible(i, j)) continue;
                
                int tmp = getSum(i, j);
                settingVisit(i, j, true);
                dfs(depth + 1, sum + tmp);
                settingVisit(i, j, false);
            }
        }
    }

    static void settingVisit(int r, int c, boolean type) {
        visit[r][c] = type;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            visit[nr][nc] = type;
        }
    }   

    static int getSum(int r, int c) {
        int sum = map[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            sum += map[nr][nc];
        }
        return sum;
    }

    static boolean isPossible(int r, int c) {
        if (visit[r][c]) return false;

        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (OOB(nr, nc)) return false;;
            if (visit[nr][nc]) return false;
        }

        return true;
    }

    static boolean OOB(int r, int c) {
        return r < 1 || r > n || c < 1 || c > n;
    }
}
