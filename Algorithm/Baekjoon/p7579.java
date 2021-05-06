/*
    앱
    DP (냅색 알고리즘)
*/
import java.io.*;
import java.util.*;
public class p7579 {
    static final int INF = 987654321;
    static int n, m;
    static int[] arr, cost;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        cost = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[10001];
        Arrays.fill(dp, -1);

        // dp[i]: i cost를 써서 확보할 수 있는 최대 메모리
        for (int i = 0; i < n; i++) {
            int co = cost[i];
            
            // 뒤에서 부터 확인해야 겹치지 않고 값을 update 할 수 있다.
            for (int j = 10000; j >= co; j--) {
                if (dp[j - co] != -1) {
                    // 이전 j-cost 까지의 최대 값에 현재 mem을 더하는게 더 크다면 update
                    if (dp[j] < dp[j - co] + arr[i]) {
                        dp[j] = dp[j - co] + arr[i];
                    }
                }
            }
            // 메모리 업데이트가 안되어있는 경우 업데이트
			// 단 메모리가 더 큰 경우에만 업데이트 가능
            if (dp[co] < arr[i]) dp[co] = arr[i];
        }
        
        for (int i = 0; i < 10001; i++) {
            if (dp[i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }
}