/*
    등수찾기
    BFS 2번.
*/
import java.io.*;
import java.util.*;

public class p17616 {
    static int n, m, x;
    static List<List<Integer>>[] list;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[2];
        list[0] = new ArrayList<>();
        list[1] = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            list[0].add(new ArrayList<>());
            list[1].add(new ArrayList<>());
        }

        visited = new boolean[n + 1][2];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a가 b보다 우선순위가 높다.
            list[0].get(a).add(b);
            list[1].get(b).add(a);
        }
        int u = dfs(1) + 1;
        int v = n - dfs(0);
        System.out.println(u+" "+v);
    }

    static int dfs(int flag) {
        Queue<Integer> q = new LinkedList<>();
        visited[x][flag] = true;
        q.add(x);
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : list[flag].get(cur)) {
                if(!visited[next][flag]) {
                    visited[next][flag] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
