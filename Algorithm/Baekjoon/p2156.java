import java.io.*;
public class p2156 {
    static int N, arr[];
    static Integer dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new Integer[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        dp[1] = arr[1];
        
        if(N > 1) {
            dp[2] = arr[1] + arr[2];
        }
        System.out.println(find(N));
    }

    static int find(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(Math.max(find(n - 2), find(n - 3) + arr[n - 1]) + arr[n], find(n - 1));
        }
        return dp[n];
    }
}