/**
 * BOJ 16564 히오스 프로게이머 : Silver 1
 * 이분탐색
 */
import java.io.*;
import java.util.*;

public class p16564 {
    static int n, k, answer;
    static int[] lev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;

        lev = new int[n];
        for (int i = 0; i < n; i++) {
            lev[i] = Integer.parseInt(br.readLine());
        }

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (min > lev[i]) {
                min = lev[i];
                continue;
            }
            if (max < lev[i]) {
                max = lev[i];
            }
        }

        int lo = min;
        int hi = max + k;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (check(mid)) {
                answer = Math.max(answer, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(answer);
    }

    /**
     * 
     * @param num 팀의 최소 목표 레벨
     * @return 달성가능 여부
     */
    static boolean check(int num) {
        // 사용할 수 있는 레벨업 한계는 k

        int useLevel = 0;
        // 초기 num=20 :: 주어진 k로 최소 20을 만들 수 있어?
        for (int i = 0; i < n; i++) {
            if (num <= lev[i]) continue;

            useLevel += num - lev[i];
            if (useLevel > k) return false;
        }

        return true;
    }
}