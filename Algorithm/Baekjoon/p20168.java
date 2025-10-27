/*
    골목대장 호석
    Dijkstra + Binary Search(Gold 5)

    ver 1(실패) : Dijkstra
      -> 다익스트라만 쓰면 최대 골목 비용을 고려할 수가 없다.
      따라서 최대 골목 비용을 X라고 fix하고 다익스트라를 사용해야 한다.
    ver 2 : Dijkstra + 이분탐색
    
*/
import java.io.*;
import java.util.*;

public class p20168 {
    static class Node implements Comparable<Node>{
        int end;
        long cost;
        public Node (int end, long cost) {
            this.end = end;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            if (cost > o.cost) return 1;
            if (cost == o.cost) return 0;
            return -1;
        }
    }
    static final long INF = 10000000000000001L;
    static int n, m, a, b, c;
    static long answer;
    static long[] dp;
    static List<List<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(u).add(new Node(v, cost));
            list.get(v).add(new Node(u, cost));
        }

        // dp[i] : i번 골목까지 가는 최소 요금
        dp = new long[n + 1];

        binarySearch();
        System.out.println(answer == 1000000001 ? -1 : answer);
    }

    static void binarySearch()  {
        long l = 1, r = 1000000001;
        answer = r;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (dijkstra(mid)) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    static boolean dijkstra(long x) {
        Arrays.fill(dp, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(a, 0));
        dp[a] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dp[cur.end] != cur.cost) continue;

            for (Node next : list.get(cur.end)) {
                // 탐색 중인 x보다 비용이 크다면 무시한다.
                if (next.cost > x) continue;

                if (dp[next.end] > next.cost + dp[cur.end]) {
                    dp[next.end] = next.cost + dp[cur.end];
                    pq.add(new Node(next.end, dp[next.end]));
                }
            }
        }

        return dp[b] <= c;
    }
}
