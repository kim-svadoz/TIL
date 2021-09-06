/*
    홀수 홀릭 호석
    완전탐색(Gold 5)
*/
import java.io.*;
import java.util.*;

public class p20164 {
    static int n;
    static int min, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        max = 0;
        func(n, getOddCnt(n));
        System.out.println(min+" "+max);
    }

    static void func(int num, int cnt) {
        // 한 자리
        if (num <= 9) {
            min = Math.min(min, cnt);
            max = Math.max(max, cnt);
            return;
        }

        // 두 자리
        if (num <= 99) {
            int next = (num / 10) + (num % 10);
            func(next, getOddCnt(next) + cnt);
        }

        // 세 자리
        String s = Integer.toString(num);

        for (int i = 0; i < s.length() - 2; i++) {
            for (int j = i + 1; j < s.length() - 1; j++) {
                String x1 = s.substring(0, i + 1); // s[0..i]
                String x2 = s.substring(i + 1, j + 1); // s[i+1..j]
                String x3 = s.substring(j + 1, s.length()); // s[j+1..end]

                int nx = Integer.parseInt(x1) + Integer.parseInt(x2) + Integer.parseInt(x3);
                func(nx, getOddCnt(nx) + cnt);
            }
        }
    }

    // x라는 수 안에 홀수가 몇 개 있는지
    static int getOddCnt(int x) {
        int ret = 0;
        while (x > 0) {
            int digit = x % 10;
            if (digit % 2 == 1) ret++;
            x /= 10;
        }
        return ret;
    }
}
