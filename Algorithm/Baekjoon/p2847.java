/**
 * BOJ 2847 게임을 만든 동준이
 * 구현
 */
import java.io.*;
import java.util.*;

public class p2847 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine()); // i level 클리어시 얻는 점수
        }

        /**
         * 1만큼 감소시키는 것이 1번의 행위
         * 레벨이 높은 순으로 확인해보자?
         */
        int answer = 0;
        for (int i = n - 1; i >= 1; i--) {
            if (arr[i] >= arr[i + 1]) {
                answer += arr[i] - arr[i + 1] + 1;
                arr[i] = arr[i + 1] - 1;
            }
        }
        System.out.println(answer);
    }
}
