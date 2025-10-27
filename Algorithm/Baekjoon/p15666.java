/*
    이전 문제와 다른 점은
    비 내림 차순이며 자신 포함 중복 가능. -> visit 불필요

    LinkedHashSet을 쓰지 않고, 중복을 제거하는 solution
*/
import java.io.*;
import java.util.*;
public class p15666 {
    static int n, m;
    static int[] arr, result;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        result = new int[m];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(1, 0);
        System.out.println(sb.toString());
    }
    static void dfs(int start, int depth) {
        if (depth == m) {
            for (int p : result) {
                sb.append(p).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= n; i++) {
            if (arr[i - 1] == arr[i]) continue; // 이 부분이 중복 수열을 없애는 부분이다.
            result[depth] = arr[i];
            dfs(i, depth + 1);
        }
    }
}