/*
    수열
    누적합 + 투포인터
*/
import java.io.*;
import java.util.*;

public class p2559 {
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        process();
    }

    static void process() {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int ans = sum;
        for (int i = k; i < n; i++) {
            sum = sum - arr[i - k] + arr[i];
            
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
