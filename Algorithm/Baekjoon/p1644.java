import java.io.*;
import java.util.*;

public class p1644 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        boolean prime[] = new boolean[n + 1];
        List<Integer> primelist = new ArrayList<>();
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (!prime[i]) {
                primelist.add(i);
            }
        }

        // 연속된 소수의 합으로 n을 만들 수 있는가? ex. 41
        // 2 3 5 7 11 13 17 19
        int lo = 0, hi = 0, sum = 0, cnt = 0;
        while (true) {
            if (sum >= n) {
                sum -= primelist.get(lo++);
            } else if (hi == primelist.size()) {
                break;
            } else {
                sum += primelist.get(hi++);
            }

            if (n == sum) cnt++;
        }
        System.out.println(cnt);
    }
}
