/*
    인내의 도미노 장인 호석
    구현(Gold 5)
*/
import java.io.*;
import java.util.*;

public class p20165 {
    static int n, m, r;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int score;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n + 1][m + 1];

        int answer = 0;
        while (r-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String action = st.nextToken();
            int dir = -1;
            if (action.equals("N")) {
                dir = 0;
            } else if (action.equals("E")) {
                dir = 1;
            } else if (action.equals("S")) {
                dir = 2;
            } else if (action.equals("W")) {
                dir = 3;
            }

            score = 0;
            visited[x][y] = true;
            score++;
            attack(x, y, dir, map[x][y] - 1);

            answer += score;
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            visited[p][q] = false; // 도미노를 세운다.
        }

        System.out.println(answer);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (visited[i][j]) {
                    sb.append("F").append(" ");
                } else {
                    sb.append("S").append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
    
    static void attack(int r, int c, int dir, int power) {
        for (int i = 1; i <= power; i++) {
            int nr = r + dr[dir] * i;
            int nc = c + dc[dir] * i;
            if (OOB(nr, nc)) continue;
            if (visited[nr][nc]) continue; // 도미노가 이미 쓰러져있다면 넘어간다.
            visited[nr][nc] = true; // 도미노를 쓰러트린다.
            score++;

            if (map[nr][nc] >= i) { // 다음 칸의 도미노 길이가 남아있는 도미노 길이보다 같거나 길다면 새롭게 attack한다
                attack(nr, nc, dir, map[nr][nc] - 1);
            }

            if (i == power && map[nr][nc] > 1) { // 마지막에 도미노의 길이가 1보다 크다면 새롭게 attack한다.
                attack(nr, nc, dir, map[nr][nc] - 1);
            }
        }
    }

    static boolean OOB(int r, int c) {
        return r <= 0 || c <= 0 || r > n || c > m;
    }
}
