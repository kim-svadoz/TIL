/*
    조합 + DP
    20까지의 숫자만 이용할 수 있고, +, - 연산만 할 수 있기 때문에
    dp[연산][21]로 메모이제이션 하면된다.
*/
import java.util.*;
import java.io.*;

public class p5557 {
    static int n;
    static int[] arr;
    static long[][] dp;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visit = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new long[100][21];
        
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= 20; j++) {
                dp[i][j] = -1;
            }
        }
        
        System.out.println(comb(1, arr[0]));
    }
    
    static long comb(int idx, int sum) {
        if (sum < 0 || sum > 20) {
            return 0;
        }
        
        if (idx == n - 1) {
            return (sum == arr[n - 1]) ? 1 : 0;
        }
        
        if (dp[idx][sum] != -1) {
            return dp[idx][sum];
        }
        dp[idx][sum] = 0;
        
        return dp[idx][sum] += comb(idx + 1, sum + arr[idx]) + comb(idx + 1, sum - arr[idx]);
    }
}

/* bottom-up

mport java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long[][] dp = new long[N - 1][21];
        dp[0][arr[0]] = 1;
        for(int i = 1; i < N - 1; i++){
            for(int j = 0; j < 21; j++){
                if(dp[i - 1][j] != 0) {
                    if(j + arr[i] <= 20) dp[i][j + arr[i]] += dp[i - 1][j]; 
                    if(j - arr[i] >= 0) dp[i][j - arr[i]] += dp[i - 1][j];
                }
            }
        }
        bw.write(dp[N - 2][arr[N - 1]] + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}

*/