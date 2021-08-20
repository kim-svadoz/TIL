/*
    두 수의 합
    이분탐색
*/
import java.io.*;
import java.util.*;

public class p3273 {
    static int n, x;
    static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());

        Arrays.sort(a, 1, n + 1);

        int ans = 0;
        for (int i = 1; i <= n - 1; i++) {
            if(solution(a, i + 1, n, x - a[i])) ans++;
        }
        System.out.println(ans);
    }

    static boolean solution(int[] arr, int l, int r, int target) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) return true;

            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
