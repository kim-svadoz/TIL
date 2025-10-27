import java.io.*;
import java.util.*;

public class p11047 {
    static int N, K, arr[];
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int min = 0;
        for (int i = N; i > 0; --i) {
            min += (K / arr[i]);
            K = (K % arr[i]);
            if(K == 0) break;
        }
        System.out.println(min);
    }
}
