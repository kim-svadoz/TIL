import java.io.*;
import java.util.*;

public class p1948 {
    static int n, m, cnt = 0, min = 0;
    static int[] degree, d;
    static boolean[] visit;
    static List<Town>[] list;
    static List<Town>[] reverseList;
    static class Town {
        int start, cost;

        public Town(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        degree = new int[n + 1];

        list = new ArrayList[n + 1];
        reverseList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 출발 
            int v = Integer.parseInt(st.nextToken()); // 도착
            int w = Integer.parseInt(st.nextToken());

            list[v].add(new Town(u, w));  // 도착하는 곳부터 보기위함
            reverseList[u].add(new Town(v, w));
            degree[u]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        min = solve(start, end);

        System.out.println(min+" "+cnt);
    }

    static int solve(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        visit = new boolean[n + 1];
        d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) { // 도착도시는 자연스럽게 degree[i]가 0이다.
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Town t : list[cur]) {
                int s = t.start;
                int cost = t.cost;


                degree[s]--; // 위상 재 정렬
                if (degree[s] == 0) {
                    q.add(s); // bfs 
                }

                if (d[s] < d[cur] + cost) { // 최대비용
                    d[s] = d[cur] + cost;
                }
            }
        }
        // 보통 위상정렬이랑 다르게 내가 지나온 경로를 trace하기 위해서 dfs를 한번 더 돌아줘야 한다.
        // 때문에 역방향 간선리스트도 만들어줘야한다.
        dfs(start);

        return d[start];
    }

    static void dfs(int start) {
        if(visit[start]) return; // 이미 지나온 경로는 배제한다.
        visit[start] = true;

        for (Town t : reverseList[start]) {
            int s = t.start;
            int cost = t.cost;
            if (d[start] == d[s] + cost) {
                cnt++;
                dfs(s);
            }

        }
    }
}
