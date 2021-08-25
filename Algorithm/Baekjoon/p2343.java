/*
    기타 레슨
    이분탐색
*/
import java.io.*;
import java.util.*;

public class p2343 {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int l = 0;
        int r = 0;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            r += arr[i];
        }
        // l 은 가장 큰 레슨 하나의 값
        for (int i = 0; i < n; i++) {
            if (l < arr[i]) {
                l = arr[i];
            }
        }
        while (l <= r) {
            int mid = (l + r) / 2;
            if (check(mid)) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(answer);
    }
    // num의 크기로 m개의 블루레이를 나눌 수 있는 가 없는가
    static boolean check(int num) {
        // m개로 나누는 기준점을 잡아야 한다.
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + arr[i] > num) {
                cnt++;
                sum = arr[i];
            } else {
                sum += arr[i];
            }
        }
        return (cnt >= m) ? false : true;
    }
}
