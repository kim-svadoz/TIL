/*
    15650 문제와 다른 점은
    고른 수열은 같은 수를 여러번 골라도 된다는 것.
    -> 중복 가능!! -> visit 배열을 쓰지 않는다.

    15649번 문제처럼 오름차순이지 않아도 되므로 시작점 start를 고려하지 않아도 된다.
*/
import java.io.*;
import java.util.*;
public class p15651 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        boolean[] visit = new boolean[n];
       
        dfs(arr, visit, 0);

        System.out.println(sb.toString());
    }
    static void dfs(int[] arr, boolean[] visit, int depth) {
        if (depth == m) {
            print(arr, visit);
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[depth] = i + 1;
            dfs(arr, visit, depth + 1);
        }
    }
    
    static void print(int[] arr, boolean[] visit) {
        for (int i = 0; i < m; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }
}