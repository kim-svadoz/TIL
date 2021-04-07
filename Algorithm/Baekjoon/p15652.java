/*
    15651 문제와 다른 점은
    고른 수열은 비내림차순이어야 한다.
	길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
	오름차순 != 비내림차순
	또한, 중복 여부를 고려하지 않는다.(중복가능) -> visit배열 사용 X
*/
import java.io.*;
import java.util.*;
public class p15652 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        boolean[] visit = new boolean[n];
               
        dfs(arr, visit, 0, 0);
        
        System.out.println(sb.toString());
    }
    static void dfs(int[] arr, boolean[] visit, int start, int depth) {
        if (depth == m) {
            print(arr, visit);
            return;
        }
        for (int i = start; i < n; i++) {
            arr[depth] = i + 1;
            dfs(arr, visit, i, depth + 1); // dfs를 돌 때 i + 1이 아닌 i로 도는 것이 핵심.
        }
    }
    
    static void print(int[] arr, boolean[] visit) {
        for (int i = 0; i < m; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }
}