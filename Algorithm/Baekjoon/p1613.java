/*
    플로이드 와샬 알고리즘 적용
*/
import java.io.*;
import java.util.*;

public class p1613 {
    static int n, k, s;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사건의 수

        dist = new int[n + 1][n + 1];

        k = Integer.parseInt(st.nextToken()); // 사건 관계의 수
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            dist[u][v] = -1;
            dist[v][u] = 1;
        }

        folyd_warshall();

        s = Integer.parseInt(br.readLine()); // 알고 싶은 사건 쌍의 수
        while (s-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            System.out.println(dist[u][v]);
        }
    }

    static void folyd_warshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] == 0) {
                        if (dist[i][k] == 1 && dist[k][j] == 1) {
                            dist[i][j] = 1;
                        } else if (dist[i][k] == -1  && dist[k][j] == - 1) {
                            dist[i][j] = -1;
                        }
                    }
                }
            }
        }
    }
}
