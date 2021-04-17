/*
    << 외판원 순회 >>

    처음 문제를 볼 땐 각각의 도시마다 출발도시로 두고 각 도시마다 최소값을 비교해서 풀어야하는줄 알았다.
    하지만, n개의 도시 중 아무 도시나 잡고 dfs를 돌려도 최소값은 항상 같다.

    모든 도시를 다 순회하고 자신의 출발도시 까지
    돌아와야 하니까 어느 도시에서 출발 하건 최솟값은 같다.
    (사이클은 어느 점을 시작점으로 잡아도 상관없다.)

    마을 방문 여부를 저장하기 위해 비트마스크를 사용한다.

    dfs + dp + 비트마스킹
*/
import java.io.*;
import java.util.*;

public class p2098 {
    // 최소값을 구해야 하니 큰 수로 저장
    static final int INF = 16 * 1000000;
    static int n;
    static int[][] w, dp;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        visit = new boolean[n];
        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        w = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        System.out.println(tsp(0, 1));
    }

    // node -> visit 방문
    static int tsp(int node, int visit) {
        // 모든 지점을 방문할 경우
        if (visit == (1 << n) - 1) {
            if (w[node][0] == 0) return INF;
            return w[node][0];
        }

        // 이미 방문된 곳이라면 이전에 저장된 값을 쓰겟다.
        if (dp[node][visit] != INF) {
            return dp[node][visit];
        }

        for (int i = 0; i < n; i++) {
            int next = visit | (1 << i);
            // i번 노드에 대해 길이 없거나, 이미 방문 한 경우(비트가 켜져있을 때 방문)
            if (w[node][i] == 0 || (visit & (1 << i)) != 0) continue;

            // 이전에 기억하고 있던 최소값이 작은지 , 새로 탐색할 곳과 그곳의 비용을 더하는게 작은지
            dp[node][visit] = Math.min(dp[node][visit], tsp(i, next) + w[node][i]);
        }

        return dp[node][visit];
    }

}
