/*
    이전 문제와 다른 점은
    같은 수를 여러번 골라도 된다. : 가능한 모든 조합! (중복 가능 조합) -> start 필요 X , visit 필요 X
*/
import java.io.*;
import java.util.*;
public class p15656 {
    static int n, m;
    static int[] arr, result;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[m];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        // 1 7 8 9
        dfs(0);
        System.out.println(sb.toString());
    }
    static void dfs(int depth) {
        if (depth == m) {
            print();
            return;
        }
        for (int i = 0; i < n; i++) {
            result[depth] = arr[i];
            dfs(depth + 1);
        }
    }
    
    static void print() {
        for (int i = 0; i < m; i++) {
            sb.append(result[i]).append(" ");
        }
        sb.append("\n");
    }
}