/**
 * BOJ 2824 최대공약수
 * 수학, 정수론, 구현
 */
import java.io.*;
import java.util.*;

public class p2824 {
    static final int MOD = 1000000000;
    static int n, m, a, b;
    static int[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 1;
        boolean flag = false;
        // 1000 * 1000
        for (int i = 0; i < n; i++) {
            if (arr1[i] == 1) continue;
            for (int j = 0; j < m; j++) {
                if (arr2[j] == 1) continue;
                long gcd = gcd(arr1[i], arr2[j]);
                answer *= gcd;
                if (answer >= MOD) flag = true;
                answer %= MOD;
                // 예를들어 한번 2로 나누었으면 그 이후에는 2로 나누지 않고 중복을 제거하기 위해
                arr1[i] /= gcd;
                arr2[j] /= gcd;
            }
        }

        String str = String.valueOf(answer);
        StringBuilder sb = new StringBuilder();
        if (str.length() < 9 && flag == true) {
            while (sb.length() + str.length() < 9) {
                sb.append("0");
            }
        }
        sb.append(answer);
        System.out.println(sb.toString());
    }

    // 유클리드 호제법 : O(logN)
    static int gcd(int p, int q) {
        if (q == 0) return p;

        return gcd(q, p % q);
    }
}
