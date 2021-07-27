import java.io.*;
import java.util.*;

public class p11066 {
    static int T, N;
    static int[] arr;
    static int[][] d, sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            // input
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            sum = new int[N + 1][N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1;  i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // process
            pro();
        }
    }

    static void pro() {
        preprocess();
        d = new int[N + 1][N + 1];

        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                d[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k + 1][j] + sum[i][j]);
                }
            }
        }
        System.out.println(d[1][N]);
    }

    static void preprocess() {
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                sum[i][j] = sum[i][j - 1] + arr[j];
            }
        }
    }
}
