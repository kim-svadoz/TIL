/*
    케이크자르기
    이분탐색
    조건체크하는 부분을 함수로 빼는 것이 시간복잡도가 더 좋다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p17179 {
    static int n, m, cakeLen;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cakeLen = Integer.parseInt(st.nextToken());
        arr = new int[m + 1];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[m] = cakeLen;

        while (n-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int answer = 0;
            // 최소 1번 자르는 것, 최대 m번 자르는것.
            // int lo = 1, hi = m;

            // 최소 길이 10, 최대 길이 arr[m-1];
            int lo = 0, hi = arr[m];
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                // 1. k번 잘라서 길이 35(가운데)가 되도록 케이크를 자를 수 있는가? 없는가? 의 결정 문제
                if (cut(mid, k)) {
                    lo = mid + 1;
                    answer = Math.max(answer, mid);
                } else {
                    hi = mid - 1;
                }
            }
            System.out.println(answer);
        }
    }
    // count의 횟수로 케이크를 잘라서 조각 길이 mid가 나올 수 있는가?
    static boolean cut(int mid, int count) {
        int prev = 0;
        int cnt = count;
        for (int i = 0; i <= m; i++) {
            if (arr[i] - prev >= mid) {
                cnt--;
                prev = arr[i];
            }
        }
        return cnt < 0 ? true : false;
    }
}

/* 시간복잡도가 조금 더 안좋다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p17179 {
    static int n, m, cakeLen;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cakeLen = Integer.parseInt(st.nextToken());
        arr = new int[m + 1];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[m] = cakeLen;

        while (n-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int answer = 0;
            // 최소 1번 자르는 것, 최대 m번 자르는것.
            // int lo = 1, hi = m;

            // 최소 길이 10, 최대 길이 arr[m-1];
            int lo = 0, hi = arr[m];
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                // 1. k번 잘라서 길이 35(가운데)가 되도록 케이크를 자를 수 있는가? 없는가? 의 결정 문제
                int cur = 0, cut = 0;
                for (int i = 0; i <= m; i++) {
                    if (arr[i] - cur >= mid) {
                        cur = arr[i];
                        cut++;
                    }
                }

                // 2. 조건에 따라 최적화하는 최적화 문제
                if (cut > k) {
                    lo = mid + 1;
                    answer = Math.max(answer, mid);
                } else {
                    hi = mid - 1;
                }
            }
            System.out.println(answer);
        }
    }
    
}

*/