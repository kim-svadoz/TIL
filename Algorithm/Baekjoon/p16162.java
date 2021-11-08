/**
 * BOJ 16162 가희와 3단고음
 * 그리디
 */
import java.io.*;
import java.util.*;

public class p16162 {
    static int n, a, d;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        init();
        pro();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void pro() {
        int step = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != a) continue;
            step++;
            a += d;
        }
        System.out.println(step);
    }
}
