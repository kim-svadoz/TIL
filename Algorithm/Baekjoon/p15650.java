/*
    15649 문제와 다른 점은
    고른 수열은 오름차순이어야 한다는 것.
    => start를 고려해야한다.
*/
import java.io.*;
import java.util.*;
public class p15650 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        boolean[] visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        
        dfs(arr, visit, 0, 0);

        System.out.println(sb.toString());
    }
    static void dfs(int[] arr, boolean[] visit, int start, int depth) {
        if (depth == m) {
            print(arr, visit);
            return;
        }
        for (int i = start; i < n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                dfs(arr, visit, i + 1, depth + 1);
                visit[i] = false;
            }
        }
    }
    
    static void print(int[] arr, boolean[] visit) {
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                sb.append(arr[i]).append(" ");
            }
        }
        sb.append("\n");
    }
}