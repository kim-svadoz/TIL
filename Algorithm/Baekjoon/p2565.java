import java.io.*;
import java.util.*;
public class p2565 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[][] wire = new int[n + 1][2];
        Integer[] dp = new Integer[n + 1];
        
        for (int i = 1; i < wire.length; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            wire[i][0] = u;
            wire[i][1] = v;
        }
        
        Arrays.sort(wire, new Comparator<int[]>() {
           public int compare(int[] o1, int[] o2) {
               return o1[0] - o2[0];
           }
        });
        
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            /*
		   * i번째 전봇대를 기준으로 이전의 전봇대들의 전선을 연결하기 위한 탐색
		   * 즉, i번째 전봇대에 연결된 B전봇대는 탐색할 j번째 전봇대에 연결된 B전봇대보다 값이 커야함 
		   */
            for (int j = 1; j < i; j++) {
                if (wire[i][1] > wire[j][1]) {
	                dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int max = 0;
        /*
		*  i번째 A전봇를 기준으로 연결가능한 개수 탐색
		*  및 최댓값 찾기
		*/
        for (int i = 1; i <= n; i++) {
            max = Math.max(dp[i], max);
        }
        // 최소 철거 개수 = 전체 개수 - 설치 가능한 전깃줄
        System.out.println(n - max);
    }
}