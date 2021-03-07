import java.io.*;
public class p9095 {
    public static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int dp[] = new int[n+2];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            for (int j = 3; j <= n; j++) {
                dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
            }
            System.out.println(dp[n]);
        }
    }
}