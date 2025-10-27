import java.io.*;
import java.util.*;
public class p1654 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, K;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[K];
        
        for (int i = 0 ; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long max = arr[K - 1];
        long min = 1;
        long middle = 0;
        
        // 이분탐색 시작
        while (max >= min) {
            middle = (max + min) / 2;
            long cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                cnt += arr[i] / middle;
            }
            // ex. 11개를 넘어간다면
            if (cnt >= N) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
        }
        System.out.println(max);
	}
}