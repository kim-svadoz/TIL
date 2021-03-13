import java.io.*;
import java.util.*;

public class p1260 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, V;
    static ArrayList<Integer>[] list;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1]; 

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }
        check = new boolean[N + 1];
        dfs(V);
        System.out.println();
        
        check = new boolean[N + 1];
        bfs(V);
        System.out.println();
    }

    static void dfs(int num) {
        if (check[num]) {
            return;
        }
        check[num] = true;
        System.out.print(num + " ");
        for (int n : list[num]) {
            if (!check[n]) {
                dfs(n);
            }
        }
    }

    static void bfs(int num) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(num);
        check[num] = true;

        while (!q.isEmpty()) {
            int x = q.poll();
            System.out.print(x + " ");
            for (int y : list[x]) {
                if (!check[y]) {
                    check[y] = true;
                    q.add(y);
                }
            }
        }
    }
}
