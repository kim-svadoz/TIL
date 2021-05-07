/*
    케이크 자르기2
    DP
    카드게임과 유사하다.
*/
import java.io.*;
import java.util.*;
public class p10714 {
    static int n;
    static long[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        dp = new long[2020][2020];
        for (int i = 0; i < 2020; i++) {
            Arrays.fill(dp[i], -1);
        }
        long answer = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, arr[i] + ioi(i, i));
        }
        sb.append(answer);
        System.out.println(sb.toString());
    }
    static long ioi(int l, int r) {
        if (Plus(r) == l) return 0;
        if (arr[Minus(l)] > arr[Plus(r)]) return joi(Minus(l), r);
        return joi(l, Plus(r));
    }
    
    static long joi(int l, int r) {
        long ret = dp[l][r];
        if (ret != -1) return ret;
        if (Plus(r) == 1) return ret = 0;
        long t1 = arr[Minus(l)] + ioi(Minus(l), r);
        long t2 = arr[Plus(r)] + ioi(l, Plus(r));
        if (t1 > t2) return t1;
        return t2;
    }
    
    static int Plus(int x) {
        return (x + 1) % n;
    }
    
    static int Minus(int x) {
        return (x + n - 1) % n;
    }
    
}