/*
    수찾기
    이분탐색

    HashSet을 이용하면 훨씬 빠르고 간단하게 찾을 수 있다.
    이분탐색 연습용!
*/
import java.io.*;
import java.util.*;

public class p1920 {
    static int n, m;
    static int[] a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if(solution(a, 0, n - 1, b[i])) ans.append(1).append('\n');
            else ans.append(0).append('\n');
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
