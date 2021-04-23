import java.io.*;
import java.util.*;

public class p2294 {
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
        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            dp[i] = 100001;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = arr[i]; j<= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
            }
        }

        if (dp[k] == 100001) dp[k] = -1;
        // dp[k] : k의 가치를 만들기 위해 사용한 동전의 최소 개수
        /*
            여기서 0은 INF를 의미. dp배열을 모두 최대값으로 세팅

                    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
            1       1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
            5       0  0  0  0  1  2  3  4  5   2   3   4   5   6   3
            12      0  0  0  0  0  0  0  0  0   0   0   1   2   3   3
            dp                         Math.min()
        */

        System.out.println(dp[k]);
    }
}
