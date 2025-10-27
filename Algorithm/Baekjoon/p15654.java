/*
    이전 문제들과 다른 점은
    입력 배열이 따로 주어진다.
    중복가능.
    수는 중복가능이지만 고른 수열은 
    오름차순일 필요가 없으므로 start 는 쓰지 않는다.
    
    -> 따로 출력할 result 배열을 선언하여 dfs 돌리면 된다.
*/
import java.io.*;
import java.util.*;
public class p15654 {
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
        visit = new boolean[n];
        result = new int[m];
               
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        dfs(0);
        
        System.out.println(sb.toString());
    }
    static void dfs(int depth) {
        if (depth == m) {
            print();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result[depth] = arr[i];
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
    
    static void print() {
        for (int i = 0; i < m; i++) {
            sb.append(result[i]).append(" ");
        }
        sb.append("\n");
    }
}