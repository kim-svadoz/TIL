// Arrays.sort(arr)을 하면 오답처리가 된다. 왜그런지 생각해보자
import java.io.*;
import java.util.*;

public class p2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 0, hi = 0, sum = 0, cnt = 0;
        while (true) {
            if (sum >=  m) {
                sum -= arr[lo++];
            } else if (hi >= n) {
                break;
            } else {
                sum += arr[hi++];
            }

            if (sum == m) cnt++;
        }
        System.out.println(cnt);
    }
}
