/*
	우유 축제
	DP
	dp[n][milk] : n번째 가게까지 milk으유를 마셨을 때 먹을 수 있는 우유의 최대 개수
	dp[n][0] = (dp[n-1][2] + 1 or dp[n-1][0])
	-> 딸기우유 = 이전 가게까지 바나나 우유를 마셨을 때의 최대 개수
	
	dp[n][1] = (dp[n-1][0] + 1 or dp[n-1][1])
	-> 딸기우유 = 이전 가게까지 바나나 우유를 마셨을 때의 최대 개수
	
	dp[n][2] = (dp[n-1][1] + 1 or dp[n-1][2])
	-> 딸기우유 = 이전 가게까지 바나나 우유를 마셨을 때의 최대 개수
	
	milk == 1 && dp[i][2] < dp[i][0]
	-> 아무리 이전 순서인 우유가 있어도 현재 딸기우유를 하나 먹을 때가 바나나 우유를 먹은것보다 항상 값이 커야 순서가 맞는 것이다.
	
	milk == 2 && dp[i][0] < dp[i][1]
	-> 아무리 이전 순서인 우유가 있어도 바나나 우유를 먹을 때가 딸기우유를 먹을때보다 항상 값이 커야 순서가 맞는 것이다.
*/
import java.io.*;
import java.util.*;
public class p14720 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n][3];
		
        // 가장 먼저 딸기우유를 마시므로 arr이 0일 경우에만 dp를 1로 만들어준다.
        if (arr[0] == 0) {
            dp[0][0] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            int milk = arr[i];
            
            dp[i][0] = milk == 0 ? dp[i-1][2] + 1 : dp[i-1][0];
            dp[i][1] = milk == 1 && dp[i][2] < dp[i][0] ? dp[i-1][0] + 1 : dp[i-1][1];
            dp[i][2] = milk == 2 && dp[i][0] < dp[i][1] ? dp[i-1][1] + 1 : dp[i-1][2];
        }
        System.out.println(Math.max(dp[n-1][0] , Math.max(dp[n-1][1], dp[n-1][2])));
    }
}