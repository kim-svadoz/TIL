import java.io.*;
import java.util.*;

public class p11054 {
    static int N, arr[];
    static Integer dp_LIS[];
    static Integer dp_LDS[];
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp_LIS = new Integer[N];
        dp_LDS = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            LIS(i);
            LDS(i);
        }

        int max = -1;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp_LIS[i] + dp_LDS[i]);
        }
        System.out.println(max - 1);
    }

    static int LIS(int n) {
        if (dp_LIS[n] == null) {
            dp_LIS[n] = 1;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[n] > arr[i]) {
                    dp_LIS[n] = Math.max(dp_LIS[n], LIS(i) + 1);
                }
            }
        }
        return dp_LIS[n];
    }

    static int LDS(int n) {
        if (dp_LDS[n] == null) {
            dp_LDS[n] = 1;
            for (int i = n + 1; i < dp_LDS.length; i++) {
                if (arr[n] > arr[i]) {
                    dp_LDS[n] = Math.max(dp_LDS[n], LDS(i) + 1);
                }
            }
        }
        return dp_LDS[n];
    }
}
