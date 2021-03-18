import java.io.*;
import java.util.*;

public class p1916 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static List<Town>[] list;
    static int[] dist;
    static class Town implements Comparable<Town>{
        int end, cost;
        public Town(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Town o) {
            return cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[u].add(new Town(v, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end));
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<Town> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n + 1];
        pq.add(new Town(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Town curTown = pq.poll();
            int cur = curTown.end;

            if (!visit[cur]) {
                visit[cur] = true;

                for (Town t : list[cur]) {
                    if (!visit[t.end] && dist[t.end] > dist[cur] + t.cost) {
                        dist[t.end] = dist[cur] + t.cost;
                        pq.add(new Town(t.end, dist[t.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
