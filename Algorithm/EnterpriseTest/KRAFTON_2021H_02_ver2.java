import java.io.*;
import java.util.*;

public class KRAFTON_2021H_02_ver2 {
    public static void main(String[] args) throws IOException {
        int n = 6;
        int[][] network = {
            {1, 2},
            {3, 5},
            {4, 2},
            {5, 6}
        };
        int[][] repair =  {
            {3, 2, 10},
            {5, 4, 15}
        };
        solution(n, network, repair);
    }
    
    static class Edge implements Comaparable<Edge> {
        int u, v, cost;
        public Edge (int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        public Edge (int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static boolean[] visited;
    static int[] parent, rank;
    static List<Edge>[] list;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void solution(int n, int[][] network, int[][] repair) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        rank = new int[n + 1];
        
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int[] net : network) {
            int u = net[0];
            int v = net[1];
            union(u, v);

            list[u].add(new Edge(v, 0));
            list[v].add(new Edge(u, 0));
        }

        for (int[] rep : repair) {
            int u = rep[0];
            int v = rep[1];
            int cost = rep[2];
            pq.add(new Edge(u, v, cost));
        }


        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.u;
            int v = edge.v;
            if (find(u) == find(v)) continue;
            union(u, v);

            list[u].add(new Edge(v, edge.cost));

            visited = new boolean[n + 1];
            dfs(1);
        }
    }

    static void dfs(int start) {
        visited[start] = true;
        for (Node node : list[start]) {
            
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
