/*
    플로이드 와샬 알고리즘 적용
*/
import java.io.*;
import java.util.*;

public class p1389 {
    static int n, m;
    static int kb[], dist[][];
    static final int INF = 100001;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 유저의 수
        dist = new int[n + 1][n + 1];
        kb = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                else {
                    dist[i][j] = INF;
                }
            }
        }

        m = Integer.parseInt(st.nextToken()); // 관계의 수
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        System.out.println(floyd());
    }

    static int floyd() {
        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += dist[i][j];
            }
            kb[i] = sum;
            min = Math.min(kb[i], min);
        }

        for (int i = 1; i <= n; i++) {
            if (kb[i] == min) {
                return i;
            }
        }

        return -1;
    }
}
