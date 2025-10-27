import java.io.*;
import java.util.*;

public class p1238 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, x;
    static List<Node>[] list; // 정상 도로
    static List<Node>[] listBack; // 돌아가는 도로
    static int dist[];
    static int distBack[];
    static class Node implements Comparable<Node>{
        int end, cost;
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점
        m = Integer.parseInt(st.nextToken()); // 간선
        x = Integer.parseInt(st.nextToken()); // 마을 (파티 위치)
        
        dist = new int[n + 1];
        distBack = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(distBack, Integer.MAX_VALUE);

        list = new ArrayList[n + 1];
        listBack = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) {
            list[i] = new ArrayList<>();
            listBack[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, cost));
            listBack[v].add(new Node(u, cost));
        }
        dijkstra(list, dist, x);
        dijkstra(listBack, distBack, x);

        int max = -1;
        for (int i = 1; i <= n; ++i) {
            max = Math.max(max, dist[i] + distBack[i]);
        }
        System.out.println(max);
    }

    static void dijkstra(List<Node>[] list, int[] dist, int start) {
        boolean[] visit = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.end;

            if (!visit[cur]) {
                visit[cur] = true;
    
                for (Node e : list[cur]) {
                    if (!visit[e.end] && dist[e.end] > dist[cur] + e.cost) {
                        dist[e.end] = dist[cur] + e.cost;
                        pq.add(new Node(e.end, dist[e.end]));
                    }
                }
            }
        }
    }
}
