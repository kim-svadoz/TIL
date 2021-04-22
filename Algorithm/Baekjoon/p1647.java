/*
    도시 분할 계획
    최소신장트리(MST)
    Union-Find
    마을을 분할하되, 최소의 비용만으로 마을 안의 집들은 연결되어 있어야 한다.
    여기서 임의의 간선 한개를 잘라도 1개의 집 자체가 마을이 되기 때문에 최소 신장트리를 만족한다.
    따라서 최소 비용으로 두 개의 마을이 되기 위해선 가장 비싼 간선 1개를 없애면 된다!
*/
import java.io.*;
import java.util.*;

public class p1647 {
    static class Edge implements Comparable<Edge>{
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        
        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }
    static int n, m;
    static int[] parent, rank;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        rank = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
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
        int max = 0;
        // 가장 비용이 적은 순부터 탐색하기 때문에 가장 마지막에 있는 값을 제거하면 답이 된다.
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int u = cur.u;
            int v = cur.v;
            int cost = cur.cost;
            if (find(u) == find(v)) continue;
            
            union(u, v);
            ret += cost;
            max = cur.cost;
        }
        System.out.println(ret - max);
    }
    
    static int find(int x) {
        if (x == parent[x]) return x;
        
        return find(parent[x]);
    }
    
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[y] = x;
                
                if (rank[x] == rank[y]) {
                    rank[x]++;
                }
            }
        }
    }
}