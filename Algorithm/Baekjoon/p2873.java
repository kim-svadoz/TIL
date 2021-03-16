import java.io.*;
import java.util.*;

public class p2873 {
    static BufferedReader br;
    static StringTokenizer st;
    static int R, C, map[][];
    static boolean visit[][];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static String answer = "";
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        visit = new boolean[R + 1][C + 1];

        for (int i = 1; i <= R; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new Pair(1, 1));
        System.out.println(answer);
    }

    static void dfs(Pair now) {
        if(visit[now.x][now.y]) return;
        visit[now.x][now.y] = true;

        for (int i = 0; i < 4; ++i) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            if (nx <= 0 || ny <= 0 || nx >= R + 1 || ny >= C + 1) continue;
            if (visit[nx][ny]) continue;

            switch (i) {
            case 0:
                answer += "R";
                break;
            case 1:
                answer += "D";
                break;
            case 2:
                answer += "L";
                break;
            case 3:
                answer += "U";
                break;
            default:
            }
            dfs(new Pair(nx, ny));
        }
    }

    static class Pair {
        int x, y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
