/*
    아직 실패한 코드이다.
*/
import java.util.*;
import java.io.*;

public class p12896 {
    static int n;
    static List<Integer>[] list;
    static List<Integer>[] dp_left;
    static List<Integer>[] dp_right;
    static int[] dp;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        dp_left = new ArrayList[n + 1];
        dp_right = new ArrayList[n + 1];
        dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
            dp_left[i] = new ArrayList<>();
            dp_right[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        
        System.out.println("left:"+left(1, -1));
        System.out.println("right:"+right(1, -1));
        dfs(1, -1);
        System.out.println(dp[1]);
    }

    static int left(int cur, int parent) {
        int max = 0;
        for (int next : list[cur]) {
            dp_left[cur].add(max);
            if (next != parent) {
                dp[next] = Math.max(left(next, cur), dp[next] + 1);
                max = Math.max(max, dp[next] + 1);
            }
        }
        return max;
    }

    static int right(int cur, int parent) {
        int max = 0;
        for (int i = list[cur].size()-1; i >= 0; i--) {
            int next = list[cur].get(i);
            dp_right[cur].add(max);
            if (next != parent) {
                dp[next] = Math.max(right(next, cur), dp[next] + 1);
                max = Math.max(max, dp[next] + 1);
            }
        }
        Collections.sort(dp_right[cur], Collections.reverseOrder());
        return max;
    }

    static void dfs(int cur, int parent) {
        int max = 0;
        for (int next : list[cur]) {
            if (next != parent) {
                dfs(next, cur);
                max = Math.max(max, dp[next] + 1);
            }
        }
        dp[cur] = max;
    }
}