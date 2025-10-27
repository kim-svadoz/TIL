/*
	공주님을 구해라
    bfs

    3차원 배열을 이용해서 GRAM을 가지고 있는지 유무를 판단한다.
*/
import java.io.*;
import java.util.*;
public class p17836 {
    static class Solider {
        int r, c, isGetGram, time;
        public Solider(int r, int c, int isGetGram, int time) {
            this.r = r;
            this.c = c;
            this.isGetGram = isGetGram;
            this.time = time;
        }
    }
    static int n, m, t;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        go(1, 1);
    }
    
    static void go(int r, int c) {
        Queue<Solider> q = new LinkedList<>();
        q.add(new Solider(r, c, 0, 0));
        boolean[][][] visited = new boolean[n + 1][m + 1][2];
        
        while (!q.isEmpty()) {
            Solider cur = q.poll();
            int gram = cur.isGetGram;
            //System.out.println("r:"+cur.r+", c:"+cur.c+", gram:"+cur.isGetGram+", time:"+cur.time);

            if (cur.time > t) {
                continue;
            }
            
            if (cur.r == n && cur.c == m) {
                System.out.println(cur.time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (OOB(nr, nc)) continue;
                if (visited[nr][nc][gram]) continue;
                
                // 마검을 들고 있다면 다음 탐색할 벽이 1인지 아닌지 노상관
                if (gram == 0) {
                    if (map[nr][nc] == 1) continue;

                    visited[nr][nc][gram] = true;
                    if (map[nr][nc] == 2) {
                        q.add(new Solider(nr, nc, 1, cur.time + 1));
                    } else {
                        q.add(new Solider(nr, nc, 0, cur.time + 1));
                    }
                } else {
                    visited[nr][nc][gram] = true;
                    q.add(new Solider(nr, nc, 1, cur.time + 1));
                }
            }
        }
        System.out.println("Fail");
    }
    
    static boolean OOB(int r, int c) {
        return r < 1 || c < 1 || r > n || c > m;
    }
}