/*
    구간합구하기4
    prefix sum
*/
import java.io.*;
import java.util.*;
public class p11659 {
    static int n, m;
    static int[] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        dp = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i-1] + arr[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int ret = prefixSum(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(ret).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int prefixSum(int a, int b) {
        return dp[b] - dp[a-1];
    }
}