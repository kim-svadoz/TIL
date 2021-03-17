import java.io.*;
import java.util.*;

public class p2533 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, dp[][];
    static boolean visit[];
    static List<Integer>[] edge;
    static int true_earlyAdoptor = 0;
    static int false_earlyAdoptor = 0;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][2];
        visit = new boolean[n + 1];

        edge = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) {
            edge[i] = new ArrayList<>();
            dp[i][0] = dp[i][1] = -1;
        }

        int loop = n - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edge[u].add(v);
            edge[v].add(u);
        }

        int min = Math.min(dfs(1, false), dfs(1, true));
        System.out.println(min);
        
    }

    static int dfs(int n, boolean t) {
        int tt = t ? 1 : 0;
        if(dp[n][tt] != -1) {
            return dp[n][tt];
        }
        int sum = t ? 1 : 0;

        visit[n] = true;
        if (t) {
            for (int next : edge[n]) {
                if(visit[next]) continue;
                sum += Math.min(dfs(next, false), dfs(next, true));
            }
        } else {
            for (int next : edge[n]) {
                if(visit[next]) continue;
                sum += dfs(next, true);
            }
        }
        visit[n] = false;
        return dp[n][tt] = sum;
    }
}
