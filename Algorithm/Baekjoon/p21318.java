/*
    정답이 안나와서 좀 해맸었는데, prefixSum()에서 정답을 리턴하는 곳에서
    return dp[r] - dp[l - 1] 이 아니라 dp[r] - dp[l] 이었다.
    이유는 문제를 고려하여 더 고민해보자.
*/
import java.io.*;
import java.util.*;
public class p21318 {
    static int n, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (arr[i] < arr[i - 1]) {
                dp[i]++;
            }
            //System.out.println("i:"+i+", dp[i]:"+dp[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int result = prefixSum(x, y, dp);
            sb.append(result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static int prefixSum(int l, int r, int[] dp) {
        return dp[r] - dp[l];
    }

}