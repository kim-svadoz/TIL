/*
    양치기 꿍
    BFS
*/
import java.io.*;
import java.util.*;
public class p3187 {
    static class Pair {
        int r, c;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int totalV, totalK, n, m;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        totalK = 0;
        totalV = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'v') totalV++;
                else if (map[i][j] == 'k') totalK++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'v' || map[i][j] == 'k') {
                    if (visited[i][j]) continue;
                    go(i, j);
                }
            }
        }
        System.out.println(totalK+" "+totalV);
    }
    
    static void go(int r, int c) {
        Queue<Pair> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new Pair(r, c));
        int k = 0, v = 0;
        
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (map[cur.r][cur.c] == 'k') {
                k++;
            } else if (map[cur.r][cur.c] == 'v') {
                v++;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (OOB(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == '#') continue;

                visited[nr][nc] = true;
                q.add(new Pair(nr, nc));
            }
        }
        
        if (k > v) {
            totalV -= v;
        } else {
            totalK -= k;
        }
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}