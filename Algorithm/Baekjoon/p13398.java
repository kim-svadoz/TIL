/*
    연속합 2.
    2차원 dp + 구간합
*/
import java.io.*;
import java.util.*;

public class p13398 {
    static int n;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][2]; // j = 0 : 수를 제거하지 않음, j = 1 : 특정 수를 제거함
        dp[0][0] = dp[0][1] = arr[0];

        int ans = arr[0];

        for (int i = 1; i < n; i++) {
            // 특정 수를 제거하지 않았을 경우에는 원래 방식대로 최대 연속합을 구한다.
            dp[i][0] = Math.max(arr[i], dp[i - 1][0] + arr[i]);

            // 특정 수를 제거할 경우는 2가지 케이스를 고려해야 한다.
            // 1. i번째 수가 처음 제거된 경우
            //   -> 단순히 이전 최대 연속 합을 할당하면 된다.
            // 2. i번째 수 전에 지워진 수가 있는 경우
            //   -> 현재 수는 지울 수 없으므로 이전 최대 연속합에다가 arr[i]를 더한다.
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);

            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        System.out.println(ans);
    }
}