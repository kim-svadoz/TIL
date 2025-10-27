import java.io.*;
import java.util.StringTokenizer;
public class p11722 {
    static int N, max_len, arr[];
    static Integer dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            recur(i);
        }

        int max = dp[0];

        for (int i = 1; i < N; i++) {
            max = Math.max(max, recur(i));
        }
        System.out.println(max);
    }

    static int recur(int n) {
        if (dp[n] == null) {
            dp[n] = 1;

            for (int i = n-1; i >= 0; i--) {
                if (arr[i] > arr[n]) {
                    dp[n] = Math.max(dp[n], recur(i) + 1);
                }
            }
        }
        return dp[n];
    }

}