import java.io.*;
import java.util.*;
public class p1912 {
    static int N, max, arr[];
    static Integer dp[];
    static BufferedReader br;
    static StringTokenizer st;
	public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        dp = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        max = arr[0];
        
        recur(N - 1);
        
        System.out.println(max);
    }
    static int recur(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(arr[n], recur(n-1) + arr[n]);
            
            max = Math.max(dp[n], max);
        }
        return dp[n];
    }
}