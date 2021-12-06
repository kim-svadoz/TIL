/**
 * BOJ 21938 영상처리 Silver2
 * dfs / bfs :: 시간은 어느 걸 쓰나 비슷하게 나온다.
 */
import java.io.*;
import java.util.*;

public class p21938 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n, m, t;
    static double[][] screen;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        screen = new double[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                double rgb = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
                rgb /= 3;
                screen[i][j] = rgb;
            }
        }

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (screen[i][j] < t) screen[i][j] = 0;
            }
        }
    }

    static int solution() {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (screen[i][j] > 0 && visited[i][j] == false) {
                    dfs(i, j);
                    answer++;
                }
            }
        }
        return answer;
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (OOB(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            if (screen[nr][nc] <= 0) continue;

            dfs(nr, nc);
        }
    }

    static void bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (OOB(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (screen[nr][nc] <= 0) continue;
                
                q.add(new Point(nr, nc));
                visited[nr][nc] = true;
            }
        }
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
