/*
    최단경로
    인접리스트 + 다익스트라.
*/
import java.io.*;
import java.util.*;
public class p1753 {
    static class Node implements Comparable<Node> {
        int e, cost;
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    static final int INF = 987654321;
    static int v, e, k;
    static int[] dist;
    static boolean[] visited;
    static List<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        dist = new int[v + 1];
        Arrays.fill(dist, INF);
        visited = new boolean[v + 1];
        list = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, cost));
        }

        bfs(k);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void bfs(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.e]) continue;
            visited[cur.e] = true;
            
            for (Node next : list[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.cost) {
                    dist[next.e] = dist[cur.e] + next.cost;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }
    }
}