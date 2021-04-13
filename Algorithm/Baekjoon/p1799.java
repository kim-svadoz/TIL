import java.io.*;
import java.util.*;

public class p1799 {
    static int n, answer, maxNum, map[][];
    static boolean[][] visit, colors;
    static int[] res = new int[2]; // black , white
    static int dr[] = {-1, -1};
    static int dc[] = {1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visit = new boolean[n][n];
        colors = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 체스판 처럼 color check
                colors[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0);
            }
        }
        
        // 처음 시작에 비숍을 안 놓는 경우도 체크하기 위해 -1로 시작
        dfs(-1, true, 0);
        dfs(-1, false, 0);
        System.out.println(res[0]+res[1]);
    }
    static void dfs(int index, boolean black, int depth) {
        for (int i = index + 1; i < n * n; i++) {
            int r = i / n;
            int c = i % n;

            if (colors[r][c] != black || map[r][c] == 0 || !check(r, c)) continue;


            visit[r][c] = true;
            dfs(i, black, depth + 1);
            visit[r][c] = false;
        }
        res[black ? 1 : 0] = Math.max(res[black ? 1 : 0], depth);
    }

    static boolean check(int r, int c) {
        // 임의의 순서를 지정해 0, 0부터 시작하므로 아래부분은 비숍이 없다.
        // 따라서 윗 대각선 2개만 체크하면 된다.
        for (int i = 0; i < 2; i++) {
            int nr = r;
            int nc = c;
            while (true) {
                if (OOB(nr, nc)) break;
                if (visit[nr][nc]) return false;

                nr += dr[i];
                nc += dc[i];
            }
        }
        return true;
    }

    static boolean OOB(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}
