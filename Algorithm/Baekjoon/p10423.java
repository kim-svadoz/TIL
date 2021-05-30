/*
    전기가 부족해
    크루스칼, MST(Union-Find by Rank)

    발전소의 루트노드를 -1로 초기화 해서 현재노드의 루트노드가 -1일 경우 종료한다.
    -> 루트 노드(발전소)로부터 전기를 공급받을 수 있기 때문에 더이상 다른 노드를 연결하지 않아도 된다.
*/
import java.io.*;
import java.util.*;
public class p10423 {
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        public Edge (int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }
    static int n, m, k;
    static int[] parent, rank;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            parent[Integer.parseInt(st.nextToken())] = -1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }

        mst();
    }
    static void mst() {
        int ret = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.u) == find(edge.v)) continue; // 서로 연결되어 있는 경우는 패스
            union(edge.u, edge.v);  // 어떻게 연결시켜줄 것인가? -> union method
            ret += edge.w;
        }
        System.out.println(ret);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (u == -1) {                  // u 만 발전소에 연결
                parent[v] = u;
            } else if (v == -1) {           // v 만 발전소에 연결
                parent[u] = v;
            } else {
                if (u == -1 && v == -1) {   // u, v 둘다 발전소에 연결
                    return;
                } else {                    // 둘다 발전소에 연결 안된 경우
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
            
        }
    }
    static int find(int x) {
        if (parent[x] == -1) return -1;
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }
}
