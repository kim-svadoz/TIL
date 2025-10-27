import java.io.*;
import java.util.*;

public class p10819 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, arr[], max;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        System.out.println(max);
    }

    static void swap(int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    // 순열
    static void perm(int depth) {
        if (depth == n) {
            sum(n);
            return;
        }
        for (int i = depth; i < n; i++) {
            swap(i, depth);
            perm(depth + 1);
            swap(i, depth);
        }
    }

    static void sum(int k) {
        int sum = 0;
        for (int i = 2; i <= k; i++) {
            sum += Math.abs(arr[i - 2] - arr[i - 1]);
        }
        if (sum > max) {
            max = sum;
        }
    }

    static int abs(int a, int b) {
        return Math.abs(a - b);
    }
}
