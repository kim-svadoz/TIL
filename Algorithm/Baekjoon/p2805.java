import java.io.*;
import java.util.*;

public class p2805 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, arr[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);

        long max = arr[N - 1]; // 20
        long min = 0; // 1
        long sum = 0;
        while (max >= min) {
            long middle = (max + min) / 2;

            sum = 0; // 가져갈 수 있는 나무의 높이
            for (int i = 0 ; i < N; i++) {
                if (arr[i] > middle) {
                    sum += (arr[i] - middle);
                }
            }
            if (sum >= M) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
        }
        System.out.println(max);
    }
}
