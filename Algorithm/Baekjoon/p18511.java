import java.io.*;
import java.util.*;
public class p18511 {
    static int n, k;
    static int[] arr;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);
        System.out.println(answer);
    }
    
    static void dfs(int now) {
        if (now > n) return;
        
        if (answer < now) answer = now;
        
        for (int i = k - 1; i >= 0; i--) {
            dfs(now * 10 + arr[i]);
        }
    }
}