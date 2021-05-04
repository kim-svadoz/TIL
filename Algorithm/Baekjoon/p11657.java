/*
    타임머신
    인접리스트 + 벨만포드 알고리즘 (음의 사이클)
*/
import java.io.*;
import java.util.*;

public class p11657 {
    static class Pair {
        int to, cost;
        public Pair (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static long[] dist;
    static List<Pair>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new long[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[u].add(new Pair(v, cost));
        }
        // 음의 사이클
        boolean minusCycle = false;
        Arrays.fill(dist, INF);
        // 초기값은 0
        dist[1] = 0;
        // (n - 1)번 + 1번의 루프 : 마지막 1번은 음의 싸이클 존재 여부를 확인 하기 위함
        for (int i = 1; i <= n; i++) {
            // n-1번 루프의 걸쳐서 각 정점이 i + 1개 정점을 거쳐오는 최단경로를 갱신하고
            for (int j = 1; j <= n; j++) {
                for (Pair pair : list[j]) {
                    if (dist[j] == INF) break;

                    int next = pair.to;
                    int cost = pair.cost;
                    if (dist[next] > dist[j] + cost) {
                        dist[next] = dist[j] + cost;
                        // n번째 루프에 값이 갱신된다면 음의 싸이클이 존재
                        if (i == n) minusCycle = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (minusCycle) sb.append("-1");
        else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] >= INF) sb.append(-1).append("\n");
                else sb.append(dist[i]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
