/*
    투자의 귀재 배주형
    DP
    1년보다는 3년, 3년보다는 5년짜리 투자가 무조건 좋으므로, 
    또 언제 투자방법을 바꾸는 것이 좋을지 모르므로 계속해서 비교해야 한다.
    처음 풀이는 i % 3 == 0, i % 5 == 0 이라는 조건으로 접근해서 틀렸었다.
*/
import java.io.*;
import java.util.*;

public class p19947 {
    static int h, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        long[] dp = new long[y + 1];
        // 원금 셋팅
        dp[0] = h;
        for (int i = 1; i <= y; i++) {
            // i = 1 : 1년이 지난 뒤 의 시점
            dp[i] = (int) (dp[i - 1] * 1.05);
            if (i >= 3) {
                dp[i] = (int) Math.max((dp[i - 3] * 1.2), dp[i]);
            }
            if (i >= 5) {
                dp[i] = (int) Math.max((dp[i - 5] * 1.35), dp[i]);
            }
        }

        
        System.out.println(dp[y]);
    }
}
