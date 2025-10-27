/*
    RGB거리 2
    
    dp[i][j] = k
    : 1 ~ i번째 집까지 칠하고 i번째 집은 j색으로 칠했을때의 최소비용 = k

    첫집 RED -> 첫집의 GREEN, BLUE의 DP값을 무한대로
    첫집 GREEN -> 첫집의 RED, BLUE의 DP값을 무한대로
    첫집 BLUE -> 첫집의 RED, GREEN DP값을 무한대로

    위 3가지 경우로 나눠서 계산한 뒤

    첫집 RED -> 마지막집을 GREEN, BLUE로 칠한 DP값 중 최솟값
    첫집 GREEN -> 마지막집을 RED, BLUE로 칠한 DP값 중 최솟값
    첫집 BLUE -> 마지막집을 RED, GREEN 칠한 DP값 중 최솟값
*/
import java.io.*;
import java.util.*;

public class p17404 {
    static int n, answer;
    static int[][] arr, dp;
    static final int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        answer = INF;
        arr = new int[n + 1][3];
        dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = r;
            arr[i][1] = g;
            arr[i][2] = b;
        }

        // k=0 -> RED,  k=1 -> GREEN,  k=2 -> BLUE 로 첫 집을 칠하는 경우
        for (int k = 0; k < 3; k++) {
            // RED, GREEN, BLUE로 칠하는 경우 각 색을 제외한 나머지는 INF로 초기화
            for (int i = 0; i < 3; i++) {
                if (i == k) dp[1][i] = arr[1][i];
                else dp[1][i] = INF;
            }

            // 열의 값인 0->RED, 1->GREEN, 2->BLUE 로 칠했을 때의 최소값을 의미
            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
            }

            for (int i = 0; i < 3; i++) {
                // 처음에 고른집은 정답후보에 고려하지 않는다!
                if (i != k) {
                    answer = Math.min(answer, dp[n][i]);
                }
            }
        }
        System.out.println(answer);
    }

}
