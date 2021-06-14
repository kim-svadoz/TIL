/*
	1480
	보석모으기
    knapsack + bitmasking
	
	n : 보석의 개수
	m : 가방의 개수
	c : 가방의 최대 한도
	dp[n][m][c]
*/
import java.io.*;
import java.util.*;
public class p1480 {
    static final int MAX = 14;
    static int n, m, c;
    static int[] arr;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        arr = new int[n];
        dp = new int[1 << MAX][11][21];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 0(bit)을 시작으로, 0번째(idx) 가방부터 탐색, 처음 허용용량은 c
        int answer = recur(0, 0, c);
        System.out.println(answer);
    }
    static int recur(int path, int idx, int k) {
        // 모든 보석이나, 모든 가방을 사용했다면 return
        if ((path == (1 << n) - 1) || idx == m) {
            return 0;
        }
        
        // 이미 방문한 곳이라면
        if (dp[path][idx][k] != 0) {
            return dp[path][idx][k];
        }
        
        for (int i = 0; i < n; i++) {
            // path의 i번째 보석이 사용되었거나(bit on), 남은 용량이 해당 보석의 무게보다 작다면 넘어감
            if ((path & (1 << i)) > 0) {
                continue;
            }
            
            // 해당 가방(idx)의 용량이 남아있다면, 다음 보석을 담기 위해 그 가방을 계속 사용
            if (k >= arr[i]) {
                dp[path][idx][k] = Math.max(dp[path][idx][k], recur(path | (1 << i), idx, k - arr[i]) + 1);    
            } else {
                // 다음 가방(idx + 1)을 탐색
                dp[path][idx][k] = Math.max(dp[path][idx][k], recur(path, idx + 1, c));
            }
            
        }

        return dp[path][idx][k];
    }
}