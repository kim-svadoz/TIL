/*
    케이크 자르기2
    DP + DFS
    카드게임과 유사하다.

    dp[l][r] : 현재까지 가져간 가장 왼쪽 위치 l, 가장 오른쪽 위치 r에서의 최대값

    i양이 먹을 차례에서는 (l-1)번쨰와 (r+1)번째 중 더 큰 값을 가져가면된다.
    j군이 먹을 차례에서는 (l-1)번째와 (r+1)번째를 가져갔을때 최종적으로 더 큰 결과를 갖게 되는 쪽을 가지면 된다.
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
            answer = Math.max(answer, arr[i] + go(0, Minus(i), Plus(i)));
        }
        sb.append(answer);
        System.out.println(sb.toString());
    }
    static long go(int turn, int l, int r) {
        if (l == r) {
            if (turn == 1) return arr[l];
            else return 0;
        }

        long ret = dp[l][r];
        if (ret != -1) return ret;
        else { ret = 0; }

        if  (turn == 1) {
            ret += Math.max(arr[l] + go(0, Minus(l), r), arr[r] + go(0, l, Plus(r)));
        } else {
            if (arr[l] > arr[r]) {
                ret += go(1, Minus(l), r);
            } else {
                ret += go(1, l, Plus(r));
            }
        }
        dp[l][r] = ret;
        return ret;
    }
    
    static int Plus(int x) {
        return (x + 1) % n;
    }
    
    static int Minus(int x) {
        return (x + n - 1) % n;
    }
    
}

/*


import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static long[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
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

    // I양이 먹을 차례인데 I양이 먹을 수 있는값
    // => I양이 먹을 차례일 때 J군이 많이 먹을 수 있는 값
    static long ioi(int l, int r) {
        // I양이 먹으면 J군이 먹을 수 있는건 0이므로
        if (Plus(r) == l) return 0;
        if (arr[Minus(l)] > arr[Plus(r)]) return joi(Minus(l), r);
        return joi(l, Plus(r));
    }
    
    // J군이 먹을 차례인데 왼쪽은 l번 조각과 오른쪽 r번조각이 있을 때 J군이 가장 많이 먹는 경우
    static long joi(int l, int r) {
        if (dp[l][r] != -1) return dp[l][r];
        if (Plus(r) == l) return dp[l][r] = 0;
        long t1 = arr[Minus(l)] + ioi(Minus(l), r);
        long t2 = arr[Plus(r)] + ioi(l, Plus(r));
        if (t1 > t2) return dp[l][r] = t1;
        return dp[l][r] = t2;
    }
    
    static int Plus(int x) {
        return (x + 1) % n;
    }
    
    static int Minus(int x) {
        return (x + n - 1) % n;
    }
    
}

*/