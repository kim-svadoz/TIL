import java.io.*;
import java.util.*;
public class p2110 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, C, arr[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long lo = 1;
        long hi = arr[N - 1] - arr[0];
        long d = 0;
        // 1 2 4 8 9
        while(hi >= lo) {
            long mid = (lo + hi) / 2;
            long start = arr[0];
            long cnt = 1;
            
            // 집집마다 검색
            for(int i = 1; i < N; i++) {
                d = arr[i] - start;
                if (mid <= d) {
                    ++cnt;
                    start = arr[i];
                }
            }

            if (cnt >= C) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(hi);
    }
}
