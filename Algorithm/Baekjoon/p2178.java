import java.io.*;
import java.util.*;

public class p2178 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, map[][];
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = input.charAt(j - 1) - '0';
            }
        }

        bfs(new Pair(1, 1));
        System.out.println(map[N][M]);
    }

    static void bfs(Pair now) {
        Queue<Pair> q = new LinkedList<>();
        q.add(now);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx <= 0 || ny <= 0 || nx >= N + 1 || ny >= M + 1) continue;
                if (map[nx][ny] == 0 || visit[nx][ny]) continue;

                q.add(new Pair(nx, ny));
                map[nx][ny] = map[x][y] + 1;
                visit[nx][ny] = true;
            }
        }
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
