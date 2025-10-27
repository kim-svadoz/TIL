/*
    악덕 영주 혜유
    최소 스패닝 트리 : MST -> Union-Find (Union-by-rank)
    트리의 지름 : dfs 2번 돌리기
*/
import java.io.*;
import java.util.*;

public class p20010 {
    static class Edge implements Comparable<Edge> {
        int u, v, cost;
        public Edge (int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        public Edge (int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static final int INF = 987654321;
    static List<Edge>[] list;
    static int n, k, leaf;
    static long answer = 0;
    static int[] parent, rank;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        rank = new int[n];
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, cost));
        }
        
        mst();

        leaf = 0;
        // 가장 먼 노드(leaf)를 구한다.
        dfs(0, 0, new boolean[n]);
        // 가장 먼 노드에서 다시 dfs를 돈다.
        dfs(leaf, 0, new boolean[n]);
        System.out.println(answer);
    }

    static void mst() {
        int ret = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int u = cur.u;
            int v = cur.v;
            int cost = cur.cost;
            if (find(u) == find(v)) continue;

            union(u, v);
            ret += cost;
            // union-find를 통해 최소 스패닝 트리를 구성하면서 해당 간선들을 graph에 추가한다.
            // !! union이 진행 된 후 알짜배기 간선들로만 graph를 만들어야 한다.
            list[u].add(new Edge(v, cost));
            list[v].add(new Edge(u, cost));
        }
        System.out.println(ret);
    }

    static void dfs(int now, int sum, boolean[] visited) {
        visited[now] = true;
        if (answer < sum) {
            answer = sum;
            leaf = now;
        }
        for (Edge edge : list[now]) {
            if (visited[edge.v]) continue;
            dfs(edge.v, sum + edge.cost, visited);
        }
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank[u] < rank[v]) {
                parent[u] = v;

            } else {
                parent[v] = u;

                if (rank[u] == rank[v]) {
                    rank[u]++;
                }
            }
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return find(parent[x]);
    }
    
}
