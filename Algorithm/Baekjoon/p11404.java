/*
    플로이드
    인접행렬 + 플로이드 와샬
*/
import java.io.*;
import java.util.*;

public class p11404 {
    static final int INF = 1000000000;
    static int n, m;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= n; j++) {
                dist[i][j] = i == j ? 0 : INF;
            }
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(dist[u][v], cost);
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] >= INF) sb.append("0").append(" ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
}
