import java.io.*;
import java.util.*;

public class p10971 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, min, w[][];
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n + 1][n + 1];
        visit = new boolean[n + 1];
        min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            dfs(i, i, 0, 0);
        }

        System.out.println(min);
    }

    static void dfs(int start, int i, int cnt, int sum) {
        if (cnt == n && start == i) {
            min = Math.min(min, sum);
            return;
        }

        for (int j = 1; j <= n; j++) {
            if (w[i][j] == 0) continue;

            if (!visit[i] && w[i][j] > 0) {
                visit[i] = true;
                sum += w[i][j];
                dfs(start, j, cnt + 1, sum);
                sum -= w[i][j];
                visit[i] = false;
            }
        }

    }

}
