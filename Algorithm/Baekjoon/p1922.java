/*
    최소신장트리(MST)
    Union-Find (Union-by-rank) 를 활용함
*/
import java.io.*;
import java.util.*;

public class p1922 {
    static class Edge implements Comparable<Edge>{
        int from, to, cost;
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        public int compareTo(Edge p) {
            return cost - p.cost;
        }
    }
    static int n, m;
    static int[] parent;
    static int[] rank;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        rank = new int[n + 1];
        parent = new int[n + 1];
        // parent 배열 초기화, 모든 초기값은 자기 자신이 부모로 세팅되어야 한다.
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, cost));
        }
        solution();
    }
    
    static void solution() {
        int ret = 0;
        // 모든 pq를 돌면서 탐색
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            // 현재 간선의 시작점과 끝점노드의 최상위 부모노드를 탐색한다.
            int a = find(cur.from);
            int b = find(cur.to);

            // 부모가 같다면 비용을 더하지 않고 넘어간다.
            // -> pq로 정렬했기 때문에, 최소 비용의 노드만 들어가게된다.
            if (a == b) continue;
            union(a, b);
            ret += cur.cost;
        }
        System.out.println(ret);
    }
    
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            // union by rank를 이용하면 시간이 조금 더 빨라진다
            // 항상 높이가 더 작은 트리가 높은 트리에 귀속된다.
            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                
                if (rank[rootA] == rank[rootB]) {
                    rank[rootA] ++;
                }
            }
        }
    }
    
    static int find(int x) {
        if (x == parent[x]) return x;
        
        // 다시 그 부모를 재귀하며 탐색
        return find(parent[x]);
    }
    
}