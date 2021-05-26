/*
    물대기
    논 사이 연결 vs 논에 우물 파기
    직접 우물을 파는 경우 0번 노드(가상의 노드)에 연결하는 간선을 만들고 직접 우물을 파는 비용을 추가하여 MST를 완성한다.
    크루스칼, MST(Union-Find)
*/
import java.io.*;
import java.util.*;
public class p1368 {
    static class Edge implements Comparable<Edge> {
        int s, e, cost;
        public Edge (int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static int n;
    static int[] w, parent, rank;
    static int[][] p;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        w = new int[n + 1];
        parent = new int[n + 1];
        rank = new int[n + 1];
        p = new int[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                p[i][j] = Integer.parseInt(st.nextToken());

                // *핵심* 여기서 가상의 노드 0을 만들고 0과 연결된 간선은 우물을 파는 비용으로 해야 한다
                if (i != j) pq.add(new Edge(i, j, p[i][j]));
                else pq.add(new Edge(0, i, w[i]));

            }
        }
        
        mst();
    }
    static void mst() {
        int ret = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.s;
            int v = edge.e;
            if (find(u) == find(v)) continue;
            union(u, v);
            ret += edge.cost;
        }
        System.out.println(ret);
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
        
        return parent[x] = find(parent[x]);
    }
}