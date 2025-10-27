import java.io.*;
public class p2193 {
    static int n;
    static long cnt[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cnt = new long[n+1][2];
        cnt[1][0] = 0;
        cnt[1][1] = 1;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    cnt[i][j] = cnt[i-1][0] + cnt[i-1][1];
                } else {
                    cnt[i][j] = cnt[i-1][0];
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 2; i++) {
            sum += cnt[n][i];
        }
        System.out.println(sum);
    }
}