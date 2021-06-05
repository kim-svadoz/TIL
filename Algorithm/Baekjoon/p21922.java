import java.io.*;
import java.util.*;
public class p21922 {
    static class Pair {
        int r, c, num;
        public Pair(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
    static int n, m;
    static int[][] lab;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        lab = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (lab[i][j] == 9) {
                    bfs(i, j);
                }
            }
        }
    }

    static void bfs(int r, int c) {
        int ret = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(r, c, lab[r][c]));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (cur.num == 9) {
                
            }
        }
    }

    static boolean OOB(int r, int c) {
        return r <= 0 || c <= 0 || r > n || c > m;
    }
}
