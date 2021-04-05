import java.io.*;
import java.util.*;

public class p20438 {
    static int n, k, q, m, s, e, add;
    static boolean[] sleep, chk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 학생 수
        k = Integer.parseInt(st.nextToken()); // 조는사람
        q = Integer.parseInt(st.nextToken()); // 출석코드 보낼
        m = Integer.parseInt(st.nextToken()); // 주어질 구간

        // 구간 합을 저장할 배열
        int[] dp = new int[n + 3];

        for (int i = 3; i <= n + 2; i++) {
            dp[i] = i;
        }

        sleep = new boolean[5005];
        chk = new boolean[5005];

        // 7
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            if (!st.hasMoreTokens()) break;
            int x = Integer.parseInt(st.nextToken());
            sleep[x] = true;
        }

        // 3 5 7 , 6 10 
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            if (!st.hasMoreTokens()) break;
            int x = Integer.parseInt(st.nextToken());
            if(sleep[x]) continue;
            add = x;
            while (x <= n + 2) {
                if (sleep[x]) {
                    x += add;
                    continue;
                }
                chk[x] = true;
                x += add;
            }
        }

        for (int i = 3; i <= n + 2; i++) {
            int a;
            if(!chk[i]) {
                a = 1;
            } else {
                a = 0;
            }
            dp[i] = dp[i - 1] + a;
        }

        // 3 ~ 12
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            System.out.println(dp[e] - dp[s - 1]);
        }

    }
}
