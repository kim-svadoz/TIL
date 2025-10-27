/*
    예산
    이분탐색
*/
import java.io.*;
import java.util.*;

public class p2512 {
    static int n, total;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        total = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        /*
            초반에 틀린 이유
            l : arr[0]
            로 설정했다.

            l을 arr[0]이 아니라 0으로 설정해줘야된다.            
        */
        int ans = solution(0, arr[n - 1]);
        System.out.println(ans);
    }

    static int solution(int l, int r) {
        int ret = l;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (check(mid)) {
                ret = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ret;
    }

    static boolean check(int budget) {
        int tmp = total;
        for (int i = 0; i < n; i++) {
            if (budget > arr[i]) {
                tmp -= arr[i];
            } else {
                tmp -= budget;
            }
        }
        if (tmp < 0) return false;

        return true;
    }
}
