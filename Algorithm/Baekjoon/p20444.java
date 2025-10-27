/**
 * BOJ 20444 색종이와 가위 : Gold 5
 * 이분탐색
 */
import java.io.*;
import java.util.*;

public class p20444 {
    static long n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

        /**
         * 입력이 long으로 들어오고,
         * 시간제한도 그만큼 빡세보인다.
         * 따라서 logN의 이분탐섹을 사용해보자.
         */

        long lo = 0;
        long hi = n / 2;
        while (lo <= hi) {
            long row = (lo + hi) / 2; // 가로로 싹둑 횟수
            long col = n - row; // 세로로 싹둑 횟수

            long total = cut(row, col);
            if (total == k) {
                System.out.println("YES");
                return;
            } else if (total > k) { // 너무 많이 잘랐기 때문에 가로로 자르는 횟수를 한번 줄여준다.
                hi = row - 1;
            } else {
                lo = row + 1;
            }
        }
        System.out.println("NO");
    }

    // 가로, 세로로 잘랐을 때 반환되는 색종이의 개수
    static long cut(long row, long col) {
        return (row + 1) * (col + 1);
    }
}