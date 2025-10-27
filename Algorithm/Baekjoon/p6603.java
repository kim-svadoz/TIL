import java.io.*;
import java.util.*;

public class p6603 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            int arr[] = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            boolean visit[] = new boolean[k];

            // k개 중에서 6개 뽑기
            comb(arr, visit, 0, k, 6);
            System.out.println();
        }
    }
    static void comb(int[] arr, boolean[] visit, int start, int n, int depth) {
        if (depth == 0) {
            print(arr, visit, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visit[i] = true;
            comb(arr, visit, i + 1, n, depth - 1);
            visit[i] = false;
        }
    }

    static void print(int[] arr, boolean[] visit, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                sb.append(arr[i]).append(" ");
            }
        }
        sb.append("\n");
        System.out.print(sb.toString());
    }
}
