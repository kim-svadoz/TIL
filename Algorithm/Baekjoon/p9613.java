import java.io.*;
import java.util.*;

public class p9613 {
    static BufferedReader br;
    static StringTokenizer st;
    static int t, n;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            System.out.println(gcd_all(arr));
        }

    }

    static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    static long gcd_all(int[] arr) {
        long sum = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                sum += gcd(arr[i], arr[j]);
            }
        }

        return sum;
    }
}
