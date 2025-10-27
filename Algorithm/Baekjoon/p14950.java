/*
    정복자
    kruskal, MST(union-find by rank)
    
    오래걸린 부분과 이유
    단순히 모든 간선을 넣는게 아니라, 1번 노드를 맨 처음으로 탐색해야 하기 때문에
    간선의 배열을 만들어서 정렬을 따로 시켜주어야 한다.
*/
import java.io.*;
import java.util.*;
public class p14950 {
    static class Edge implements Comparable<Edge> {
        int u, v, cost;
        public Edge (int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static int n, m, t;
    static int[] parent, rank;
    static Edge[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        parent = new int[n + 1];
        rank = new int[n + 1];
        edges = new Edge[m];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            edges[i] = new Edge(u, v, cost);
        }
        Arrays.sort(edges);
        mst();
    }
    static void mst() {
        long ret = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if (find(edge.u) == find(edge.v)) continue;
            union(edge.u, edge.v);
            ret += edge.cost + (cnt * t);
            cnt++;
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
    static int find (int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }
}