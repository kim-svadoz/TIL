import java.io.*;
import java.util.*;

public class p11725 {
    static int N;
    static BufferedReader br;
    static StringTokenizer st;
    static List<Integer>[] list;
    static boolean[] visit;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        bfs(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }

    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int x : list[cur]) {
                if(!visit[x]) {
                    q.add(x);
                    visit[x] = true;
                    parents[x] = cur;
                }
            }
        }
    }
}
