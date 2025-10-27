/*
    빚
    슬라이딩 윈도우, 규칙찾기, 구현

    처음엔 조합을 사용해서 모든 경우의 수를 뽑아서 계산했는데, TLE가 났다.
    시간을 줄이기 위해 고민한 결과
    각 배열의 원소의 차이가 적은 구간이 가장 최소값을 뽑아 낼 수 있는 구간임을 찾아내어 쉽게 구현하였다.
*/
import java.io.*;
import java.util.*;
public class p10427 {
    static int t, n;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }
    static long solve() {
        long ret = 0;
        Arrays.sort(arr);
        for (int i = 2; i <= n; i++) {
            ret += func(i);
        }
        return ret;
    }
    
    // 슬라이딩 윈도우를 이용해 최소값 찾아내기 1 3 4 5 8 
    static long func(int dist) {
        long min = Integer.MAX_VALUE;
        for (int i = 1; i <= n - dist + 1; i++) {
            min = Math.min(min, calc(i, i + dist - 1));
        }
        return min;
    }
    
    static long calc(int lo, int hi) {
        long ret = 0;
        long big = arr[hi] * (hi - lo + 1);
        long small = 0;
        for (int i = lo; i <= hi; i++) {
            small += arr[i];
        }
        ret = big - small;
        return ret;
    }
}