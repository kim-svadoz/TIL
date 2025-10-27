import java.io.*;
import java.util.*;

public class p4963 {
    static BufferedReader br;
    static StringTokenizer st;
    static String input;
    static int w, h, sum, map[][];
    static boolean visit[][];
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            map = new int[h + 1][w + 1];
            visit = new boolean[h + 1][w + 1];

            for (int i = 1; i <= h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sum = 0;
            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        bfs(new Point(i, j));
                        sum++;
                    }
                }
            }
            System.out.println(sum);
        }
    }

    static void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int a = cur.x;
            int b = cur.y;
            visit[a][b] = true;

            for (int i = 0; i < 8; i++) {
                int nx = a + dx[i];
                int ny = b + dy[i];

                if (nx <= 0 || ny <= 0 || nx >= h + 1 || ny >= w + 1) continue;
                if (map[nx][ny] == 0 || visit[nx][ny]) continue;
                q.add(new Point(nx, ny));
                visit[nx][ny] = true;
            }
        }
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
