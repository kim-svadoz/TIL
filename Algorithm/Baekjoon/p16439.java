import java.util.*;
import java.io.*;
public class p16439 {
    static int n, m, max;
    static int[][] member;
    static int[] chicken;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[m];
        member = new int[n][m]; // 총 멤버의 치킨 선호도 표
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                member[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        max = 0;
        comb(0, 0);
        System.out.println(max);
    }
    
    static void comb(int start, int depth) {
        if (depth == 3) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int sat = 0;
                for (int j = 0; j < m; j++) {
                    if (visit[j]) {
                        sat = Math.max(sat, member[i][j]);
                    }
                }
                sum += sat;
            }
            
            max = Math.max(max, sum);
            return;
        }
        
        for (int i = start; i < m; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            comb(i, depth + 1);
            visit[i] = false;
        }
    }
}