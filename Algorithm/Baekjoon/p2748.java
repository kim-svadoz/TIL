/*
    피보나치 수2
    n이 90까지이다.
    따라서 int타입의 범위를 넘어가므로 long타입을 써줘야한다!
*/
import java.util.*;
import java.io.*;

public class p2748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= 100; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n]);
    }
}