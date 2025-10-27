import java.io.*;
import java.util.*;

public class p1261 {
    static int m, n, map[][];
    static boolean[][] visit;
    static int dr[] = {1, 0, -1, 0};
    static int dc[] = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        System.out.println(bfs(new Point(1, 1, 0)));
    }

    static int bfs(Point start) {
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(start);
        visit[start.r][start.c] = true;
        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.r == n && cur.c == m) {
                return cur.cost;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (OOB(nr, nc) || visit[nr][nc]) continue;

                if (map[nr][nc] == 1) { // 부숴야함
                    q.add(new Point(nr, nc, cur.cost + 1));
                    visit[nr][nc] = true;
                } else if (map[nr][nc] == 0) { // 그냥 지나감
                    q.add(new Point(nr, nc, cur.cost));
                    visit[nr][nc] = true;
                }
            }
        }

        return min;
    }

    // Out Of Bounds
    static boolean OOB(int r, int c) {
        return (r < 1 || r > n || c < 1 || c > m);
    }

    static class Point implements Comparable<Point>{
        int r, c, cost;
        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
        @Override
        public int compareTo(Point o) {
            return cost - o.cost;
        }
    }
}
