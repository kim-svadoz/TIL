/*
    그림
    BFS or DFS 구현
*/
import java.io.*;
import java.util.*;
public class p1926 {
    static class Pair {
        int r, c, cnt;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int n, m, cnt = 0, max = 0;
    static int[][] pic;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pic = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                pic[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && pic[i][j] == 1) {
                    max = Math.max(max, go(i, j));
                    cnt++;
                }
            }
        }
        
        System.out.println(cnt);
        System.out.println(max);
    }
    
    static int go(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;
        int ret = 0;
        
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int r = p.r;
            int c = p.c;
            int cnt = p.cnt;
            ret += 1;
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (OOB(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (pic[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                q.add(new Pair(nr, nc));
            }
        }
        
        return ret;
    }
    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}