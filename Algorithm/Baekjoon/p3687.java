import java.io.*;
import java.util.*;

public class p3687 {
    static int tc, n;
    static long[] minDp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        /*
            i : 성냥개비의 개수, dp[i] : 만들 수 있는 가장 작은 숫자
            dp[0] = 0;
            dp[1] = 0;
            dp[2] = 1; (dp[2])
            dp[3] = 7; (dp[3])
            dp[4] = 4; (dp[4])
            dp[5] = 2; (dp[5])
            dp[6] = 0; (dp[6])
            dp[7] = 8; (dp[7])
            **
            dp[8] = 10; (dp[2] + dp[6])
            dp[9] = 18; (dp[2] + dp[7])
            dp[10] = 22; (dp[5] + dp[5])
            dp[11] = 20; (dp[5] + dp[6])
            dp[12] = 28; (dp[5] + dp[7])
            dp[13] = 68; (dp[6] + dp[7])
            dp[14] = 88; (dp[7] + dp[7])
            **
            dp[15] = 108; (dp[2] + dp[6] + dp[7])
            ...

        */
        while(tc-- > 0) {
            n = Integer.parseInt(br.readLine());
            // 최소
            minDp = new long[101];
            Arrays.fill(minDp, Long.MAX_VALUE);
            minDp[2] = 1;
            minDp[3] = 7;
            minDp[4] = 4;
            minDp[5] = 2;
            minDp[6] = 6;
            minDp[7] = 8;

            minDp[8] = 10;
            
            int[] arr = {1, 7, 4, 2, 0, 8};
            for (int i = 9; i <= 100; i++) {
                for (int j = 2; j <=7; j++) {
                    String line = ""+ minDp[i - j] + arr[j - 2];
                    minDp[i] = Math.min(minDp[i], Long.parseLong(line));
                }
            }

            /// 최대
            StringBuilder max = new StringBuilder();
            long a = n / 2;
            long b = n % 2;
            
            if (b == 1) {
                max.append("7");
            } else {
                max.append("1");
            }
            
            for (int i = 1; i < a; i++) {
                max.append("1");
            }
            
            System.out.println(minDp[n]+" "+max.toString());
        }
    }
}
