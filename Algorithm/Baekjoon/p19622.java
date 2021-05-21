/*
	회의실 배정 3
	DP
	dp[i] : i번째 회의까지 진행했을 때의 최대 인원
*/
import java.io.*;
import java.util.*;
public class p19622 {
    static class Meeting {
        int s, e, total;
        public Meeting(int s, int e, int total) {
            this.s = s;
            this.e = e;
            this.total = total;
        }
    }
    static int n;
    static Meeting[] meet;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        meet = new Meeting[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int total = Integer.parseInt(st.nextToken());

            meet[i] = new Meeting(start, end, total);
        }
        dp = new int[n];
        
        int answer = -123456789;
        dp[0] = meet[0].total;
        if (n >= 1) {
            answer = dp[0];
        }
        if (n >= 2) {
            dp[1] = meet[1].total;
        }
        
        
        for (int i = 2; i < n; i++) {
            // i번째에서의 최대인원은 i번째 회의실 총원 + 그 전까지 회의를 진행한 인원 수
            dp[i] = meet[i].total + answer;
            answer = Math.max(answer, dp[i - 1]);
        }
        answer = Math.max(answer, dp[n - 1]);
        System.out.println(answer);
    }

}