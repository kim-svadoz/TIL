/*
    군사이동
    크루스칼, MST(Union-Find by Rank)
*/
import java.io.*;
import java.util.*;
public class p11085 {
    static class Edge implements Comparable<Edge>{
        int u, v, cost;
        public Edge (int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        
        public int compareTo(Edge o) {
            return o.cost - cost;
        }
    }
    static int p, w, c, v;
    static int[] parent, rank;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        
        parent = new int[p];
        rank = new int[p];
        for (int i = 0; i < p; i++) {
            parent[i] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, cost));
        }

        mst();
    }
    
    static void mst() {
        int ret = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            union(edge.u, edge.v);
            // 모든 경로들을 union하면서, c와 v가 서로 연결되어있는지 확인한다. (같은 루트인지)
            if (find(c) == find(v)) {
                ret = edge.cost;
                break;
            }
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