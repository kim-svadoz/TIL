import java.io.*;
import java.util.*;

public class p1987 {
    static int r, c, max;
    static char board[][];
    static boolean visit[];
    static int dr[] = {1, 0, -1, 0};
    static int dc[] = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        visit = new boolean[26];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        max = 0;
        dfs(0, 0, 1);
        System.out.println(max);
    }

    static void dfs(int x, int y, int cnt) {
        visit[board[x][y]-'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nr = x + dr[i];
            int nc = y + dc[i];
            int ncnt = cnt + 1;

            if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
            if (visit[board[nr][nc]-'A']) {
                max = Math.max(max, cnt);
                continue;
            }

            visit[board[nr][nc]-'A'] = true;
            dfs(nr, nc , ncnt);
            visit[board[nr][nc]-'A'] = false;
        }
    }
}
