/*
    최소비용 구하기 2
    Dijkstra + BackTrace
    다익스트라를 활용하며 추가로 역추적을 구현하면 된다.
*/
import java.io.*;
import java.util.*;
public class p11779 {
    static class Node implements Comparable<Node> {
        int e, cost;
        public Node (int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    static final int INF = 987654321;
    static int n, m, start, end;
    static List<Node>[] list;
    static List<Integer> routes;
    static int[] route;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dist, INF);
        route = new int[n + 1];
        routes = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, cost));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        dijkstra(start);
        
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");
        sb.append(routes.size()).append("\n");
        for (int i = routes.size() - 1; i >= 0; i--) {
            sb.append(routes.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        route[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.e;

            for (Node nextNode : list[cur]) {
                int next = nextNode.e;
                int cost = nextNode.cost;

                if (dist[next] > dist[cur] + cost) {
                    dist[next] = dist[cur] + cost;
                    pq.add(new Node(next, dist[next]));
                    // 역추적을 위한 parent 배열 정보 저장
                    route[next] = cur;
                }
            }
        }

        // recursive하게 탐색하며 ArrayList에 저장
        int node = end;
        while (node != 0) {
            routes.add(node);
            node = route[node];
        }
    }
}