import java.io.*;
import java.util.*;

public class p1167 {
    static BufferedReader br;
    static StringTokenizer st;
    static int V;
    static List<Edge>[] list;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        list = new ArrayList[V + 1];
        int[] dust = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());

            int v = -1;
            while ((v = Integer.parseInt(st.nextToken())) != -1) {
                int dist = Integer.parseInt(st.nextToken());
                list[u].add(new Edge(v, dist));
            }
        }
        dust = bfs(list, 1);

        int start = 0;
        for (int i = 1; i < dust.length; i++) {
            if (dust[start] < dust[i]) {
                start = i;
            }
        }

        dust = bfs(list, start);

        int max = start;
        for (int i = 1; i < dust.length; i++) {
            if (dust[max] < dust[i]) {
                max = i;
            }
        }

        System.out.println(dust[max]);
    }

    static int[] bfs(List<Edge>[] list, int start) {
        boolean[] visit = new boolean[V + 1];
        int[] dust = new int[V + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            visit[cur] = true;

            for(Edge e : list[cur]) {
                int y = e.y;
                int cost = e.cost;
                if (!visit[y]) {
                    q.add(y);
                    dust[y] = dust[cur] + cost;
                }
            }
        }

        return dust;
    }

    static class Edge {
        int y, cost;
        public Edge(int y, int cost) {
            this.y = y;
            this.cost = cost;
        }
    }
}
