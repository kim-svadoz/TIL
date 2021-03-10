import java.io.*;

public class p2133 {
    static int N;
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        System.out.println(N % 2 == 0 ? recur(N) : 0);
    }
    
    static int recur(int n) {
        if (n == 0) return 1;
        if (n == 2) dp[2] = 3;
        else if (dp[n] == 0) {
            for (int i = 2; i <= n; i += 2) {
                int standard = i == 2 ? 3 : 2;
                dp[n] += standard * recur(n - i);
            }
        }
        return dp[n];
    }
}