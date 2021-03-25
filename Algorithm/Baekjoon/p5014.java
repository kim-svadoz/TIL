// dp + bfs
import java.io.*;
import java.util.*;

public class p5014 {
    static BufferedReader br;
    static StringTokenizer st;
    static int f, g, s, u, d;
    static int dp[];
    static boolean visit[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken()); // 최고층
        g = Integer.parseInt(st.nextToken()); // 강호가 있는 층
        s = Integer.parseInt(st.nextToken()); // 면접 장소
        u = Integer.parseInt(st.nextToken()); // 위로 
        d = Integer.parseInt(st.nextToken()); // 아래로

        dp = new int[f + 1];
        Arrays.fill(dp, -1);
        visit = new boolean[f + 1];

        bfs(g);

        if (dp[s] == -1 && s != g) {
            System.out.println("use the stairs");
        } else {
            System.out.println(dp[s] + 1);
        }
    }
    
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == s) {
                return;
            }

            if (cur + u <= f && !visit[cur + u]) {
                q.add(cur + u);
                dp[cur + u] = dp[cur] + 1;
                visit[cur + u] = true;
            }
            if (cur - d > 0 && !visit[cur - d]) {
                q.add(cur - d);
                dp[cur - d] = dp[cur] + 1;
                visit[cur - d] = true;
            }
        }

    }
}