import java.io.*;
import java.util.*;

public class p10451 {
    static BufferedReader br;
    static StringTokenizer st;
    static int T, N;
    static List<Integer>[] list;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int v = Integer.parseInt(st.nextToken());

                list[i].add(v);
                list[v].add(i);
            }

            visit = new boolean[N + 1];

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (!visit[i]) {
                    dfs(i);
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    static void dfs(int num) {
        if (visit[num]) {
            return;
        }
        visit[num] = true;
        for (int x : list[num]) {
            if (!visit[x]) {
                dfs(x);
            }
        }
    }
}
