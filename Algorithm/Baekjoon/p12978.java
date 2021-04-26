/*
    TREE + DP 기본
*/
import java.util.*;
import java.io.*;

public class p12978 {
    static int n;
    static List<Integer>[] list;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        
        // 사이클을 형성하지 않으므로 아무 노드나 시작점으로 결정해도 상관없다.
        dfs(1, -1);
        // 시작점에서의 메모이제이션 배열을 가져와야한다.
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    
    static void dfs(int cur, int parent) {
        //dp[cur][0] = 0;
        //dp[cur][1] = 1;
        
        for (int next : list[cur]) {
            if (next != parent) {
                dfs(next, cur);
                // 현재도시에서 건설하지 않는다면 자식 도시들은 반드시 건설해야한다.
                dp[cur][0] += dp[next][1];
                // 현재도시에서 건설한다면 다음 도시는 건설해도 되고, 안해도 된다.
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
        dp[cur][1] += 1;
    }
}