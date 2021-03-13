import java.io.*;
import java.util.*;

public class p11724 {
    static int N, M, cnt;
    static BufferedReader br;
    static StringTokenizer st;
    static List<Integer>[] list;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        check = new boolean[N + 1];
        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (!check[i]) {
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void dfs(int num) {
        if (check[num]) {
            return;
        }
        check[num] = true;
        for (int x : list[num]) {
            if (!check[x]) {
                dfs(x);
            }
        }
    }
}
