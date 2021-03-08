import java.io.*;
import java.util.StringTokenizer;
public class p11055 {
    static int N, arr[];
    static Integer dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new Integer[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = arr[1];
        
        for (int i = 1; i <= N; i++) {
            recur(i);
        }

        int max = dp[1];        
        for (int i = 2; i <= N; i++) {
            max = Math.max(max, recur(i));
        }
        System.out.println(max);
        
    }

    static int recur(int n) {
        if (dp[n] == null) {
            dp[n] = arr[n];
            for (int i = n-1; i >= 0; i--) {
                if (arr[i] < arr[n]) {
                    dp[n] = Math.max(dp[n], arr[n]+recur(i));
                }
            }
        }
        return dp[n];
    }

}