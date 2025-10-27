/*
    양
    BFS 구현
*/
import java.io.*;
import java.util.*;
public class p3184 {
    static class Pair {
        int r, c;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int rr, cc, oo, vv;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] visited;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rr = Integer.parseInt(st.nextToken());
        cc = Integer.parseInt(st.nextToken());
        
        vv = 0;
        oo = 0;
        map = new char[rr][cc];
        visited = new boolean[rr][cc];
        for (int i = 0; i < rr; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < cc; j++) {
                char c = input[j];
                if (c == 'v') {
                    vv++;
                } else if (c == 'o') {
                    oo++;
                }
                map[i][j] = c;
            }
        }
        for (int i = 0; i < rr; i++) {
            for (int j = 0; j < cc; j++) {
                if (map[i][j] == 'v') {
                    if (visited[i][j]) continue;
                    bfs(i, j);
                }
            }
        }
        System.out.println(oo+" "+vv);
    }
    
    static void bfs(int y, int x) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        int o = 0, v = 0;
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int r = p.r;
            int c = p.c;
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (OOB(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == '#') continue;
                if (map[nr][nc] == 'o') {
                    o++;
                } else if (map[nr][nc] == 'v') {
                    v++;
                }
                visited[nr][nc] = true;
                q.add(new Pair(nr, nc));
            }
        }
        if (o > v) {
            vv = vv - v;
        } else {
            oo = oo - o;
        }
    }
    
    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= rr || c >= cc;
    }
}