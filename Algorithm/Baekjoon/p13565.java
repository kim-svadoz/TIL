/*
    침투
    DFS or BFS
*/
import java.io.*;
import java.util.*;
public class p13565 {
    static int m, n;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[][] map;
    static boolean flag = false;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j ++) {
                map[i][j] = line.charAt(j)-'0';
            }
        }
        for (int i = 0; i < n; i++) {
            if (map[0][i] == 0) {
                go(0, i);
            }
        }
        System.out.println(flag ? "YES" : "NO");
    }
    static void go(int r, int c) {
        visited[r][c] = true;
        if (r == m - 1) {
            flag = true;
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (OOB(nr, nc)) continue;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] == 1) continue;
            go(nr, nc);
        }
    }
    
    static boolean OOB (int r, int c) {
        return r < 0 || c < 0 || r >= m || c >= n;
    }
}