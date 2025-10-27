import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p20543 {
    static int n, m;
    static int h[][], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];
        for (int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= n; c++) {
                h[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                dp[r][c] = dp[r -1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + h[r][c];
            }
        }

        int target = (m * m);
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                for (int x = r; x < n; x++) {
                    for (int y = c; y < m; y++) {
                        long result = Math.abs(prefixSum(r, c, x, y, dp));
                        System.out.println(result);
                        if (result % target == 0) {
                            // 가능 성 있다.
                        }
                    }
                }
            }
        }
    }
    static long prefixSum(int x1, int y1, int x2, int y2, int[][] psum) {
        return psum[x2][y2] - psum[x1 - 1][y2] - psum[x2][y1 - 1] + psum[x1 - 1][y1 - 1];
    }
}
