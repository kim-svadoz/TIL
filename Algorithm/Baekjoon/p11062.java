/*
    카드게임
    DP
*/
import java.io.*;
import java.util.*;
public class p11062 {
    static int t, n;
    static int[] arr;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            /*
            	dp[k][l][r]
            	k=0 : 근우 , k=1 : 명우
            	l : 카드의 왼쪽 끝 idx
            	r : 카드의 오른쪽 끝 idx
            */
            dp = new int[2][n + 1][n + 1];
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k <= n; k++) {
                        dp[i][j][k] = -1;
                    }
                }
            }
            System.out.println(go(0, 1, n));
        }
    }
    // who : 누가 뽑을 차례인지 근우(0), 명우(1)
    static int go(int who, int left, int right) {
        if (dp[who][left][right] != -1) {
            return dp[who][left][right];
        }

        if (left >= right) {
            if (who == 0) return arr[left]; // 근우 차례
            else return 0; // 명우 차례
        }

        dp[who][left][right] = 0;

        // 근우 차례인 경우 근우의 점수가 제일 크도록
        // 내 턴이엇다면 실제로 점수 계산을 위한 코드가 있어야 한다.
        if (who == 0) {
            dp[who][left][right] = Math.max(go(1, left + 1, right) + arr[left], go(1, left, right - 1) + arr[right]);
        } else { 
            // 명우 차례인 경우 근우의 점수가 제일 낮도록
            // 여기서 arr을 더하지 않는 이유는 DP를 근우가 가질 수 있는 최대 점수로 설정 해놓앗기 때문에 명우가 먹는 건 더하지 않는다.
            // 상대 턴이엇다면, 상대도 최선의 전략으로 플레이 하기 때문에 양 쪽 플레이중 나의 점수를 최소로 하는 플레이로 할 것
            dp[who][left][right] = Math.min(go(0, left + 1, right), go(0, left, right - 1));
        }
        return dp[who][left][right];
    }
}