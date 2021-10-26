/**
 * BOJ 1719 택배 : Gold 4
 * 다익스트라, 역추적, 플로이드와샬
 */
import java.io.*;
import java.util.*;

public class p1719 {
    static class Edge implements Comparable<Edge> {
        int to, cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static int n, m;
    static List<List<Edge>> list;
    static int[] path, dist;
    static boolean[] visited;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(u).add(new Edge(v, cost));
            list.get(v).add(new Edge(u, cost));
        }

        for  (int i = 1; i <= n; i++) {
            path = new int[n + 1];
            dist = new int[n + 1];
            visited = new boolean[n + 1];
            Arrays.fill(dist, INF);
            dijkstra(i);
        }
    }

    /**
     * 한 정점에서의 최단거리가 모두 다르므로 각 정점마다 다익스트라를 돌려준다. -> 따라서 플로이드도 가능하다
     * @param start
     */
    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge adj : list.get(cur.to)) {
                if (visited[adj.to]) continue;

                if (dist[adj.to] > dist[cur.to] + adj.cost) {
                    // 경로를 역추적하기 위해 현재 인덱스에 직전 노드를 저장한다.
                    // ex. path[3] = 1 ::: 3번 노드를 오기 바로 직전노드는 1번노드
                    path[adj.to] = cur.to;
                    dist[adj.to] = dist[cur.to] + adj.cost;
                    pq.add(new Edge(adj.to, dist[adj.to]));
                }
            }
        }

        // dfs로 경로를 역추적해야 한다. (find method)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i == start) sb.append("-").append(" ");
            else sb.append(find(i, start)).append(" ");
        }
        System.out.println(sb.toString());
    }

    /**
     * 
     * @param x 현재 위치
     * @param start 최단거리 그래프의 맨 처음 시작 노드
     * @return
     */
    static int find(int x, int start) {
        if (path[x] == start) return x;
        return find(path[x], start);
    }
}
