/*
    이전 문제와 다른 점은
    고른 수열이 오름차순이여야 하므로 start를 찍어줘야한다. -> 중복 불가 조합
    
*/
import java.io.*;
import java.util.*;
public class p15655 {
    static int n, m;
    static int[] arr;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visit = new boolean[n];
               
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        dfs(0, 0);
        
        System.out.println(sb.toString());
    }
    static void dfs(int start, int depth) {
        if (depth == m) {
            print();
            return;
        }
        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i + 1, depth + 1);
                visit[i] = false;
            }
        }
    }
    
    static void print() {
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
	            sb.append(arr[i]).append(" ");                
            }
        }
        sb.append("\n");
    }
}