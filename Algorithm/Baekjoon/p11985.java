/*
	오렌지 출하
	
	dp[i] : i번 오렌지까지 포장하는 최소 비용
    i번째 오렌지를 포함하는 현재 박스의 사이즈를 size라고 할 때
    cost1 = (size 1일 때 박스 비용 + dp[i - 1])
    cost2 = (size 2일 때 박스 비용 + dp[i - 2])
    ...
    costM = (size M일 때 박스 비용 + dp[i - M]);
    즉, dp[i] 는 (cost1 ~ costM 중에 최솟값) 이다.
*/
import java.io.*;
import java.util.*;
public class p11985 {
    static int n, m, k;
    static long[] arr;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[20020];
        dp = new long[20020];
        
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // i번 오렌지의 크기 arr[i]
            arr[i] = Long.parseLong(br.readLine());
            // i번 오렌지가 가질 수 있는 최대 비용으로 셋팅
            dp[i] = i * k;
        }

        for (int i = 1; i <= n; i++) {
            long min = arr[i];
            long max = arr[i];
            // 변수 j : 오렌지를 담을 개수
            for (int j = i; j >= Math.max(1, i - m + 1); j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                long cost = k + (i - j + 1) * (max - min);
                dp[i] = Math.min(dp[i], dp[j - 1] + cost);
            }
        }
        System.out.println(dp[n]);
    }
}