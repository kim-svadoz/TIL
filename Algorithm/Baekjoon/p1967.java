import java.io.*;
import java.util.*;

public class p1967 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static List<Edge>[] list;
	static int[] dust;
	public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        list = new ArrayList[N + 1];
        dust = new int[N + 1];
        
        for (int i = 1; i <= N; ++i) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 1; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            list[u].add(new Edge(v, cost));
            list[v].add(new Edge(u, cost));
        }
        
        dust = bfs(list, 1);
        
        int start = 1;
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if(dust[start] < dust[i]) {
                    start = i;
                }
            }
        }

        dust = bfs(list, start);

        int max = 0;
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if(dust[max] < dust[i]) {
                    max = i;
                }
            }
        }

        System.out.println(dust[max]);
	}
    
    static int[] bfs(List<Edge>[] list, int start) {
        int[] dust = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        
        while (!q.isEmpty()) {
            // 부모
            int cur = q.poll();
            visit[cur] = true;
            
            // 자식 탐색
            for (Edge e : list[cur]) {
                int y = e.y;
                int cost = e.cost;
                if (!visit[y]) {
                    q.add(y);
                    visit[y] = true;
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