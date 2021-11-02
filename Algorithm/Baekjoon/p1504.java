/**
 * BOJ 1504 특정한 최단경로
 * 다익스트라
 */
import java.io.*;
import java.util.*;

public class p1504 {
    static class Node implements Comparable<Node> {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    static int n, e;
    static int[] dp;
    static boolean[] visited;
    static List<List<Node>> list;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        visited = new boolean[n + 1];
        list = new ArrayList<>();
        pq = new PriorityQueue<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long res1  = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, n);

        long res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, n);

        long answer = Math.min(res1, res2);
        answer = answer >= INF ? -1 : answer;

        System.out.println(answer);
    }
    static int dijkstra(int s, int e) {
        Arrays.fill(dp, INF);
        Arrays.fill(visited, false);

        pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        dp[s] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int v = cur.v;
            if (visited[v]) continue;
            visited[v] = true;
            if (v == e) break;

            for (Node next : list.get(v)) {
                if (visited[next.v]) continue;

                if (!visited[next.v] && dp[next.v] > dp[v] + next.cost) {
                    dp[next.v] = dp[v] + next.cost;
                    pq.add(new Node(next.v, dp[next.v]));
                }
            }
        }
        return dp[e];
    }
}
