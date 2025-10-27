import java.io.*;
public class p2579 {
    static int n, arr[];
    static Integer dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new Integer[n + 1];
        dp[0] = arr[0];
        dp[1] = arr[1];

        if (n >= 2) {
            dp[2] = arr[1] + arr[2];
        }
        System.out.println(find(n));

    }
    static int find(int N) {
        if (dp[N] == null) {
            dp[N] = Math.max(find(N-2), find(N-3) + arr[N-1]) + arr[N];
        }
        return dp[N];
    }
}