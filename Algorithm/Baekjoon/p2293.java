import java.io.*;
import java.util.*;

public class p2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[k + 1];
        dp[0] = 1;
        //   i    0 1 2 3 
        // arr[i] 0 1 2 5
        // dp[k] : k원이 되도록 하는 동전의 경우의 수
        // 모든 동전을 사용하는 경우의 수이므로 각각의 동전들로 구성할 수 있는 경우를 누적한다.
        for (int i = 1; i <= n; i++) {
            for (int j = arr[i]; j <= k; j++) {
                dp[j] += dp[j- arr[i]];
            }
        }
        System.out.println(dp[k]);
    }
}
