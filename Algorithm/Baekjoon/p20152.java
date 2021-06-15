/*
	Game Addiction
	DP
	
	(h, h)에서 (n, n)으로 가는 최단 경로 개수 중, y <= x인 경로의 개수를 구한다.
	시작점(H, H)를 왼쪽 아래, 목표(N, N)지점을 오른쪽 위라고 가정하고 한다.
	그러면 dp는 (왼쪽에서 오는것 + 아래에서 오는 것)이 현재 지점의 경로 개수가 된다.
	
	여기서 dp[i][j] 는 i가 y좌표, j가 x좌표이다.

    ** 나올 수 있는 값은 long타입으로 타입 조심하자..
*/
import java.io.*;
import java.util.*;
public class p20152 {
    static long[][] dp;
    static int h, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new long[33][33];
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        long answer = 0;
        if (h == n) {
            answer = 1;
        } else {
            if (h > n) {
                int tmp = h;
                h = n;
                n = tmp;
            }
            
            for (int i = h; i <= n; i++) {
                dp[h][i] = 1;
            }
            
            for (int i = h + 1; i <= n; i++) {
                for (int j = h + 1; j <= n; j++) {
                    // x < y(실제 좌표 법)인 경우는 침투되었으므로 i > j 일때는 continue 해줘야 함
                    if (i <= j) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            answer = dp[n][n];
        }
        
        System.out.println(answer);
    }
}