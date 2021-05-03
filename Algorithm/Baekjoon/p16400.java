/*
    소수화폐
    소수구하기 + DP(동전 바꾸기)
*/
import java.io.*;
import java.util.*;
public class p16400 {
    static final int MOD = 123456789;
    static int n, cnt, size;
    static List<Integer> primeList;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        primeList = new ArrayList<>();
        boolean[] prime = new boolean[40001];
        // true : 소수가 아니다, false : 소수이다.
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i <= 40000; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= 40000; j += i) {
                    prime[j] = true;
                }
            }
        }
        
        for (int i = 2; i <= n; i++) {
            // 소수인 것들 중에서
            if (!prime[i]) {
                primeList.add(i);
            }
        }
        // dp[n] : 가격 n을 소수화폐로 살수 있는 경우의 수
        dp = new int[40001];
        dp[0] = 1;
        for (int i = 0; i < primeList.size(); i++) {
            int tmp = primeList.get(i);
            for (int j = tmp; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - tmp]) % MOD;
            }
        }
        System.out.println(dp[n]);
    }
}

/*  DP(동전바꾸기) 점화식 도출 과정

    tmp = 2
    dp[2] = dp[2] + dp[2-2]; // 0 + 1
    dp[3] = dp[3] + dp[3-2]; // 0 + 0
    dp[4] = dp[4] + dp[4-2]; // 0 + 1
    dp[5] = dp[5] + dp[5-2]; // 0 + 0
    dp[6] = dp[6] + dp[6-2]; // 0 + 1
    dp[7] = dp[7] + dp[7-2]; // 0 + 0
    dp[8] = dp[8] + dp[8-2]; // 0 + 1
    ...
    dp[n] = dp[n] + dp[n-2]; // ?

    tmp = 3
    dp[3] = dp[3] + dp[3-3]; // 0 + 1
    dp[4] = dp[4] + dp[4-3]; // 1 + 0
    dp[5] = dp[5] + dp[5-3]; // 0 + 1
    dp[6] = dp[6] + dp[6-3]; // 1 + 1
    dp[7] = dp[7] + dp[7-3]; // 0 + 1
    dp[8] = dp[8] + dp[8-3]; // 1 + 1
    ...
    dp[n] = dp[n] + dp[n-3];  // ?

    tmp = 5
    dp[5] = dp[5] + dp[5-5]; // 1 + 1
    dp[6] = dp[6] + dp[6-5]; // 2 + 0
    dp[7] = dp[7] + dp[7-5]; // 1 + 1
    dp[8] = dp[8] + dp[8-5]; // 2 + 1
    ...

*/